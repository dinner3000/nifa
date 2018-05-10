package com.brh.p2p.nifa.data.repository;

import com.brh.p2p.nifa.data.entity.ExportBusinessInvEntity;

import java.util.List;

public interface ExportBusinessInvMapper {

//    int insert(ExportBusinessInvEntity record);
//
//    int insertSelective(ExportBusinessInvEntity record);


    List<ExportBusinessInvEntity> selectAllByInputdate();
}