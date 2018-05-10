package com.brh.p2p.nifa.service;

import com.brh.p2p.nifa.MainEntry;
import com.brh.p2p.nifa.data.entity.ExportBusinessProjEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainEntry.class)
@WebAppConfiguration
public class ExportBusinessProjServiceTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExportBusinessProjService exportBusinessProjService;

    @Test
    public void findAllDataByInputdate() throws Exception {
        List<ExportBusinessProjEntity> records = exportBusinessProjService.findAllDataByInputdate("2018-05-10");
        logger.info("{} records returned", records.size());
    }

    @Test
    public void generateDataByInputdate() throws Exception {
//        exportBusinessProjService.generateDataByInputdate("2018-05-10");
    }

    @Test
    public void removeDataByInputdate() throws Exception {
    }

    @Test
    public void regenerateDataByInputdate() throws Exception {
    }

}