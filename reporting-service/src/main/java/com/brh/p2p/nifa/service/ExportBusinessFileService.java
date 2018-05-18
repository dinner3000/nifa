package com.brh.p2p.nifa.service;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public interface ExportBusinessFileService {

    void initWorkEnv(String inputDate) throws ParseException;

    String generateFilePackage() throws Exception;

    File generateProjFile() throws IOException, TemplateException;

    File generateBorFile() throws IOException, TemplateException;

    File generateInvFile() throws IOException, TemplateException;
}
