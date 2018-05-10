package com.brh.p2p.nifa.web.controller;

import com.brh.p2p.nifa.data.entity.ExportBusinessProjEntity;
import com.brh.p2p.nifa.service.ExportBusinessFileNameService;
import com.brh.p2p.nifa.service.ExportBusinessProjService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.tomcat.jni.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/nifa/report/proj")
public class ExportBusinessProjController {

    @Autowired
    private Configuration configuration;

    @Autowired
    private ExportBusinessProjService exportBusinessProjService;

    @ResponseBody
    @GetMapping("/generateData")
    public Object generateData(String inputdate) {
        exportBusinessProjService.generateAll(inputdate);
        return "Done";
    }

    @ResponseBody
    @GetMapping("/showData")
    public Object showData(String inputdate) {
        List<ExportBusinessProjEntity> records = exportBusinessProjService.findAllByInputdate(inputdate);
        return records;
    }

//    @GetMapping("/showReport")
//    public String doExportBusiness(String inputdate, Model model){
//
//        List<ExportBusinessProjEntity> records = exportBusinessProjService.findAllByInputdate(inputdate);
//        model.addAttribute("records", records);
//
//        return "report";
//    }

    @GetMapping("/showFile")
    public String showFile(String inputdate, Model model) {
        List<ExportBusinessProjEntity> list = exportBusinessProjService.findAllByInputdate(inputdate);
        model.addAttribute("list", list);
        return "html/proj";
    }

    @Value("${nifa.report.file.dir}")
    private String filePath;

    @GetMapping("/saveFile")
    @ResponseBody
    public Object generateFile(String inputdate) throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        List<ExportBusinessProjEntity> list = exportBusinessProjService.findAllByInputdate(inputdate);
        model.put("list", list);

        Path dirPath = Paths.get(System.getProperty("user.dir"), "datafiles");
        File dir = dirPath.toFile();
        if(!dir.exists()){
            dir.mkdir();
        }

        Path filePath = Paths.get(dirPath.toString(), ExportBusinessFileNameService.getProjFileName());
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath.toFile()), "UTF-8"));
        Template t = configuration.getTemplate("text/proj.ftl");
        t.process(model, out);
        out.flush();
        out.close();

        return "Done";
    }
}
