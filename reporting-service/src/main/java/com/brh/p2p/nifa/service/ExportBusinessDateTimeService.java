package com.brh.p2p.nifa.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExportBusinessDateTimeService {
    public Date parseFromRestParm(String date) throws ParseException {
        return this.parseFromRestParm(date, "yyyy/MM/dd");
    }

    public Date parseFromRestParm(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(date);
    }

    public String convertToDateStamp(Date date) throws ParseException {
        return this.convertToString(date, "yyyyMMdd");
    }

    public String convertToDateTimeStamp(Date date) throws ParseException {
        return this.convertToString(date, "yyyyMMddHHmmss");
    }

    public String convertToString (Date date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
