package me.dio.desafiocloudparking.service;

import me.dio.desafiocloudparking.exception.PakingNotFoundException;
import me.dio.desafiocloudparking.model.Parking;
import me.dio.desafiocloudparking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;

    }

    @Transactional
    public List<Parking> listAll(){
        return parkingRepository.findAll();
    }

    @Transactional
    public Parking findById(String id){
        return parkingRepository.findById(id).orElseThrow(() -> new PakingNotFoundException(id));
    }
    @Transactional
    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    @Transactional
    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }

    @Transactional
    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parking.setState(parkingCreate.getState());
        parking.setModel(parkingCreate.getModel());
        parking.setLicense(parkingCreate.getLicense());
        parkingRepository.save(parking);
        return parking;
    }

    @Transactional
    public Parking checkout(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckoutService.getBill(parking));
        parkingRepository.save(parking);
        return parking;
    }

}

