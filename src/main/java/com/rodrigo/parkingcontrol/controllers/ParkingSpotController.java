package com.rodrigo.parkingcontrol.controllers;

import com.rodrigo.parkingcontrol.dtos.ParkingSpotDTO;
import com.rodrigo.parkingcontrol.models.ParkingSpotModel;
import com.rodrigo.parkingcontrol.services.ParkingSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    final ParkingSpotService service;

    public ParkingSpotController(ParkingSpotService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDTO data) {
        if (this.service.existsByLicensePlateCar(data.getLicensePlateCar()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("License Plate Car is already in use!");

        if (this.service.existsByParkingSpotNumber(data.getParkingSpotNumber()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Parking Spot is already in use!");

        if (this.service.existsByApartmentAndBlock(data.getApartment(), data.getBlock()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Parking Spot is already in registered for this apartment/block!");

        var parkingSpot = new ParkingSpotModel();
        BeanUtils.copyProperties(data, parkingSpot);
        parkingSpot.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(parkingSpot));
    }

    @GetMapping
    public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots(
            @PageableDefault(page = 0, size = 10) Pageable pageable
            ) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> parkingSpot = this.service.findById(id);

        if (parkingSpot.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found!");

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpot.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> parkingSpot = this.service.findById(id);

        if (parkingSpot.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found!");

        this.service.delete(parkingSpot.get());

        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot was successfully deleted!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid ParkingSpotDTO data
    ) {
        Optional<ParkingSpotModel> parkingSpot = this.service.findById(id);

        if (parkingSpot.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found!");

        BeanUtils.copyProperties(data, parkingSpot.get());

        this.service.save(parkingSpot.get());

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpot);
    }
}
