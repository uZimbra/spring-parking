package com.rodrigo.parkingcontrol.modules.parking.useCases.listParkingSpots;

import com.rodrigo.parkingcontrol.modules.parking.entities.ParkingSpot;
import com.rodrigo.parkingcontrol.modules.parking.repositories.implementations.ParkingSpotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ListParkingSpotsUseCase {

    final ParkingSpotRepository repository;

    public ListParkingSpotsUseCase(ParkingSpotRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Page<ParkingSpot>> execute(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.repository.findAll(pageable));
    }
}
