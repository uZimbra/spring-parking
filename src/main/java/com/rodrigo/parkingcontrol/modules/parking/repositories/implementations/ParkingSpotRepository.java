package com.rodrigo.parkingcontrol.modules.parking.repositories.implementations;

import com.rodrigo.parkingcontrol.modules.parking.entities.ParkingSpot;
import com.rodrigo.parkingcontrol.modules.parking.repositories.IParkingSpotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotRepository {

    final IParkingSpotRepository repository;

    public ParkingSpotRepository(IParkingSpotRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ParkingSpot save(ParkingSpot parkingSpot) {
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

    public Page<ParkingSpot> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Optional<ParkingSpot> findById(UUID id) {
        return this.repository.findById(id);
    }

    @Transactional
    public void delete(ParkingSpot data) {
        this.repository.delete(data);
    }
}
