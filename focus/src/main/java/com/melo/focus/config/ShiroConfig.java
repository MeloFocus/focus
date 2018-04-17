package com.melo.focus.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.melo.focus.realm.MyShiroRealm;

@Configuration
public class ShiroConfig {
	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
	 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 *
	 * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 3、部分过滤器可指定参数，如perms，roles
	 *
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		//访问的是后端url地址为 /login的接口
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/ajaxLogin", "anon");
		filterChainDefinitionMap.put("/focus/userlogin", "anon");
		filterChainDefinitionMap.put("/swagger-ui.html#", "anon");
		
		filterChainDefinitionMap.put("/hello", "anon");
		
		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了,加上这个会导致302，请求重置，暂不明白原因
		 //filterChainDefinitionMap.put("/logout", "logout");
		//配置某个url需要某个权限码
		//filterChainDefinitionMap.put("/hello", "perms[how_are_you]");
		
		// 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问;user:remember me的可以访问-->
		filterChainDefinitionMap.put("/fine", "user");  
		filterChainDefinitionMap.put("/focus/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		System.out.println("Shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}
	
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(myShiroRealm());
		
		//将缓存注入安全管理器，就不会反复执行  realm的授权方法了；只要实现了shiro的cache接口、CacheManager接口就可以用来注入安全管理器
		//shiro自带的一个内存缓存，本质是hashmap，MemoryConstrainedCacheManager()，试验没问题，非常轻，简单的登录用这个
		securityManager.setCacheManager(new MemoryConstrainedCacheManager());
		
		//用了redis缓存注入安全管理器，会报一个序列化失败的错误，推测是new SimpleAuthenticationInfo 时 user对象无法序列化，加上序列化就好了
		//securityManager.setCacheManager(cacheManager());
		
		//将session托管给redis，nigix试验分布式，确实 做到了session共享
		//securityManager.setSessionManager(SessionManager());
		
		  //注入记住我管理器;  
	    securityManager.setRememberMeManager(rememberMeManager()); 
		return securityManager;
	}
	/**
	 * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
	 * @return
	 */
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}
	
	/**
	 * 配置shiro redisManager
	 * 网上的一个 shiro-redis 插件，实现了shiro的cache接口、CacheManager接口就
	 * @return
	 */
	@Bean
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost("localhost");
		redisManager.setPort(6379);
		redisManager.setExpire(18000);// 配置过期时间
		// redisManager.setTimeout(timeout);
		// redisManager.setPassword(password);
		return redisManager;
	}
	/**
	 * cacheManager 缓存 redis实现
	 * 网上的一个 shiro-redis 插件
	 * @return
	 */
	@Bean
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}
	

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis
	 */
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}
	/**
	 * shiro session的管理
	 */
	public DefaultWebSessionManager SessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}
	
	@Bean  
	public SimpleCookie rememberMeCookie(){  
	  
	       System.out.println("ShiroConfiguration.rememberMeCookie()");  
	       //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe  
	       SimpleCookie simpleCookie = new SimpleCookie("rememberMe");  
	       //<!-- 记住我cookie生效时间30天 ,单位秒;-->  
	       simpleCookie.setMaxAge(259200);
	       simpleCookie.setHttpOnly(true);
	       return simpleCookie;  
	}  
	/**  
	  * cookie管理对象;  
	  * @return  
	  */  
	@Bean  
	public CookieRememberMeManager rememberMeManager(){  
	  
	       System.out.println("ShiroConfiguration.rememberMeManager()");  
	       CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();  
	      // cookieRememberMeManager.setCipherKey(org.apache.shiro.codec.Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA=="));
	       cookieRememberMeManager.setCookie(rememberMeCookie());  
	       return cookieRememberMeManager;  
	}  
	
	  /**
     * Shiro生命周期处理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
	
}