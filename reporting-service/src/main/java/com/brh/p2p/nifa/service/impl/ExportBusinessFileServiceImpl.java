package com.brh.p2p.nifa.service.impl;

import com.brh.p2p.nifa.data.entity.ExportBusinessProjEntity;
import com.brh.p2p.nifa.service.ExportBusinessDateTimeService;
import com.brh.p2p.nifa.service.ExportBusinessFileNameService;
import com.brh.p2p.nifa.service.ExportBusinessFileService;
import com.brh.p2p.nifa.service.ExportBusinessService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.http.HttpEntity;
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
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExportBusinessFileServiceImpl implements ExportBusinessFileService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Configuration configuration;

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
    private ExportBusinessDateTimeService exportBusinessDateTimeService;

    @Autowired
    private ExportBusinessFileNameService exportBusinessFileNameService;

    @Value("${nifa.work-dir.datafiles}")
    private String dataFilesDir;
//    @Value("${nifa.work-dir.feedback}")
//    private String feedbackDir;

    private Path dataFilesDirPath;
//    private Path feedBackDirPath;

    @PostConstruct
    public void initWorkDir() throws ParseException {
        Path dirPath = Paths.get(this.dataFilesDir);
        File dir = dirPath.toFile();
        if(!dir.exists()){
            dir.mkdirs();
        }
        this.dataFilesDirPath = dirPath;
        logger.info("Data file dir: {}", dirPath.toString());

        if(!dir.exists()){
            logger.error("Unexpected error: work dir not exists");
        }
    }

//    protected void initFeedbackDir() throws ParseException {
//        Path dirPath = Paths.get(this.feedbackDir);
//        File dir = dirPath.toFile();
//        if(!dir.exists()){
//            dir.mkdirs();
//        }
//        this.feedBackDirPath = dirPath;
//        logger.info("Feedback dir: {}", dirPath.toString());
//
//        if(!dir.exists()){
//            logger.error("Unexpected error: feedback dir not exists");
//        }
//    }

    @Value("${nifa.transfer-service.upload-url}")
    private String nifaTransferServiceUploadUrl;

    @Override
    public String transferFilePackage(String packagePath) throws Exception {

        String url = String.format(
                nifaTransferServiceUploadUrl,
                URLEncoder.encode(packagePath, "UTF-8"));

        logger.info("Prepare to sent file via url: {}", url);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        String ret = null;
        try {
            logger.info(response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            ret = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        logger.info("Completed: {}", ret);
        return ret;
    }

    @Override
    public String generateFilePackage(String inputDate) throws Exception {

        logger.info("Generate package for date: {}", inputDate);

        File projFile = this.generateProjFile(inputDate);
        File borFile = this.generateBorFile(inputDate);
        File invFile = this.generateInvFile(inputDate);

        logger.info("Package files into a zip");
        ZipParameters par = new ZipParameters();
        par.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        par.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

        String datestamp = exportBusinessDateTimeService.convertToDateStamp(exportBusinessDateTimeService.parseFromRestParm(inputDate));
        Path pkgPath = null;
        boolean availableNameFound = false;
        for (int i = 1; i <= 999; i++){
            pkgPath = Paths.get(dataFilesDirPath.toString(), exportBusinessFileNameService.getPackageFileName(datestamp, i));
            File pkgFile = pkgPath.toFile();
            if(!pkgFile.exists()){
                availableNameFound = true;
                break;
            }
        }
        if(!availableNameFound){
            throw new Exception("Cannot find available package name!");
        }

        ZipFile zipfile=new ZipFile(pkgPath.toString());
        zipfile.addFile(projFile, par);
        zipfile.addFile(borFile, par);
        zipfile.addFile(invFile, par);
        logger.info("Packaged: {}", pkgPath.toString());

        projFile.delete();
        borFile.delete();
        invFile.delete();
        logger.info("Source file cleared");

        return pkgPath.toString();
    }

    @Override
    public File generateProjFile(String inputDate) throws Exception {

        logger.info("Generate: proj file");
        Map<String, Object> model = new HashMap<>();
        List<ExportBusinessProjEntity> list = exportBusinessProjService.findAllByInputdate(inputDate);
        if(list.size() <= 0){
            logger.info("No data found");
//            throw new Exception("No data found");
        }

        model.put("list", list);

        Path filePath = Paths.get(dataFilesDirPath.toString(), exportBusinessFileNameService.getProjFileName());
        File file = filePath.toFile();
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

        Template t = configuration.getTemplate("text/proj.ftl");
        t.process(model, out);
        out.flush();
        out.close();
        logger.info("Generated: {}", filePath.toString());

        return file;
    }

    @Override
    public File generateBorFile(String inputDate) throws Exception {

        logger.info("Generate: bor file");
        Map<String, Object> model = new HashMap<>();
        List<ExportBusinessProjEntity> list = exportBusinessBorService.findAllByInputdate(inputDate);
        if(list.size() <= 0){
            logger.info("No data found");
//            throw new Exception("No data found");
        }

        model.put("list", list);

        Path filePath = Paths.get(dataFilesDirPath.toString(), exportBusinessFileNameService.getBorFileName());
        File file = filePath.toFile();
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

        Template t = configuration.getTemplate("text/bor.ftl");
        t.process(model, out);
        out.flush();
        out.close();
        logger.info("Generated: {}", filePath.toString());

        return file;
    }

    @Override
    public File generateInvFile(String inputDate) throws Exception {

        logger.info("Generate: inv file");
        Map<String, Object> model = new HashMap<>();
        List<ExportBusinessProjEntity> list = exportBusinessInvService.findAllByInputdate(inputDate);
        if(list.size() <= 0){
            logger.info("No data found");
//            throw new Exception("No data found");
        }

        model.put("list", list);

        Path filePath = Paths.get(dataFilesDirPath.toString(), exportBusinessFileNameService.getInvFileName());
        File file = filePath.toFile();
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

        Template t = configuration.getTemplate("text/inv.ftl");
        t.process(model, out);
        out.flush();
        out.close();
        logger.info("Generated: {}", filePath.toString());

        return file;
    }

}
