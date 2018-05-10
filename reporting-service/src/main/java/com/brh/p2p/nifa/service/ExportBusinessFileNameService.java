package com.brh.p2p.nifa.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExportBusinessFileNameService {

    private static String socialCreditCode = "110107018286222";
    private static String category = "24";
    private static String serial = "001";

    public ExportBusinessFileNameService(){
    }

    public static String getProjFileName(){
        return "24EXPORTBUSINESSZHAIQ.txt";
    }

    public static String getBorFileName(){
        return "24EXPORTBUSINESSZHAIQ_BOR.txt";
    }

    public static String getInvFileName(){
        return "24EXPORTBUSINESSZHAIQ_INV.txt";
    }

    public static String getPackageFileName(Date date){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return String.format("%s%s%s%s", socialCreditCode, sdf.format(date.getTime()), category, serial);
    }
}
