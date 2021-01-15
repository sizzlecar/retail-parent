package com.voyageone.retail.oauth.dao;

import com.voyageone.retail.oauth.model.RetailGroup;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table retail_group
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table retail_group
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    int insert(RetailGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table retail_group
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    int insertSelective(RetailGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table retail_group
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    RetailGroup selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table retail_group
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    int updateByPrimaryKeySelective(RetailGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table retail_group
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    int updateByPrimaryKey(RetailGroup record);
}