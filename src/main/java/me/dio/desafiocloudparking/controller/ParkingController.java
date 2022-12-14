package me.dio.desafiocloudparking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import me.dio.desafiocloudparking.controller.dto.ParkingCreateDTO;
import me.dio.desafiocloudparking.controller.dto.ParkingDTO;
import me.dio.desafiocloudparking.controller.mapper.ParkingMapper;
import me.dio.desafiocloudparking.model.Parking;
import me.dio.desafiocloudparking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@SecurityRequirement(name = "parkingapi")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper){
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }
    @GetMapping
    @Operation(description = "List all parkings")
    public ResponseEntity<List<ParkingDTO>> findAll(){

        List<Parking> parkingList = parkingService.listAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(description = "Find a parking by id")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
        Parking parking = parkingService.findById(id);

        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(description = "Create a new parking")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
        var parkingCreate = parkingMapper.toParking(dto);
        var parking = parkingService.create(parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{id}/checkout")
    public ResponseEntity<ParkingDTO> checkout(@PathVariable String id){
        Parking parking = parkingService.checkout(id);
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }


    @PutMapping("/{id}")
    @Operation(description = "Update a parking")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto){
        var parkingCreate = parkingMapper.toParking(dto);
        var parking = parkingService.update(id, parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete a parking by id")
    public ResponseEntity deleteById(@PathVariable String id){
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
