package com.voyageone.retail.oauth.model.bean;

import java.util.List;
import java.util.Map;

public interface BaseMySqlDao<T> {

    List<T> selectList(T model);

    List<T> selectList(Map<String, Object> map);

    T selectOne(T model);

    T selectOne(Map<String, Object> map);

    int selectCount(T model);

    int selectCount(Map<String, Object> map);

    <K> T select(K id);

    int insert(T record);

    int insertList(List<? extends T> list);

    int update(T record);

    <K> int delete(K id);
}
