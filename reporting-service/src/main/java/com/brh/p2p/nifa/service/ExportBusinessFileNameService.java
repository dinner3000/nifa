package com.brh.p2p.nifa.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExportBusinessFileNameService {

    private String socialCreditCode = "91110107318330368D";
    private String category = "24";
//    private String serial = "001";

    public ExportBusinessFileNameService(){
    }

    public String getProjFileName(){
        return "24EXPORTBUSINESSZHAIQ.txt";
    }

    public String getBorFileName(){
        return "24EXPORTBUSINESSZHAIQ_BOR.txt";
    }

    public String getInvFileName(){
        return "24EXPORTBUSINESSZHAIQ_INV.txt";
    }

    public String getPackageFileName(String inputDate, Integer serial){
        return String.format("%s%s%s%03d.zip", socialCreditCode, inputDate, category, serial);
    }
}
