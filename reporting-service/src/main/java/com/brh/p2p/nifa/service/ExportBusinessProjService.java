package com.brh.p2p.nifa.service;

import com.brh.p2p.nifa.data.entity.ExportBusinessProjEntity;

import java.util.List;

public interface ExportBusinessProjService {

//    List<ExportBusinessProjEntity> findAllDataByInputdate(int pageNum, int pageSize, String inputdate);
    List<ExportBusinessProjEntity> findAllDataByInputdate(String inputdate);

    void generateAllByInputdate(String inputdate);

    void removeAllByInputdate(String inputdate);

    void clearAll();

    void regenerateAllByInputdate(String inputdate);

}
