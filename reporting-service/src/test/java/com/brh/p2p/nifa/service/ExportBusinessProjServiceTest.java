package com.brh.p2p.nifa.service;

import com.brh.p2p.nifa.MainEntry;
import com.brh.p2p.nifa.data.entity.ExportBusinessProjEntity;
import com.brh.p2p.nifa.service.ExportBusinessProjService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExportBusinessProjServiceTest {

    @Autowired
    private ExportBusinessProjService exportBusinessProjService;

    @Before
    public void setUp() throws Exception {
        exportBusinessProjService.clearAll();
    }

    @After
    public void tearDown() throws Exception {
        exportBusinessProjService.clearAll();
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void t2generateDataByInputdate() throws Exception {
        String date = "2018-05-09";
        List<ExportBusinessProjEntity> records;
        long n = 0;

        exportBusinessProjService.generateAllByInputdate(date);

        records = exportBusinessProjService.findAllDataByInputdate(date);
        Assert.assertTrue(records.size() > 0);
        n = records.size();

        exportBusinessProjService.removeAllByInputdate(date);
        records = exportBusinessProjService.findAllDataByInputdate(date);
        Assert.assertTrue(records.size() <= 0);

        exportBusinessProjService.regenerateAllByInputdate(date);
        records = exportBusinessProjService.findAllDataByInputdate(date);
        Assert.assertTrue(records.size() == n);

    }

}