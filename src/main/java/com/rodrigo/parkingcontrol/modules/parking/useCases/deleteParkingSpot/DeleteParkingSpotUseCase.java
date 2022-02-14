package com.rodrigo.parkingcontrol.modules.parking.useCases.deleteParkingSpot;

import com.rodrigo.parkingcontrol.modules.parking.entities.ParkingSpot;
import com.rodrigo.parkingcontrol.modules.parking.repositories.implementations.ParkingSpotRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteParkingSpotUseCase {

    final ParkingSpotRepository repository;

    public DeleteParkingSpotUseCase(ParkingSpotRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Object> execute(UUID id) {
        Optional<ParkingSpot> parkingSpot = this.repository.findById(id);

        if (parkingSpot.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found!");

        this.repository.delete(parkingSpot.get());

        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot was successfully deleted!");
    }

}
