package com.voyageone.retail.biz.process.service;

import com.voyageone.retail.biz.process.dao.RbLogMapperExt;
import com.voyageone.retail.biz.process.model.RbLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RbLogService {

    private final RbLogMapperExt rbLogMapper;

    public RbLogService(RbLogMapperExt rbLogMapper) {
        this.rbLogMapper = rbLogMapper;
    }


    public int insertLog(RbLog rbLog){
        return rbLogMapper.insert(rbLog);
    }

    public int deleteLog(RbLog rbLog){
        return rbLogMapper.delete(rbLog);
    }
}
