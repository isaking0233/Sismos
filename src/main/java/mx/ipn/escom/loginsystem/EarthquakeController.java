package mx.ipn.escom.loginsystem;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import mx.ipn.escom.loginsystem.model.Earthquake;
import mx.ipn.escom.loginsystem.repository.EarthquakeRepository;

@Controller
public class EarthquakeController {

    @Autowired
    private EarthquakeRepository earthquakeRepository;

    @GetMapping("/earthquakes")
    public String showEarthquakesMap(Model model) {
        List<Earthquake> earthquakes = earthquakeRepository.findAll();
        model.addAttribute("earthquakes", earthquakes);
        return "earthquakes"; // Esto debe coincidir con el nombre de tu archivo HTML
    }

    @GetMapping("/earthquake/{id}")
    public String getEarthquakeDetails(@PathVariable Long id, Model model) {
        Optional<Earthquake> earthquake = earthquakeRepository.findById(id);
        earthquake.ifPresent(e -> model.addAttribute("earthquake", e));
        return "earthquake-details"; // Esto debe coincidir con el nombre de tu archivo HTML
    }
}