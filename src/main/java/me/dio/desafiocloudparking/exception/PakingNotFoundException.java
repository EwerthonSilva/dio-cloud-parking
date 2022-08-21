package me.dio.desafiocloudparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class PakingNotFoundException extends RuntimeException {
    public PakingNotFoundException(String id) {
        super("Parking not found with Id: "+id);
    }
}
