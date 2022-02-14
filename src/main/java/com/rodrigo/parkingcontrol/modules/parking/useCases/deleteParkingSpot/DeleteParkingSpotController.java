package com.rodrigo.parkingcontrol.modules.parking.useCases.deleteParkingSpot;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/parking-spot")
public class DeleteParkingSpotController {

    final DeleteParkingSpotUseCase useCase;

    public DeleteParkingSpotController(DeleteParkingSpotUseCase useCase) {
        this.useCase = useCase;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> handle(@PathVariable(name = "id") UUID id) {
        return this.useCase.execute(id);
    }
}
