package com.rodrigo.parkingcontrol.modules.parking.useCases.updateParkingSpot;

import com.rodrigo.parkingcontrol.modules.parking.dtos.ParkingSpotDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/parking-spot")
public class UpdateParkingSpotController {

    final UpdateParkingSpotUseCase useCase;

    public UpdateParkingSpotController(UpdateParkingSpotUseCase useCase) {
        this.useCase = useCase;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> handle(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDTO data) {
        return this.useCase.execute(id, data);
    }

}
