package com.brh.p2p.nifa.data.repository;

import com.brh.p2p.nifa.data.entity.ExportBusinessBorEntity;

import java.util.List;

public interface ExportBusinessBorMapper {

//    int insert(ExportBusinessBorEntity record);
//
//    int insertSelective(ExportBusinessBorEntity record);

    List<ExportBusinessBorEntity> selectAllByInputdate();

}