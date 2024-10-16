package com.machado.poi.controller;

import com.machado.poi.dto.CreatePointOfInterest;
import com.machado.poi.entity.PointOfInterest;
import com.machado.poi.service.PointOfInterestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poi")
public class PointOfInterestController {

    private final PointOfInterestService poiService;

    public PointOfInterestController(PointOfInterestService poiService) {
        this.poiService = poiService;
    }

    @PostMapping
    public ResponseEntity<Void> createPoi(@RequestBody CreatePointOfInterest body) {
        poiService.createPoi(body);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<PointOfInterest>> listPoi(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        var body = poiService.listAllPois(page, pageSize);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/near-me")
    public ResponseEntity<List<PointOfInterest>> nearMe(@RequestParam(name = "x") Long x,
                                                        @RequestParam(name = "y") Long y,
                                                        @RequestParam(name = "max") Long max) {
        var body = poiService.findNearMe(x, y, max);
        return ResponseEntity.ok(body);
    }
}
