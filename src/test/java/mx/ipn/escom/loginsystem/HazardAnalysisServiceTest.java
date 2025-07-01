package mx.ipn.escom.loginsystem;

import mx.ipn.escom.loginsystem.model.Earthquake;
import mx.ipn.escom.loginsystem.repository.EarthquakeRepository;
import mx.ipn.escom.loginsystem.service.HazardAnalysisService;
import mx.ipn.escom.loginsystem.dto.HazardZoneDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HazardAnalysisServiceTest {

    @MockBean
    private EarthquakeRepository repository;

    @Autowired
    private HazardAnalysisService service;

    @Test
    void testCalculateHazardZones() {
        Earthquake eq = new Earthquake();
        eq.setLatitude(19.4);
        eq.setLongitude(-99.1);
        eq.setMagnitude(5.0);
        eq.setRecordDate("2024-06-30"); // Usamos String en lugar de LocalDate

        when(repository.findByMagnitudeGreaterThanEqual(anyDouble()))
                .thenReturn(List.of(eq));

        List<HazardZoneDTO> result = service.calculateHazardZones(4.0);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertTrue(result.get(0).latCell >= 19);  // Validación básica
    }
}
