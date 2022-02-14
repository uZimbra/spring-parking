package com.rodrigo.parkingcontrol.modules.parking.repositories;

import com.rodrigo.parkingcontrol.modules.parking.entities.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IParkingSpotRepository extends JpaRepository<ParkingSpot, UUID> {
    boolean existsByLicensePlateCar(String licensePlateCar);
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    boolean existsByApartmentAndBlock(String apartment, String block);
}
