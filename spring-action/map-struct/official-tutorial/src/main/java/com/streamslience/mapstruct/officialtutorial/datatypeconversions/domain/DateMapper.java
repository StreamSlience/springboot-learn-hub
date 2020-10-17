package com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateMapper {

    public String asString(Date date) {
        return date != null ? new SimpleDateFormat("yyyy-MM-dd").format(date) : null;
    }

    public Date asDate(String date) {
        try {
            return date != null ? new SimpleDateFormat("yyy-MM-dd").parse(date) : null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
