package com.brh.p2p.nifa.web.controller;

import com.brh.p2p.nifa.data.entity.ExportBusinessProjEntity;
import com.brh.p2p.nifa.service.ExportBusinessProjService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping(value = "")
public class GenerateFileController {

    @Autowired
    private ExportBusinessProjService exportBusinessProjService;

    @Autowired
    private Configuration configuration;

    @ResponseBody
    @GetMapping("/generateFile")
    public String generateFile(String inputdate){
        String content = "";
        Map<String ,Object> model = new HashMap<>();
        List<ExportBusinessProjEntity> list = exportBusinessProjService.findAllDataByInputdate(inputdate);
        model.put("list" ,list);
        try {
            Template t = configuration.getTemplate("test.ftl");
            content = FreeMarkerTemplateUtils.processTemplateIntoString(t,model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
