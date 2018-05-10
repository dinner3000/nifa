package com.brh.p2p.nifa.web.controller;

import com.brh.p2p.nifa.data.entity.ExportBusinessProjEntity;
import com.brh.p2p.nifa.service.ExportBusinessProjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/nifa/report/proj")
public class ExportBusinessProjController {
    @Autowired
    private ExportBusinessProjService exportBusinessService;


    /**
     * 获取ExportBusiness数据
     * @return
     */
    @ResponseBody
    @GetMapping("/showData")
    public Object showData(String inputdate){
        return exportBusinessService.findAllDataByInputdate(inputdate);
    }

    /**
     * 根据输入日期生成数据
     * @param
     * @return
     */
    @GetMapping("/showReport")
    public String doExportBusiness(String inputdate, Model model){

        List<ExportBusinessProjEntity> records = exportBusinessService.findAllDataByInputdate(inputdate);
        model.addAttribute("records", records);

        return "report";
    }



}
