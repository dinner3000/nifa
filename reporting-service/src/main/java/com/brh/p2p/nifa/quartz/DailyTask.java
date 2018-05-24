package com.brh.p2p.nifa.quartz;

import com.brh.p2p.nifa.service.ExportBusinessFileService;
import com.brh.p2p.nifa.service.ExportBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
@EnableScheduling
public class DailyTask {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("ExportBusinessProjService")
    private ExportBusinessService exportBusinessProjService;

    @Autowired
    @Qualifier("ExportBusinessBorService")
    private ExportBusinessService exportBusinessBorService;

    @Autowired
    @Qualifier("ExportBusinessInvService")
    private ExportBusinessService exportBusinessInvService;

    @Autowired
    private ExportBusinessFileService exportBusinessFileService;

    @Scheduled(cron = "0 0 2 * * ?")
    public void dailyDataGenerator() {

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String inputDate = sdf.format(calendar.getTime());

        try {
            logger.info("Daily data generation start for date: {}", inputDate);
            exportBusinessProjService.generateAllByInputdate(inputDate);
            exportBusinessBorService.generateAllByInputdate(inputDate);
            exportBusinessInvService.generateAllByInputdate(inputDate);
            logger.info("Complete");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 11 * * ?")
    public void dailyDataTransfer() {

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String inputDate = sdf.format(calendar.getTime());

        try {
            logger.info("Daily data transfer start for date: {}", inputDate);
            String pkgPath = exportBusinessFileService.generateFilePackage(inputDate);
            String result = exportBusinessFileService.transferFilePackage(pkgPath);
            logger.info("Complete with result: {}", result);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}