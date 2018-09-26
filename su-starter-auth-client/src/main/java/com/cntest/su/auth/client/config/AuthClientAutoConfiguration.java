package com.cntest.su.auth.client.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cntest.su.auth.client.AuthRealm;
import com.cntest.su.auth.client.ClientAuthService;
import com.cntest.su.auth.client.JwtFilter;
import com.cntest.su.auth.client.handle.AuthClientErrorController;
import com.cntest.su.auth.config.AuthProperties;

/**
 * 组件配置。
 */
@Configuration
public class AuthClientAutoConfiguration {
  /**
   * 配置认证服务异常处理组件。
   * 
   * @return 返回认证服务异常处理组件。
   */
  @Bean
  public AuthClientErrorController authClientErrorController() {
    return new AuthClientErrorController();
  }

  /**
   * 配置认证服务组件。
   * 
   * @return 返回认证服务组件。
   */
  @Bean
  public ClientAuthService authService(AuthProperties prop) {
    return new ClientAuthService();
  }

  /**
   * 配置认证组件。
   * 
   * @param authService 认证服务
   * @return 返回认证组件。
   */
  @Bean
  public AuthRealm authRealm() {
    return new AuthRealm();
  }

  /**
   * 配置SecurityManager组件。
   * 
   * @param authRealm 认证组件
   * @return 返回SecurityManager组件。
   */
  @Bean
  public SecurityManager securityManager(AuthRealm authRealm) {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(authRealm);

    DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
    DefaultSessionStorageEvaluator defaultSessionStorageEvaluator =
        new DefaultSessionStorageEvaluator();
    defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
    subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
    securityManager.setSubjectDAO(subjectDAO);

    return securityManager;
  }

  /**
   * 配置DefaultAdvisorAutoProxyCreator组件。
   * 
   * @return 返回DefaultAdvisorAutoProxyCreator组件。
   */
  @Bean
  public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator =
        new DefaultAdvisorAutoProxyCreator();
    defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
    return defaultAdvisorAutoProxyCreator;
  }

  /**
   * 配置AuthorizationAttributeSourceAdvisor组件。
   * 
   * @param securityManager SecurityManager组件
   * @return 返回AuthorizationAttributeSourceAdvisor组件。
   */
  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
      SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager);
    return advisor;
  }

  /**
   * 配置LifecycleBeanPostProcessor组件。
   * 
   * @return 返回LifecycleBeanPostProcessor组件。
   */
  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  /**
   * 配置ShiroFilter组件。
   * 
   * @return 返回ShiroFilter组件。
   */
  @Bean
  public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
    ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();
    factory.setSecurityManager(securityManager);
    factory.setUnauthorizedUrl("/401");

    Map<String, Filter> filterMap = new HashMap<>();
    filterMap.put("jwt", new JwtFilter());
    factory.setFilters(filterMap);

    Map<String, String> filterRuleMap = new HashMap<>();
    filterRuleMap.put("/**", "jwt");
    filterRuleMap.put("/401", "anon");
    factory.setFilterChainDefinitionMap(filterRuleMap);

    return factory;
  }
}
