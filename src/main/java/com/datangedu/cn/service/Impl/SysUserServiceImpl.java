package com.datangedu.cn.service.Impl;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.SysUserMapper;
import com.datangedu.cn.model.sysUser.SysUser;
import com.datangedu.cn.model.sysUser.SysUserExample;
import com.datangedu.cn.service.SysUserService;
@Service
public class SysUserServiceImpl implements SysUserService {
	@Resource
	SysUserMapper sysUserMapper;

	@Override
	public List<SysUser>  getSystemUserById(String id){
		System.out.println("查询用户：" + id);
		
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria critera = sysUserExample.createCriteria();
		critera.andIdEqualTo(id);
		return sysUserMapper.selectByExample(sysUserExample);
		
	}
	@Override
	public List<SysUser> getUserListById(String id){
		SysUserExample sysUserExample = new SysUserExample();
	//	SysUserExample.Criteria criteria = sysUserExample.createCriteria();
	//	criteria.andIdEqualTo(id);
		  //按照id排序
		  sysUserExample.setOrderByClause("id"); 
		  //消除重复行
		  sysUserExample.setDistinct(true);
		  sysUserExample.setPageStart(0);
		  sysUserExample.setPageSize(2); 
		  
		 
		return sysUserMapper.selectByExample(sysUserExample);
	}
	@Override
	public int setUserRegister(HttpServletRequest request) {
		if(request.getParameter("id").length() == 0) {
			return 0;
		}else if(request.getParameter("username").length()  == 0) {
			return 4;
		}
		else if(request.getParameter("password").length() < 6) {
			return 2;
		}else if(request.getParameter("password").length() > 12) {
			return 3;
		}
		SysUser sysUser = new SysUser();
		sysUser.setId(request.getParameter("id"));
		sysUser.setUsername(request.getParameter("username"));
		sysUser.setPassword(request.getParameter("password"));

		
		return sysUserMapper.insert(sysUser);
	}
	@Override
	public int setUserDelete(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria critera = sysUserExample.createCriteria();
		critera.andIdEqualTo(id);
		return sysUserMapper.deleteByExample(sysUserExample);
	}
}
