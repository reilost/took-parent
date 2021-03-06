package com.tooklili.admin.web.controller.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tooklili.admin.web.interceptor.annotation.RequiresPermissions;
import com.tooklili.admin.web.interceptor.annotation.SameUrlData;
import com.tooklili.admin.web.resolver.annotation.CurrentUser;
import com.tooklili.enums.admin.UserDelEnum;
import com.tooklili.model.admin.SysUser;
import com.tooklili.service.biz.intf.admin.system.UserService;
import com.tooklili.util.result.BaseResult;
import com.tooklili.util.result.PageResult;

/**
 * 用户控制器
 * @author shuai.ding
 *
 * @date 2017年8月27日下午4:54:00
 */
@Controller
@RequestMapping("/system/user")
public class UserController{
	
	@Resource
	private UserService userService;
	
	/**
	 * 主页
	 * @author shuai.ding
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@RequiresPermissions("system:user:view")
    public String main() {
		return "system/user";
    }
	

	/**
	 * 用户列表查询
	 * @author shuai.ding
	 * @param page        当前页
	 * @param rows        页面大小
	 * @return
	 */
	@RequestMapping(value = "/userList")
	@ResponseBody
	@RequiresPermissions("system:user:view")
	public PageResult<SysUser> userList(SysUser sysUser, Integer page,Integer rows){
		//展示非逻辑删除的用户
		if(sysUser==null){
			sysUser=new SysUser();			
		}
		sysUser.setUserDeleted(UserDelEnum.USER_NO_DEL.getCode());
		return userService.findUsers(sysUser, page,rows);
	}
	

	/**
	 * 添加用户
	 * @author shuai.ding
	 * @param sysUser   用户实体
	 * @param role      角色id
	 * @return
	 */
	@RequestMapping(value = "/addUser")
	@ResponseBody
	@RequiresPermissions("system:user:add")
	public BaseResult addUser(SysUser sysUser,Long role){
		return  userService.addUserAndRole(sysUser, role);
	}
	

	/**
	 * 修改用户
	 * @author shuai.ding
	 * @param sysUser    用户实体
	 * @param role       角色id
	 * @return
	 */
	@RequestMapping(value = "/editUser")
	@ResponseBody
	@RequiresPermissions("system:user:modify")
	public BaseResult editUser(SysUser sysUser,Long role){
		return userService.editUserAndRole(sysUser, role);
	}
	

	/**
	 * 逻辑删除用户
	 * @author shuai.ding
	 * @param id      用户主键
	 * @return
	 */
	@RequestMapping(value = "/logicDelUser/{id}")
	@ResponseBody
	@RequiresPermissions("system:user:delete")
	public BaseResult logicDelUser(@PathVariable Long id){
		return userService.logicDelUser(id);
	}
	
	/**
	 * 重置用户密码，密码默认为123
	 * @author shuai.ding
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/defaultUserPwd/{id}")
	@ResponseBody
	public BaseResult defaultUserPwd(@PathVariable Long id){
		return userService.defaultUserPwd(id);
	}
	
	/**
	 * 判断用户名是否重复
	 * true 不重复  false 重复
	 * @author shuai.ding
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "userNameIfNotRepeate")
	@ResponseBody
	public boolean userNameIfNotRepeate(String userName){
		if(StringUtils.isEmpty(userName)){
			return true;
		}
		
		SysUser user = new SysUser();
		user.setUserName(userName);
		user.setUserDeleted(UserDelEnum.USER_NO_DEL.getCode());
		List<SysUser> users = userService.findUser(user).getData();
		if(users!=null && users.size()>0){
			return false;
		}		
		return true;
	}
	
	
	/**
	 * 修改密码
	 * @author shuai.ding
	 * @param oldPwd      原密码(md5加密后)
	 * @param newPwd      新密码(md5加密后)
	 * @param confirmPwd  确认密码(md5加密后)
	 * @return
	 */
	@RequestMapping(value = "modifyPassword")
	@ResponseBody
	@SameUrlData
	public BaseResult modifyPassword(String oldPwd,String newPwd,String confirmPwd,@CurrentUser SysUser user){
		return userService.modifyPassword(user.getId(), oldPwd, newPwd, confirmPwd);
	}

}
