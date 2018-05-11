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
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
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

    private String inputDate;
    private Path dirPath;

    public void initWorkEnv(String inputdate){

        logger.info("Init file service with below settings:");

        logger.info("Date: {}", inputdate);
        this.inputDate = inputdate;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Path dirPath = Paths.get(System.getProperty("user.dir"),
                "datafiles",
                inputdate.replaceAll("/", "-"),
                sdf.format(new Date()));
        File dir = dirPath.toFile();
        if(!dir.exists()){
            dir.mkdir();
        }
        this.dirPath = dirPath;
        logger.info("Work dir: {}", dirPath.toString());
    }

    public String generateFilePackage() throws IOException, TemplateException, ZipException {
        this.generateProjFile();
        this.generateBorFile();
        this.generateInvFile();

        logger.info("Package files into a zip");
        ZipParameters par=new ZipParameters();
        par.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        par.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

        Path pkgPath = Paths.get(dirPath.toString(), ExportBusinessFileNameService.getPackageFileName(new Date()));
        ZipFile zipfile=new ZipFile(pkgPath.toString());

        File dir = dirPath.toFile();
        for (File f : dir.listFiles()){
            if(f.isFile()){
                zipfile.addFile(f, par);
            }
        }
        logger.info("Packaged: {}", pkgPath.toString());

        return pkgPath.toString();
    }

    public void generateProjFile() throws IOException, TemplateException {

        logger.info("Generate: proj file");
        Map<String, Object> model = new HashMap<>();
        List<ExportBusinessProjEntity> list = exportBusinessProjService.findAllByInputdate(inputDate);
        model.put("list", list);

        Path filePath = Paths.get(dirPath.toString(), ExportBusinessFileNameService.getProjFileName());
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath.toFile()), "UTF-8"));

        Template t = configuration.getTemplate("text/proj.ftl");
        t.process(model, out);
        out.flush();
        out.close();
        logger.info("Generated: {}", filePath.toString());
    }

    public void generateBorFile() throws IOException, TemplateException {

        logger.info("Generate: bor file");
        Map<String, Object> model = new HashMap<>();
        List<ExportBusinessProjEntity> list = exportBusinessBorService.findAllByInputdate(inputDate);
        model.put("list", list);

        Path filePath = Paths.get(dirPath.toString(), ExportBusinessFileNameService.getBorFileName());
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath.toFile()), "UTF-8"));

        Template t = configuration.getTemplate("text/bor.ftl");
        t.process(model, out);
        out.flush();
        out.close();
        logger.info("Generated: {}", filePath.toString());
    }

    public void generateInvFile() throws IOException, TemplateException {

        logger.info("Generate: inv file");
        Map<String, Object> model = new HashMap<>();
        List<ExportBusinessProjEntity> list = exportBusinessInvService.findAllByInputdate(inputDate);
        model.put("list", list);

        Path filePath = Paths.get(dirPath.toString(), ExportBusinessFileNameService.getInvFileName());
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath.toFile()), "UTF-8"));

        Template t = configuration.getTemplate("text/inv.ftl");
        t.process(model, out);
        out.flush();
        out.close();
        logger.info("Generated: {}", filePath.toString());
    }

}
