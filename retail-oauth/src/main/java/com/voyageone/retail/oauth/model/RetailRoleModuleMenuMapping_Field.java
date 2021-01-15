package com.voyageone.retail.oauth.model;

import com.voyageone.retail.oauth.model.bean.RetailBaseModel;

public abstract class RetailRoleModuleMenuMapping_Field extends RetailBaseModel<Integer> {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column retail_role_module_menu_mapping.id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column retail_role_module_menu_mapping.group_id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    private Integer groupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column retail_role_module_menu_mapping.role_id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    private Integer roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column retail_role_module_menu_mapping.module_id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    private Integer moduleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column retail_role_module_menu_mapping.menu_id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    private Integer menuId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column retail_role_module_menu_mapping.status
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    private Byte status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column retail_role_module_menu_mapping.id
     *
     * @return the value of retail_role_module_menu_mapping.id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column retail_role_module_menu_mapping.id
     *
     * @param id the value for retail_role_module_menu_mapping.id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column retail_role_module_menu_mapping.group_id
     *
     * @return the value of retail_role_module_menu_mapping.group_id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column retail_role_module_menu_mapping.group_id
     *
     * @param groupId the value for retail_role_module_menu_mapping.group_id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column retail_role_module_menu_mapping.role_id
     *
     * @return the value of retail_role_module_menu_mapping.role_id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column retail_role_module_menu_mapping.role_id
     *
     * @param roleId the value for retail_role_module_menu_mapping.role_id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column retail_role_module_menu_mapping.module_id
     *
     * @return the value of retail_role_module_menu_mapping.module_id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    public Integer getModuleId() {
        return moduleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column retail_role_module_menu_mapping.module_id
     *
     * @param moduleId the value for retail_role_module_menu_mapping.module_id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column retail_role_module_menu_mapping.menu_id
     *
     * @return the value of retail_role_module_menu_mapping.menu_id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column retail_role_module_menu_mapping.menu_id
     *
     * @param menuId the value for retail_role_module_menu_mapping.menu_id
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column retail_role_module_menu_mapping.status
     *
     * @return the value of retail_role_module_menu_mapping.status
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column retail_role_module_menu_mapping.status
     *
     * @param status the value for retail_role_module_menu_mapping.status
     *
     * @mbg.generated Fri Jan 15 16:28:24 CST 2021
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}