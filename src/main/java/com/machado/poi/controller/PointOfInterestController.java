package com.machado.poi.controller;

import com.machado.poi.dto.CreatePointOfInterest;
import com.machado.poi.entity.PointOfInterest;
import com.machado.poi.repository.PointOfInterestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PointOfInterestController {

    private final PointOfInterestRepository poiRepository;

    public PointOfInterestController(PointOfInterestRepository poiRepository) {
        this.poiRepository = poiRepository;
    }


    @PostMapping("/poi")
    public ResponseEntity<Void> createPoi(@RequestBody CreatePointOfInterest body) {

        poiRepository.save(new PointOfInterest(body.x(), body.y(), body.name()));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/poi")
    public ResponseEntity<Page<PointOfInterest>> listPoi(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        var body = poiRepository.findAll(PageRequest.of(page, pageSize));

        return ResponseEntity.ok(body);
    }

    @GetMapping("/near-me")
    public ResponseEntity<List<PointOfInterest>> nearMe(@RequestParam(name = "x") Long x,
                                                        @RequestParam(name = "y") Long y,
                                                        @RequestParam(name = "max") Long max) {

        var xMin = x - max;
        var yMin = y - max;
        var xMax = x + max;
        var yMax = y + max;

        var body = poiRepository.findNearMe(xMin, xMax, yMin, yMax);

        return ResponseEntity.ok(body);
    }
}
