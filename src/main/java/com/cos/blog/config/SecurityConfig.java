package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.cos.blog.config.auth.PrincipalDetailService;

@Configuration // 빈등록 (Ioc관리)
@EnableWebSecurity // 시큐리티 필터추가 = 스프링 시큐리티가 활성화 되었는데 어떤 설정을 해당 파일에서 하겠다
@EnableGlobalMethodSecurity(prePostEnabled = true)// 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

	
	
	@Bean // IoC가 된다
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	
	// 시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암화화해서 DB에 있는 해쉬랑 비교할 수 있음
	
	
	
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	http
	  .csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
	  .authorizeHttpRequests()
	    .antMatchers("/", "/auth/**", "/js/**", "/css/**",  "/image/**", "/dummy/**", "/admin/**")
		.permitAll()
		.anyRequest()
		.authenticated()
	  .and()
	    .formLogin()
	    .loginPage("/auth/loginForm")
	    .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인을 해준다.
	    .defaultSuccessUrl("/");
	return http.build();
 }

}
