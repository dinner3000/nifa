package com.brh.p2p.nifa.service;

import com.brh.p2p.nifa.data.entity.ExportBusinessProjEntity;
import com.brh.p2p.nifa.data.repository.ExportBusinessProjMapper;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ExportBusinessProjService")
public class ExportBusinessProjServiceImpl implements ExportBusinessService<ExportBusinessProjEntity> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ExportBusinessProjMapper exportBusinessProjMapper;

    @Autowired
    private Configuration configuration;

    /**
     * 不分页获取所有指定日期数据
     * @param inputdate
     * @return
     */
    @Override
    public List<ExportBusinessProjEntity> findAllByInputdate(String inputdate) {

        List<ExportBusinessProjEntity> records = exportBusinessProjMapper.selectAllByInputdate(inputdate);
        logger.debug("{} records returned", records.size());
        return records;
    }

    /**
     * 删除指定日期数据
     * @param inputdate
     * @return
     */
    @Override
    @Transactional
    public void removeAllByInputdate(String inputdate){
        int n = exportBusinessProjMapper.deleteAllByInpudate(inputdate);
        logger.debug("{} records deleted", n);
    }

    @Override
    @Transactional
    public void clearAll(){
        int n = exportBusinessProjMapper.deleteAll();
        logger.debug("{} records deleted", n);
    }

    /**
     *
     * @param inputdate
     * @return
     */
    @Override
    @Transactional
    public void generateAllByInputdate(String inputdate){
        int n = exportBusinessProjMapper.insertAllByInputdate(inputdate);
        logger.debug("{} records inserted", n);
    }

    @Override
    @Transactional
    public void generateAll(String inputdate) {
        int n = exportBusinessProjMapper.insertAll(inputdate);
        logger.debug("{} records inserted", n);
    }

    /**
     * 1、先删除指定日期数据（保证为新数据）
     * 2、再插入指定日期数据
     * @param inputdate
     * @return
     */
    @Override
    @Transactional
    public void regenerateAllByInputdate(String inputdate) {
        removeAllByInputdate(inputdate);
        generateAllByInputdate(inputdate);
    }

    @Override
    @Transactional
    public void regenerateAll(String inputdate) {
        clearAll();
        generateAll(inputdate);
    }

}
