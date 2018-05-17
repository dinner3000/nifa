package com.brh.p2p.nifa.service;

import com.brh.p2p.nifa.data.entity.ExportBusinessProjEntity;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExportBusinessFileService {

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

    private String inputDate;
    private Path dataFilesDirPath;
//    private Path feedBackDirPath;

    public void initWorkEnv(String inputDate) throws ParseException {

        logger.info("Init file service with below settings:");

        logger.info("Date: {}", inputDate);
        this.inputDate = inputDate;

        this.initDataFilesDir(inputDate);
//        this.initFeedbackDir();
    }

    protected void initDataFilesDir(String inputDate) throws ParseException {
        Date date = exportBusinessDateTimeService.parseFromRestParm(inputDate);
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

    public String generateFilePackage() throws Exception {
        File projFile = this.generateProjFile();
        File borFile = this.generateBorFile();
        File invFile = this.generateInvFile();

        logger.info("Package files into a zip");
        ZipParameters par = new ZipParameters();
        par.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        par.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

        String dateStamp = exportBusinessDateTimeService.convertToDateStamp(exportBusinessDateTimeService.parseFromRestParm(this.inputDate));
        Path pkgPath = null;
        boolean availableNameFound = false;
        for (int i = 1; i <= 999; i++){
            pkgPath = Paths.get(dataFilesDirPath.toString(), exportBusinessFileNameService.getPackageFileName(dateStamp, i));
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

    public File generateProjFile() throws IOException, TemplateException {

        logger.info("Generate: proj file");
        Map<String, Object> model = new HashMap<>();
        List<ExportBusinessProjEntity> list = exportBusinessProjService.findAllByInputdate(inputDate);
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

    public File generateBorFile() throws IOException, TemplateException {

        logger.info("Generate: bor file");
        Map<String, Object> model = new HashMap<>();
        List<ExportBusinessProjEntity> list = exportBusinessBorService.findAllByInputdate(inputDate);
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

    public File generateInvFile() throws IOException, TemplateException {

        logger.info("Generate: inv file");
        Map<String, Object> model = new HashMap<>();
        List<ExportBusinessProjEntity> list = exportBusinessInvService.findAllByInputdate(inputDate);
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
