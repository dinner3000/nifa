package com.brh.p2p.nifa.service.impl;

import com.brh.p2p.nifa.data.entity.ExportBusinessBorEntity;
import com.brh.p2p.nifa.data.repository.ExportBusinessBorMapper;
import com.brh.p2p.nifa.service.ExportBusinessService;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ExportBusinessBorService")
public class ExportBusinessBorServiceImpl implements ExportBusinessService<ExportBusinessBorEntity> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ExportBusinessBorMapper exportBusinessBorMapper;

    @Autowired
    private Configuration configuration;

    @Override
    public List<ExportBusinessBorEntity> findAllByInputdate(String inputdate) {

        List<ExportBusinessBorEntity> records = exportBusinessBorMapper.selectAllByInputdate(inputdate);
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
        int n = exportBusinessBorMapper.deleteAllByInpudate(inputdate);
        logger.debug("{} records deleted", n);
    }

    @Override
    @Transactional
    public void clearAll(){
        int n = exportBusinessBorMapper.deleteAll();
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
        int n = exportBusinessBorMapper.insertAllByInputdate(inputdate);
        logger.debug("{} records inserted", n);
    }

    @Override
    @Transactional
    public void generateAll(String inputdate) {
        int n = exportBusinessBorMapper.insertAll(inputdate);
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
