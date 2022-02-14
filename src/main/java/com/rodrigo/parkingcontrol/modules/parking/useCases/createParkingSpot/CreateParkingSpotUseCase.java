package com.rodrigo.parkingcontrol.modules.parking.useCases.createParkingSpot;

import com.rodrigo.parkingcontrol.modules.parking.dtos.ParkingSpotDTO;
import com.rodrigo.parkingcontrol.modules.parking.entities.ParkingSpot;
import com.rodrigo.parkingcontrol.modules.parking.repositories.implementations.ParkingSpotRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class CreateParkingSpotUseCase {
    final ParkingSpotRepository service;

    public CreateParkingSpotUseCase(ParkingSpotRepository service) {
        this.service = service;
    }

    public ResponseEntity<Object> execute(ParkingSpotDTO data) {
        if (this.service.existsByLicensePlateCar(data.getLicensePlateCar()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("License Plate Car is already in use!");

        if (this.service.existsByParkingSpotNumber(data.getParkingSpotNumber()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Parking Spot is already in use!");

        if (this.service.existsByApartmentAndBlock(data.getApartment(), data.getBlock()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Parking Spot is already in registered for this apartment/block!");

        var parkingSpot = new ParkingSpot();
        BeanUtils.copyProperties(data, parkingSpot);
        parkingSpot.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(parkingSpot));
    }
}
