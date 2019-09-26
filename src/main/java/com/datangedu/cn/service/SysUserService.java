package com.datangedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.SysUser;

public interface SysUserService {

	public List<SysUser> getSystemUserById(String id);

	public List<SysUser> getUserListById(String id);

	public int setUserRegister(HttpServletRequest request);

	public int setUserDelete(HttpServletRequest request);

}
