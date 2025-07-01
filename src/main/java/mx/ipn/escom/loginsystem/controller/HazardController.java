package mx.ipn.escom.loginsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.ipn.escom.loginsystem.dto.HazardZoneDTO;
import mx.ipn.escom.loginsystem.service.HazardAnalysisService;

@RestController
@RequestMapping("/api/hazard")
public class HazardController {

    @Autowired
    private HazardAnalysisService hazardAnalysisService;

    @GetMapping
    public List<HazardZoneDTO> getHazardZones(@RequestParam(defaultValue = "4.0") double minMagnitude) {
        return hazardAnalysisService.calculateHazardZones(minMagnitude);
    }
}
