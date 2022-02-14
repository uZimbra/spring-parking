package com.rodrigo.parkingcontrol.modules.parking.useCases.listParkingSpots;

import com.rodrigo.parkingcontrol.modules.parking.entities.ParkingSpot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking-spot")
public class ListParkingSpotsController {

    final ListParkingSpotsUseCase useCase;

    public ListParkingSpotsController(ListParkingSpotsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<Page<ParkingSpot>> handle(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return this.useCase.execute(pageable);
    }

}
