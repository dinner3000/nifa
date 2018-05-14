package com.brh.p2p.nifa.web.controller;

import com.brh.p2p.nifa.data.entity.ExportBusinessBorEntity;
import com.brh.p2p.nifa.service.ExportBusinessService;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/nifa/report/bor")
public class ExportBusinessBorController {

    @Autowired
    private Configuration configuration;

    @Autowired
    @Qualifier("ExportBusinessBorService")
    private ExportBusinessService exportBusinessBorService;

    @ResponseBody
    @GetMapping("/generateData")
    public Object generateData(String inputdate) {
        exportBusinessBorService.generateAllByInputdate(inputdate);
        return "Done";
    }

    @ResponseBody
    @GetMapping("/regenerateData")
    public Object regenerateData(String inputdate) {
        exportBusinessBorService.regenerateAllByInputdate(inputdate);
        return "Done";
    }

    @ResponseBody
    @GetMapping("/showData")
    public Object showData(String inputdate) {
        List<ExportBusinessBorEntity> records = exportBusinessBorService.findAllByInputdate(inputdate);
        return records;
    }

    @GetMapping("/showFile")
    public String showFile(String inputdate, Model model) {
        List<ExportBusinessBorEntity> list = exportBusinessBorService.findAllByInputdate(inputdate);
        model.addAttribute("list", list);
        return "html/bor";
    }

}
