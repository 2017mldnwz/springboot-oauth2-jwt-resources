package com.dy.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Order(1)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录
                .loginProcessingUrl("/login") // 处理表单登录 URL
            .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/index").permitAll()
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
            .and()
                .csrf().disable();
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
