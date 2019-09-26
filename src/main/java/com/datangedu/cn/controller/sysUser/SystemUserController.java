package com.datangedu.cn.controller.sysUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datangedu.cn.model.sysUser.SysUser;
import com.datangedu.cn.service.SysUserService;

@Controller
@RequestMapping("/sysuser")
public class SystemUserController {
	@Resource
	SysUserService sysUserService;
	@RequestMapping("/sysuserlist")
	public String getSystemUserById(String id,Map <String,Object> map) {
		List<SysUser> sysUserList = sysUserService.getSystemUserById(id);
		map.put("sysUserList", sysUserList);
		return "userlist";
	}
	@ResponseBody
	@RequestMapping(value = "/getuserlist",method = RequestMethod.GET)
	public Map<String,Object> SysUserList(HttpServletRequest request){
		String  id = request.getParameter("id");
		Map<String,Object> map = new HashMap<String,Object>();
		List<SysUser> userList = sysUserService.getUserListById(id);
		map.put("userList", userList);
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "/userregister",method = RequestMethod.POST)
	public Map<String,Object> userRegister(HttpServletRequest request){

		Map<String,Object> map = new HashMap<String,Object>();
		int userList = sysUserService.setUserRegister(request);
		 if(userList == 0) {
			map.put("msg", "id不能为空！");
		}else if(userList == 4) {
			map.put("msg", "username不能为空！");
		}else if(userList == 2) {
			map.put("msg", "密码长度小于6位，请重新输入！");
		}else if(userList == 3) {
			map.put("msg","密码长度大于12位，请重新输入！");
		}
		else {
			map.put("msg", "恭喜注册成功！");
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "/userdelete",method = RequestMethod.POST)
	public Map<String,Object> userDelete(HttpServletRequest request){

		Map<String,Object> map = new HashMap<String,Object>();
		int userList = sysUserService.setUserDelete(request);
		if(userList == 2) {
			map.put("msg", "密码长度小于6位，请重新输入！");
		}
		map.put("msg", "恭喜注册成功！");
		map.put("code", 1);
		return map;
	}

}
