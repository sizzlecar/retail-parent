package com.voyageone.retail.biz.process.dao;

import com.voyageone.retail.biz.process.model.RbLog;
import org.springframework.stereotype.Repository;

@Repository
public interface RbLogMapperExt extends RbLogMapper{


    int delete(RbLog rbLog);
}