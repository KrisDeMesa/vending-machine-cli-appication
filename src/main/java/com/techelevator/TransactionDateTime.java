package com.techelevator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionDateTime {

    public String getDateTimeOfTransaction() {
        LocalDateTime localTime = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a");
        String dateTime = localTime.format(timeFormat);
        return dateTime;
    }
}
