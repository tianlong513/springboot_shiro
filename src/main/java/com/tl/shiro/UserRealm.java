package com.tl.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm extends AuthorizingRealm{
	
	/**
	 * 执行授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行认证授权");
		return null;
	}
	
	/**
	 * 执行认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("执行认证逻辑");
		//假数据
		String name = "admin";
		String password = "123456";
		
		//编写shiro判断逻辑，判断用户密码
		//判断用户名
		UsernamePasswordToken getToken = (UsernamePasswordToken)token;
		if(!getToken.getUsername().equals(name)){
			//用户名不存在
			return null; //shiro底层会抛出UnknowAccountException
		}
		//判断密码
		return new SimpleAuthenticationInfo("",password,"");
	}
	
}
