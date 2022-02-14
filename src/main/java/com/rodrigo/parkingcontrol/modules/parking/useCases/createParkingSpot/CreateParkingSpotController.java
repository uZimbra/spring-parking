package com.rodrigo.parkingcontrol.modules.parking.useCases.createParkingSpot;


import com.rodrigo.parkingcontrol.modules.parking.dtos.ParkingSpotDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/parking-spot")
public class CreateParkingSpotController {

    final CreateParkingSpotUseCase useCase;

    public CreateParkingSpotController(CreateParkingSpotUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<Object> handle(@RequestBody @Valid ParkingSpotDTO data) {
        return this.useCase.execute(data);
    }

}
