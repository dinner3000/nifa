package com.brh.p2p.nifa.service.impl;

import com.brh.p2p.nifa.data.repository.ExportBusinessProjMapper;
import com.brh.p2p.nifa.data.entity.ExportBusinessProjEntity;
import com.brh.p2p.nifa.service.ExportBusinessProjService;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExportBusinessProjServiceImpl implements ExportBusinessProjService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ExportBusinessProjMapper exportBusinessProjMapper;

    @Autowired
    private Configuration configuration;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
//    @Override
//    public List<ExportBusinessProjEntity> findAllDataByInputdate(int pageNum, int pageSize, String inputdate) {
//
//        //将参数传给这个方法可以实现物理分页
//        PageHelper.startPage(pageNum, pageSize);
//        return exportBusinessProjMapper.findAllDataByInputdate(inputdate);
//    }

    /**
     * 不分页获取所有指定日期数据
     * @param inputdate
     * @return
     */
    @Override
    public List<ExportBusinessProjEntity> findAllDataByInputdate(String inputdate) {

        return exportBusinessProjMapper.selectAllByInputdate(inputdate);
    }

    /**
     * 删除指定日期数据
     * @param inputdate
     * @return
     */
    @Override
    @Transactional
    public void removeDataByInputdate(String inputdate){
        int n = exportBusinessProjMapper.deleteAllByInpudate(inputdate);
        logger.debug("{} records deleted", n);
    }

    /**
     *
     * @param inputdate
     * @return
     */
    @Override
    @Transactional
    public void generateDataByInputdate(String inputdate){
        int n = exportBusinessProjMapper.insertAllByInputdate(inputdate);
        logger.debug("{} records deleted", n);
    }

    /**
     * 1、先删除指定日期数据（保证为新数据）
     * 2、再插入指定日期数据
     * @param inputdate
     * @return
     */
    @Override
    @Transactional
    public void regenerateDataByInputdate(String inputdate) {
        removeDataByInputdate(inputdate);
        generateDataByInputdate(inputdate);
    }

}
