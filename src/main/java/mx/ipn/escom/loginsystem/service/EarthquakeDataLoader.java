package mx.ipn.escom.loginsystem.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import mx.ipn.escom.loginsystem.model.Earthquake;
import mx.ipn.escom.loginsystem.repository.EarthquakeRepository;

@Service
public class EarthquakeDataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(EarthquakeDataLoader.class);

    @Autowired
    private EarthquakeRepository earthquakeRepository;

    @Override
    public void run(String... args) throws Exception {
        String reloadEnv = System.getenv("RELOAD_EARTHQUAKE_CSV");

        if ("true".equalsIgnoreCase(reloadEnv)) {
            logger.info("Recargando datos de sismos desde CSV (modo forzado activado)...");

            earthquakeRepository.deleteAll();

            try (Reader reader = new BufferedReader(new InputStreamReader(
                    new ClassPathResource("sismos_mexico.csv").getInputStream()))) {

                ColumnPositionMappingStrategy<Earthquake> strategy = new ColumnPositionMappingStrategy<>();
                strategy.setType(Earthquake.class);
                strategy.setColumnMapping(
                    "spatialEvent",
                    "magnitude",
                    "intensity",
                    "depth",
                    "time",
                    "latitude",
                    "longitude",
                    "entity",
                    "municipality",
                    "locality",
                    "recordDate",
                    "source",
                    "observations",
                    "representation"
                );

                CsvToBean<Earthquake> csvToBean = new CsvToBeanBuilder<Earthquake>(reader)
                        .withMappingStrategy(strategy)
                        .withSkipLines(1) // salta encabezado
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<Earthquake> earthquakes = csvToBean.parse();

                if (!earthquakes.isEmpty()) {
                    logger.info("Ejemplo de carga: magnitud={}, lat={}, lon={}",
                            earthquakes.get(0).getMagnitude(),
                            earthquakes.get(0).getLatitude(),
                            earthquakes.get(0).getLongitude());
                }

                earthquakeRepository.saveAll(earthquakes);
                logger.info("Datos de sismos cargados: {} registros", earthquakes.size());

            } catch (Exception e) {
                logger.error("Error al cargar el archivo CSV de sismos", e);
            }

        } else {
            logger.info("Carga de sismos omitida (RELOAD_EARTHQUAKE_CSV no está en 'true')");
        }
    }
}
