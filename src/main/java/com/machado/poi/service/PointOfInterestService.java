package com.machado.poi.service;

import com.machado.poi.dto.CreatePointOfInterest;
import com.machado.poi.entity.PointOfInterest;
import com.machado.poi.repository.PointOfInterestRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointOfInterestService {

    private final PointOfInterestRepository poiRepository;

    public PointOfInterestService(PointOfInterestRepository poiRepository) {
        this.poiRepository = poiRepository;
    }

    public void createPoi(CreatePointOfInterest body) {
        poiRepository.save(new PointOfInterest(body.x(), body.y(), body.name()));
    }

    public List<PointOfInterest> listAllPoints(int page, int pageSize) {
        return poiRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    public List<PointOfInterest> findNearMe(Long x, Long y, Long maxDistance) {
        var xMin = x - maxDistance;
        var yMin = y - maxDistance;
        var xMax = x + maxDistance;
        var yMax = y + maxDistance;

        return poiRepository.findNearMe(xMin, xMax, yMin, yMax)
                .stream()
                .filter(p -> calculateDistance(x, y, p.getX(), p.getY()) <= maxDistance)
                .toList();
    }

    private Double calculateDistance(Long x1, Long y1, Long x2, Long y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
