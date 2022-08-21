package me.dio.desafiocloudparking.service;

import me.dio.desafiocloudparking.model.Parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingCheckoutService {

    public static final int ONE_HOUR = 60;
    public static final int TWENTY_FOUR_HOUR = 24 * ONE_HOUR;
    public static final double ONE_HOUR_VALUE = 5.00;
    public static final double ADDITIONAL_PER_HOUR_VALUE = 2.00;
    public static final double DAY_VALUE = 20.00;

    public static Double getBill(Parking parking){
        return getBill(parking.getEntryDate(), parking.getExitDate());
    }

    private static Double getBill(LocalDateTime entryDate, LocalDateTime exitDate) {
        long minutes = entryDate.until(exitDate, ChronoUnit.MINUTES);
        Double bill = ONE_HOUR_VALUE;

        if(minutes <= ONE_HOUR){
            return bill;
        }

        if(minutes <= TWENTY_FOUR_HOUR){
            int hours = (int) (minutes/ ONE_HOUR);
            for (int i = 0; i < hours; i++){
                bill += ADDITIONAL_PER_HOUR_VALUE;
            }
            return bill;
        }

        int days = (int) (minutes/TWENTY_FOUR_HOUR);
        for (int i = 0; i < days; i++) {
            bill += DAY_VALUE;
        }
        return bill;
    }
}
