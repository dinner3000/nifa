package com.brh.p2p.nifa.web.controller;

import com.brh.p2p.nifa.data.entity.ExportBusinessInvEntity;
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
@RequestMapping(value = "/nifa/report/inv")
public class ExportBusinessInvController {

    @Autowired
    private Configuration configuration;

    @Autowired
    @Qualifier("ExportBusinessInvService")
    private ExportBusinessService exportBusinessInvService;

    @ResponseBody
    @GetMapping("/generateData")
    public Object generateData(String inputdate) {
        exportBusinessInvService.generateAllByInputdate(inputdate);
        return "Done";
    }

    @ResponseBody
    @GetMapping("/regenerateData")
    public Object regenerateData(String inputdate) {
        exportBusinessInvService.regenerateAllByInputdate(inputdate);
        return "Done";
    }

    @ResponseBody
    @GetMapping("/showData")
    public Object showData(String inputdate) {
        List<ExportBusinessInvEntity> records = exportBusinessInvService.findAllByInputdate(inputdate);
        return records;
    }

    @GetMapping("/showFile")
    public String showFile(String inputdate, Model model) {
        List<ExportBusinessInvEntity> list = exportBusinessInvService.findAllByInputdate(inputdate);
        model.addAttribute("list", list);
        return "html/inv";
    }

}
