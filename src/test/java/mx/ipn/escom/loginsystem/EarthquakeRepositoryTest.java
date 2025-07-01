package mx.ipn.escom.loginsystem;

import mx.ipn.escom.loginsystem.model.Earthquake;
import mx.ipn.escom.loginsystem.repository.EarthquakeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@EntityScan(basePackages = "mx.ipn.escom.loginsystem.model")
@EnableJpaRepositories(basePackages = "mx.ipn.escom.loginsystem.repository")
@ComponentScan(basePackages = "mx.ipn.escom.loginsystem")
public class EarthquakeRepositoryTest {

    @Autowired
    private EarthquakeRepository repository;

    @Test
    void testSaveAndRetrieve() {
        Earthquake eq = new Earthquake();
        eq.setMagnitude(5.5);
        eq.setLatitude(19.4);
        eq.setLongitude(-99.1);
        eq.setRecordDate("2024-06-30");
        eq.setLocality("CDMX");

        Earthquake saved = repository.save(eq);
        Optional<Earthquake> found = repository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("CDMX", found.get().getLocality());
    }
}
