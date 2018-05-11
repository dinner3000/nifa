package com.brh.p2p.nifa.web.controller;

import com.brh.p2p.nifa.data.entity.ExportBusinessProjEntity;
import com.brh.p2p.nifa.service.ExportBusinessFileNameService;
import com.brh.p2p.nifa.service.ExportBusinessProjServiceImpl;
import com.brh.p2p.nifa.service.ExportBusinessService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/nifa/report/proj")
public class ExportBusinessProjController {

    @Autowired
    private Configuration configuration;

    @Autowired
    @Qualifier("ExportBusinessProjService")
    private ExportBusinessService exportBusinessProjService;

    @ResponseBody
    @GetMapping("/generateData")
    public Object generateData(String inputdate) {
        exportBusinessProjService.generateAllByInputdate(inputdate);
        return "Done";
    }

    @ResponseBody
    @GetMapping("/regenerateData")
    public Object regenerateData(String inputdate) {
        exportBusinessProjService.regenerateAllByInputdate(inputdate);
        return "Done";
    }

    @ResponseBody
    @GetMapping("/showData")
    public Object showData(String inputdate) {
        List<ExportBusinessProjEntity> records = exportBusinessProjService.findAllByInputdate(inputdate);
        return records;
    }

    @GetMapping("/showFile")
    public String showFile(String inputdate, Model model) {
        List<ExportBusinessProjEntity> list = exportBusinessProjService.findAllByInputdate(inputdate);
        model.addAttribute("list", list);
        return "html/proj";
    }

}
