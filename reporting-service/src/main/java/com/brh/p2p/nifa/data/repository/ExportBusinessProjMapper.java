package com.brh.p2p.nifa.data.repository;

import com.brh.p2p.nifa.data.entity.ExportBusinessProjEntity;

import java.util.List;

public interface ExportBusinessProjMapper {

//    int insert(ExportBusinessProjEntity record);
//
//    int insertSelective(ExportBusinessProjEntity record);
//
    int insertAllByInputdate(String inputdate);
    int insertAll(String inputdate);

    List<ExportBusinessProjEntity> selectAllByInputdate(String inputdate);

    int deleteAllByInpudate(String inputdate);
    int deleteAll();
}
