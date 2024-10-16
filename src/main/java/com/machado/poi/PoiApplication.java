package com.machado.poi;

import com.machado.poi.entity.PointOfInterest;
import com.machado.poi.repository.PointOfInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PoiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PoiApplication.class, args);
	}

	@Autowired
	private PointOfInterestRepository poiRepo;

	@Override
	public void run(String... args) throws Exception {
		poiRepo.save(new PointOfInterest(27L, 12L, "Lanchonete"));
		poiRepo.save(new PointOfInterest(31L, 18L, "Posto"));
		poiRepo.save(new PointOfInterest(15L, 12L, "Joalheria"));
		poiRepo.save(new PointOfInterest(19L, 31L, "Floricultura"));
		poiRepo.save(new PointOfInterest(12L, 8L, "Pub"));
		poiRepo.save(new PointOfInterest(23L, 6L, "Supermercado"));
		poiRepo.save(new PointOfInterest(28L, 2L, "Churrascaria"));
	}
}
