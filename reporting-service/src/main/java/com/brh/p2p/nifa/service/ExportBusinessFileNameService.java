package com.brh.p2p.nifa.service;

import org.springframework.stereotype.Service;

@Service
public class ExportBusinessFileNameService {

    private String socialCreditCode = "91110107318330368D";
    private String category = "24";
//    private String serial = "001";
    private String baseName = "EXPORTBUSINESSZHAIQ";

    public ExportBusinessFileNameService(){
    }

    public String getProjFileName(){
        return String.format("%s%s.txt", category, baseName);
    }

    public String getBorFileName(){
        return String.format("%s%s_BOR.txt", category, baseName);
    }

    public String getInvFileName(){
        return String.format("%s%s_INV.txt", category, baseName);
    }

    public String getPackageFileName(String datestamp, Integer serial){
        return String.format("%s%s%s%03d.zip", socialCreditCode, datestamp, category, serial);
    }
}
