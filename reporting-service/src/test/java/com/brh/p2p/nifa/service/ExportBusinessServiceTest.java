package com.brh.p2p.nifa.service;

import com.brh.p2p.nifa.MainEntry;
import com.brh.p2p.nifa.data.entity.ExportBusinessProjEntity;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainEntry.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExportBusinessServiceTest {

    @Autowired
    private ExportBusinessService exportBusinessService;

    @Before
    public void setUp() throws Exception {
        exportBusinessService.clearAll();
    }

    @After
    public void tearDown() throws Exception {
//        exportBusinessService.clearAll();
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void generateAll() throws Exception {
        String date = "2018-05-10";
        List<ExportBusinessProjEntity> records;
        long n = 0;

        exportBusinessService.generateAll(date);

        records = exportBusinessService.findAllByInputdate(date);
        Assert.assertTrue(records.size() > 0);
        n = records.size();

        exportBusinessService.removeAllByInputdate(date);
        records = exportBusinessService.findAllByInputdate(date);
        Assert.assertTrue(records.size() <= 0);

        exportBusinessService.regenerateAll(date);
        records = exportBusinessService.findAllByInputdate(date);
        Assert.assertTrue(records.size() == n);

    }

//    @Test
//    public void generateAllByInputdate() throws Exception {
//        String date = "2018-05-09";
//        List<ExportBusinessProjEntity> records;
//        long n = 0;
//
//        exportBusinessService.generateAllByInputdate(date);
//
//        records = exportBusinessService.findAllByInputdate(date);
//        Assert.assertTrue(records.size() > 0);
//        n = records.size();
//
//        exportBusinessService.removeAllByInputdate(date);
//        records = exportBusinessService.findAllByInputdate(date);
//        Assert.assertTrue(records.size() <= 0);
//
//        exportBusinessService.regenerateAllByInputdate(date);
//        records = exportBusinessService.findAllByInputdate(date);
//        Assert.assertTrue(records.size() == n);
//
//    }

}