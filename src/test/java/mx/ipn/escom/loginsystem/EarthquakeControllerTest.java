package mx.ipn.escom.loginsystem;

import mx.ipn.escom.loginsystem.EarthquakeController;
import mx.ipn.escom.loginsystem.model.Earthquake;
import mx.ipn.escom.loginsystem.repository.EarthquakeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EarthquakeController.class)
public class EarthquakeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EarthquakeRepository repository;
    
    @WithMockUser
    @Test
    void testGetEarthquakesView() throws Exception {
        when(repository.findAll()).thenReturn(List.of(new Earthquake()));

        mockMvc.perform(get("/earthquakes"))
                .andExpect(status().isOk())
                .andExpect(view().name("earthquakes"))
                .andExpect(model().attributeExists("earthquakes"));
    }
}