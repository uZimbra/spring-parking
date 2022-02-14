package com.rodrigo.parkingcontrol.modules.parking.useCases.updateParkingSpot;

import com.rodrigo.parkingcontrol.modules.parking.dtos.ParkingSpotDTO;
import com.rodrigo.parkingcontrol.modules.parking.entities.ParkingSpot;
import com.rodrigo.parkingcontrol.modules.parking.repositories.implementations.ParkingSpotRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateParkingSpotUseCase {

    final ParkingSpotRepository repository;

    public UpdateParkingSpotUseCase(ParkingSpotRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Object> execute(UUID id, ParkingSpotDTO data) {
        Optional<ParkingSpot> parkingSpot = this.repository.findById(id);

        if (parkingSpot.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found!");

        BeanUtils.copyProperties(data, parkingSpot.get());

        this.repository.save(parkingSpot.get());

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpot);
    }

}
