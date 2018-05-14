package com.brh.p2p.nifa.web.controller;

import com.brh.p2p.nifa.data.entity.ExportBusinessProjEntity;
import com.brh.p2p.nifa.service.ExportBusinessFileNameService;
import com.brh.p2p.nifa.service.ExportBusinessFileService;
import com.brh.p2p.nifa.service.ExportBusinessService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.lingala.zip4j.exception.ZipException;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.geom.RectangularShape;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/nifa/report")
public class ExportBusinessController {

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

    @ResponseBody
    @GetMapping("/generateData")
    @Transactional
    public Object generateData(String inputdate) {
        exportBusinessProjService.generateAllByInputdate(inputdate);
        exportBusinessBorService.generateAllByInputdate(inputdate);
        exportBusinessInvService.generateAllByInputdate(inputdate);
        return true;
    }

    @ResponseBody
    @GetMapping("/regenerateData")
    @Transactional
    public Object regenerateData(String inputdate) {
        exportBusinessProjService.regenerateAllByInputdate(inputdate);
        exportBusinessBorService.regenerateAllByInputdate(inputdate);
        exportBusinessInvService.regenerateAllByInputdate(inputdate);
        return true;
    }

    @GetMapping("/generateFiles")
    @ResponseBody
    public Object generateFiles(String inputdate) throws IOException, TemplateException, ZipException {
        exportBusinessFileService.initWorkEnv(inputdate);
        String packagePath =  exportBusinessFileService.generateFilePackage();

        return packagePath;
    }

    @Value("${nifa.transfer-service.url}")
    private String nifaTransferServiceUrl;

    @GetMapping("/sendFiles")
    @ResponseBody
    public Object sendFiles(String inputdate) throws IOException, TemplateException, ZipException {

        logger.info("Manual process: {}", inputdate);
        exportBusinessFileService.initWorkEnv(inputdate);
        String packagePath =  exportBusinessFileService.generateFilePackage();

        String url = String.format("%s?systemid=1&stype=24&sourcePath=%s",
                nifaTransferServiceUrl,
                URLEncoder.encode(packagePath, "UTF-8"));

        logger.info("Prepare to sent file via url: {}", url);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        String ret = null;
        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            ret = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        logger.info("Completed: {}", ret);
        return ret;
    }


}
