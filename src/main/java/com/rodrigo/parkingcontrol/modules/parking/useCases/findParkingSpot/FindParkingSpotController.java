package com.rodrigo.parkingcontrol.modules.parking.useCases.findParkingSpot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/parking-spot")
public class FindParkingSpotController {

    final FindParkingSpotUseCase useCase;

    public FindParkingSpotController(FindParkingSpotUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> handle(@PathVariable(name = "id") UUID id) {
        return this.useCase.execute(id);
    }

}
