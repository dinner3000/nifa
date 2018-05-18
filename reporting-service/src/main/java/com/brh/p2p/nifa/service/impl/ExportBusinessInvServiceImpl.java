package com.brh.p2p.nifa.service.impl;

import com.brh.p2p.nifa.data.entity.ExportBusinessInvEntity;
import com.brh.p2p.nifa.data.repository.ExportBusinessInvMapper;
import com.brh.p2p.nifa.service.ExportBusinessService;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ExportBusinessInvService")
public class ExportBusinessInvServiceImpl implements ExportBusinessService<ExportBusinessInvEntity> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ExportBusinessInvMapper exportBusinessInvMapper;

    @Autowired
    private Configuration configuration;

    @Override
    public List<ExportBusinessInvEntity> findAllByInputdate(String inputdate) {

        List<ExportBusinessInvEntity> records = exportBusinessInvMapper.selectAllByInputdate(inputdate);
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
        int n = exportBusinessInvMapper.deleteAllByInpudate(inputdate);
        logger.debug("{} records deleted", n);
    }

    @Override
    @Transactional
    public void clearAll(){
        int n = exportBusinessInvMapper.deleteAll();
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
        int n = exportBusinessInvMapper.insertAllByInputdate(inputdate);
        logger.debug("{} records inserted", n);
    }

    @Override
    @Transactional
    public void generateAll(String inputdate) {
        int n = exportBusinessInvMapper.insertAll(inputdate);
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
