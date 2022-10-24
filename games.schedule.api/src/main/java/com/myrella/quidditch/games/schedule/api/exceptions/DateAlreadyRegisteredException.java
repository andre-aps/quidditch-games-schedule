package com.myrella.quidditch.games.schedule.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DateAlreadyRegisteredException extends RuntimeException {
    public DateAlreadyRegisteredException(String date) {
        super(String.format("The date is already registered in the system."));
    }
}
