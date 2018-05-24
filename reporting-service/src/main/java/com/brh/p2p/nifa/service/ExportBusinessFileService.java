package com.brh.p2p.nifa.service;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public interface ExportBusinessFileService {

    String transferFilePackage(String packagePath) throws Exception;

    String generateFilePackage(String inputDate) throws Exception;

    File generateProjFile(String inputDate) throws Exception;

    File generateBorFile(String inputDate) throws Exception;

    File generateInvFile(String inputDate) throws Exception;
}
