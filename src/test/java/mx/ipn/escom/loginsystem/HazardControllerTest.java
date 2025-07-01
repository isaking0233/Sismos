package mx.ipn.escom.loginsystem;

import mx.ipn.escom.loginsystem.dto.HazardZoneDTO;
import mx.ipn.escom.loginsystem.controller.HazardController;
import mx.ipn.escom.loginsystem.service.HazardAnalysisService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HazardController.class)
public class HazardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HazardAnalysisService hazardService;

    @WithMockUser
    @Test
    void testGetHazardData() throws Exception {
        HazardZoneDTO dto = new HazardZoneDTO(20, -100, 3, 0.5, 0.15);
        when(hazardService.calculateHazardZones(anyDouble()))
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/api/hazard?minMagnitude=4.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].latCell").value(20))
                .andExpect(jsonPath("$[0].probability").value(0.15));
    }
}