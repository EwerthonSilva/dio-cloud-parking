package me.dio.desafiocloudparking.controller;

import me.dio.desafiocloudparking.controller.dto.ParkingDTO;
import me.dio.desafiocloudparking.controller.mapper.ParkingMapper;
import me.dio.desafiocloudparking.model.Parking;
import me.dio.desafiocloudparking.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper){
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }
    @GetMapping
    public List<ParkingDTO> findAll(){

        List<Parking> parkingList = parkingService.listAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return result;
    }
}
