package com.noname.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Created by noname on 2019/4/10.
 */
@Configuration
@EnableWebSecurity          //此注解必须
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider provider;
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailHander;

    /**
     * 此方式是登录成功与失败都会进行页面跳转，如果需要返回指定的json请使用下面的方式
     * .loginPage("/login")此句表示登录时的跳转页面，所以在controller申明一个login的方法，只要在非登录状态下访问其他页面都会被拦截到该页面
     * .loginProcessingUrl("/login/form") 此句表示数据验证的方法，也是在controller申明
     * .failureUrl("/login-error")是表明登录失败之后调用的方法，在controller中申明，如果是希望登录返回的是json字符串，请查看注释部分
     *
     * @param http
     * @throws Exception
     */
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.formLogin().loginPage("/login").loginProcessingUrl("/login/form").failureUrl("/login-error").permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
//                .and()
//                .authorizeRequests().anyRequest().authenticated()
//                .and().csrf().disable();
//
//    }


    /**
     * 此方式是登录成功与失败都会返回指定的json格式，如果需要页面跳转请使用上面的方式
     * .loginPage("/login")此句表示登录时的跳转页面，所以在controller申明一个login的方法，只要在非登录状态下访问其他页面都会被拦截到该页面
     * .loginProcessingUrl("/login/form") 此句表示数据验证的方法，也是在controller申明
     * .successHandler(myAuthenticationSuccessHandler) 返回登录成功的指定的json格式，该格式在自定义的myAuthenticationSuccessHandler决定
     * .failureHandler(myAuthenticationFailHander)  返回登录失败的指定的json格式，该格式在自定义的myAuthenticationFailHander决定
     *
     * @param http
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http
                .formLogin().loginPage("/login").loginProcessingUrl("/login/form")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHander)
                .permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(provider);
//        被注释的方法部分是用来官方测试的代码，因为其账号密码是写死的
//        auth
//                .inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("USER")
//                .and()
//                .withUser("test").password("test123").roles("ADMIN");
    }


}

