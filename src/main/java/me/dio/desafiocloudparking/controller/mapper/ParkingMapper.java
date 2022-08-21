package me.dio.desafiocloudparking.controller.mapper;

import me.dio.desafiocloudparking.controller.dto.ParkingCreateDTO;
import me.dio.desafiocloudparking.controller.dto.ParkingDTO;
import me.dio.desafiocloudparking.model.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDTO toParkingDTO(Parking parking){
        return MODEL_MAPPER.map(parking,ParkingDTO.class);
    }

    public Parking toParking(ParkingDTO dto) {
        return MODEL_MAPPER.map(dto,Parking.class);
    }

    public Parking toParking(ParkingCreateDTO dto) {
        return MODEL_MAPPER.map(dto,Parking.class);
    }
    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
        return parkingList.stream().map(this::toParkingDTO).collect(Collectors.toList());
    }


}
