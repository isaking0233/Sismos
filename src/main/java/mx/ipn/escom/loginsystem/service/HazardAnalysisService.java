package mx.ipn.escom.loginsystem.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ipn.escom.loginsystem.dto.HazardZoneDTO;
import mx.ipn.escom.loginsystem.model.Earthquake;
import mx.ipn.escom.loginsystem.repository.EarthquakeRepository;

@Service
public class HazardAnalysisService {

    @Autowired
    private EarthquakeRepository earthquakeRepository;

    public List<HazardZoneDTO> calculateHazardZones(double minMagnitude) {
        List<Earthquake> earthquakes = earthquakeRepository.findByMagnitudeGreaterThanEqual(minMagnitude);

        // Agrupar por cuadrante
        Map<String, List<Earthquake>> gridMap = new HashMap<>();
        for (Earthquake eq : earthquakes) {
            if (eq.getLatitude() == null || eq.getLongitude() == null || eq.getMagnitude() == null) continue;
            if (eq.getMagnitude() < minMagnitude) continue;

            int latCell = (int) Math.floor(eq.getLatitude());
            int lonCell = (int) Math.floor(eq.getLongitude());
            String key = latCell + "," + lonCell;

            gridMap.computeIfAbsent(key, k -> new ArrayList<>()).add(eq);
        }

        // Estimar duración del catálogo en años
        int startYear = 9999, endYear = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Earthquake eq : earthquakes) {
            try {
                LocalDate date = LocalDate.parse(eq.getRecordDate(), formatter);
                int year = date.getYear();
                startYear = Math.min(startYear, year);
                endYear = Math.max(endYear, year);
            } catch (Exception ignored) {}
        }

        int years = Math.max(endYear - startYear + 1, 1); // evitar división por cero

        List<HazardZoneDTO> results = new ArrayList<>();
        for (Map.Entry<String, List<Earthquake>> entry : gridMap.entrySet()) {
            String[] parts = entry.getKey().split(",");
            int lat = Integer.parseInt(parts[0]);
            int lon = Integer.parseInt(parts[1]);
            long count = entry.getValue().size();
            double lambda = (double) count / years;
            double probability = 1 - Math.exp(-lambda * 30);
            results.add(new HazardZoneDTO(lat, lon, count, lambda, probability));
        }

        return results;
    }

}
