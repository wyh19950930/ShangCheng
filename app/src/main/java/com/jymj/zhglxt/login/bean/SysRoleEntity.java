package com.jymj.zhglxt.login.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:27:38
 */
public class SysRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建者ID
	 */
	private Long createUserId;
	
	private List<Long> menuIdList;
	private List<Long> xzqIdList;
	private List<Long> layerIdList;
	private List<Long> departIdList;
	/**
	 * 创建时间
	 */
	private String createTime;
	private Integer type;//
	private String typeText;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		if (roleName==null){
			roleName = "";
		}
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark==null?"":remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public List<Long> getMenuIdList() {
		return menuIdList==null?new ArrayList<>():menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public List<Long> getXzqIdList() {
		return xzqIdList==null?new ArrayList<>():xzqIdList;
	}

	public void setXzqIdList(List<Long> xzqIdList) {
		this.xzqIdList = xzqIdList;
	}

	public List<Long> getLayerIdList() {
		return layerIdList==null?new ArrayList<>():layerIdList;
	}

	public void setLayerIdList(List<Long> layerIdList) {
		this.layerIdList = layerIdList;
	}

	public List<Long> getDepartIdList() {
		return departIdList==null?new ArrayList<>():departIdList;
	}

	public void setDepartIdList(List<Long> departIdList) {
		this.departIdList = departIdList;
	}

	public String getCreateTime() {
		return createTime==null?"":createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeText() {
		return typeText==null?"":typeText;
	}

	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}
}
