package com.tl.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro配置
 * 
 * @author Administrator
 *
 */
@Configuration
public class ShiroConfig {

	/**
	 * 创建ShiroFilterFactorBen
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(
			@Qualifier("suSecurityManager") DefaultWebSecurityManager securityManager) {

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 添加shiro内置过滤器
		/**
		 * Shiro内置过滤器，可以实现权限相关拦截
		 * 		常用过滤器：
		 * 			anon：无需认证（登录）可以访问
		 * 			authc：必须认证才可以访问
		 * 			user：如果使用rememberMe的功能可以直接访问
		 * 			perms：改资源必须得到资源权限才可以访问
		 * 			role：该资源必须得到角色权限才可以访问
		 * 
		 */
		Map<String, String> filterMap = new LinkedHashMap<String,String>();
		/*filterMap.put("/add", "authc");
		filterMap.put("/update", "authc");*/
		
		filterMap.put("/test1", "anon");
		filterMap.put("/login", "anon");
		
		filterMap.put("/**", "authc");
		
		//修改调整的登录页面
		shiroFilterFactoryBean.setLoginUrl("/toLogin");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		
		return shiroFilterFactoryBean;

	}

	/**
	 * 创建DefaultWebSecurityManager
	 */
	@Bean(name = "suSecurityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager suSecurityManager = new DefaultWebSecurityManager();
		// 关联Realm
		suSecurityManager.setRealm(userRealm);
		return suSecurityManager;
	}

	/**
	 * 创建Realm
	 * 
	 * @return
	 */
	@Bean(name = "userRealm")
	public UserRealm getRealm() {
		return new UserRealm();
	}
}
