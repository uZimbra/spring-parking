package com.rodrigo.parkingcontrol.services;

import com.rodrigo.parkingcontrol.models.ParkingSpotModel;
import com.rodrigo.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    final ParkingSpotRepository repository;

    public ParkingSpotService(ParkingSpotRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpot) {
        return this.repository.save(parkingSpot);
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return this.repository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return this.repository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return this.repository.existsByApartmentAndBlock(apartment, block);
    }

    public Page<ParkingSpotModel> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Optional<ParkingSpotModel> findById(UUID id) {
        return this.repository.findById(id);
    }

    @Transactional
    public void delete(ParkingSpotModel data) {
        this.repository.delete(data);
    }
}
