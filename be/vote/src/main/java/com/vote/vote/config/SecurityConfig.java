package com.vote.vote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
@Configuration
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	public LoginFailConfig loginFail;

    @Override  
	protected void configure(HttpSecurity http) throws Exception{   
		// 0 : 일반 사용자 , 1: 관리자,  2: 매니저 
		http
		.authorizeRequests()    
			.antMatchers("/auth","/oauth2/**","/").permitAll()
			.antMatchers("/css/**").permitAll() //프론트 권한
			.antMatchers("/vendor/**").permitAll() //프론트 권한
			.antMatchers("/js/**").permitAll() // 프론트 권한
			.antMatchers("/img/**").permitAll() // 프론트 권한
			.antMatchers("/").permitAll() // 모든 권한을 줌.=로그인 필요 없음.     
			.antMatchers("/auth/**").permitAll()

			// 블록체인 투표
			.antMatchers(HttpMethod.GET,"/vote").permitAll()//  투표페이지 
			.antMatchers("/vote/program/axios").permitAll()// 투표 index (투표목록)
			.antMatchers("/vote/programAndPop/axios").hasAnyAuthority("2")// 프로그램 목록(투표목록)			
			.antMatchers(HttpMethod.GET,"/vote/axios").permitAll()//  투표페이지 
			.antMatchers("/vote/axios/**").hasAnyAuthority("0","1","2","3")//투표 정보..
			.antMatchers("/vote/{voteId}/**").hasAnyAuthority("0","1","2","3")
			.antMatchers("/vote/result/**").hasAnyAuthority("0","1","2","3")
			.antMatchers("/vote/create/**").hasAnyAuthority("1","2")// 투표 생성뷰 vote/{} 이거 때문에 작동 안하는 듯.
			.antMatchers(HttpMethod.POST,"/vote").hasAnyAuthority("1","2")// 투표 생성
			.antMatchers(HttpMethod.DELETE,"/vote/*/").hasAnyAuthority("1","2")// 투표 삭제


			//방청권
			.antMatchers("/audience/mlist/**").hasAnyAuthority("0","1","2")
			.antMatchers("/audience/mread/**").hasAnyAuthority("0","1","2")
			.antMatchers("/audience/update/**").hasAnyAuthority("0","1","2")
			.antMatchers("/audience/showList").hasAnyAuthority("0","1","2")
			.antMatchers("/audience/showResult").hasAnyAuthority("0","1","2")
			.antMatchers("/audience/delete/**").hasAnyAuthority("0","1","2")
			.antMatchers("/audience/list/**").permitAll()
			.antMatchers("/introduce/read/**").permitAll()
			.antMatchers("/introduce/apply/**").permitAll()

			//채팅방
			.antMatchers("/soket.io-client").permitAll()

			
			//오디션
			.antMatchers("/userInfo/**").permitAll()
			// .antMatchers("/userInfo/hoo").hasAnyAuthority("1","2")
			// .antMatchers("/userInfo/hoo/**").hasAnyAuthority("1","2")

			// 소개 
			.antMatchers("/introduce/**").permitAll()

			// 쇼핑몰
			.antMatchers("/shop/**").permitAll()//  쇼핑몰
			.antMatchers("/shop/create").hasAnyAuthority("1","2")// 상품 생성
			
			//핫클립
			.antMatchers("/hotclib/upload").hasAnyAuthority("2")	//등록
			.antMatchers("/hotclib/update/**").hasAnyAuthority("2")	//수정
			.antMatchers("/hotclib/delete/**").hasAnyAuthority("2") //삭제
			

			// .antMatchers("/vote/axios").permitAll()
			// .antMatchers("/vote/axios/**").hasRole("USER")
			// .antMatchers("/vote/").hasRole("USER")
			.antMatchers("/uploads/**").permitAll()
			.antMatchers("/auth/oauth2/**").permitAll()
			
			// .antMatchers("/posts/**").hasRole("USER")
			.antMatchers("/home").hasRole("USER")
			//.antMatchers("/").hasAnyRole("USER","ADMIN")
			// user 권한만 접근 가능.     
			.antMatchers("/logout").permitAll()     
			.anyRequest().authenticated() // 로그인 체크함.     
			.and()    
		.formLogin()     
			.loginPage("/auth") // 이 줄을 지우면 스프링이 제공하는 폼이 출력됨.     
			.permitAll()     
			.successHandler(successHandler())     
			.failureHandler(loginFail)
			.and()    
		.logout()    
			.logoutUrl("/logout")    
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)    
			.and()   
		.csrf() 
			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())    
			.and();


		// .oauth2Login() 
		// 	.userInfoEndpoint()
		// 	.userService(new CustomOAuth2UserService()) // 네이버 USER INFO의 응답을 처리하기 위한 설정 
		// 	.and() 
		// .defaultSuccessUrl("/") 
		// 	.failureUrl("/") 
		// 	.and() 
		// .exceptionHandling() 
		// .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/auth"));

			
		// .oauth2Login();
		// oauth2Login -> oauth2Login.userInfoEndpoint(
			// userInfoEndpoint -> userInfoEndpoint.customUserType(KakaoOAuth2User.class, "kakao"))
		// 	.loginPage("/auth") // 이 줄을 지우면 스프링이 제공하는 폼이 출력됨.     
		// 	.permitAll()     
		// 	.successHandler(successHandler())
		// 	.and();  
	} 

	// @Bean
	// public ClientRegistrationRepository clientRegistrationRepository(
	// 	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	// 	String kakaoClientId,
	// 	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	// 	String kakaoClientSecret){
	// 		List<ClientRegistration> registrations = new ArrayList<>();
	// 		registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
	// 		.clientId(kakaoClientId)
	// 		.clientSecret(kakaoClientSecret)
	// 		.jwkSetUri("temp")
	// 		.build());

	// 		System.out.println("------------clientRegistrationRepository-------------");
	// 		return new InMemoryClientRegistrationRepository(registrations);
	// 	}

	// @Bean
	// public ClientRegistrationRepository clientRegistrationRepository(
	// 	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	// 	String kakaoClientId,
	// 	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	// 	String kakaoClientSecret){
	// 		List<ClientRegistration> registrations = new ArrayList<>();
	// 		registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
	// 		.clientId(kakaoClientId)
	// 		.clientSecret(kakaoClientSecret)
	// 		.jwkSetUri("temp")
	// 		.build());

	// 		System.out.println("------------clientRegistrationRepository-------------");
	// 		return new InMemoryClientRegistrationRepository(registrations);
	// 	}

	// @Bean
    // public ClientRegistrationRepository clientRegistrationRepository(
    //         OAuth2ClientProperties oAuth2ClientProperties,
    //         @Value("${spring.security.oauth2.client.registration.kakao.client-id}") String kakaoClientId,
    //         @Value("${spring.security.oauth2.client.registration.kakao.client-secret}") String kakaoClientSecret)
    //         // @Value("${custom.oauth2.naver.client-id}") String naverClientId,
	// 		// @Value("${custom.oauth2.naver.client-secret}") String naverClientSecret) 
	// 		{

	// 		List<ClientRegistration> registrations = new ArrayList<>();
	// 		registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
	// 		.clientId(kakaoClientId)
	// 		.clientSecret(kakaoClientSecret)
	// 		.jwkSetUri("temp")
	// 		.build());	
			
			
        // List<ClientRegistration> registrations = oAuth2ClientProperties
        //         .getRegistration().keySet().stream()
        //         .map(client -> getRegistration(oAuth2ClientProperties, client))
        //         .filter(Objects::nonNull)
        //         .collect(Collectors.toList());

        // registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
        //             .clientId(kakaoClientId)
        //             .clientSecret(kakaoClientSecret)
        //             .jwkSetUri("temp")
        //             .build());

        // registrations.add(CustomOAuth2Provider.NAVER.getBuilder("naver")
        //         .clientId(naverClientId)
        //         .clientSecret(naverClientSecret)
        //         .jwkSetUri("temp")
        //         .build());
        // return new InMemoryClientRegistrationRepository(registrations);
    // }
// 	@Bean
// 	public RestTemplate restTemplate() {
//     return new RestTemplate();
// }


	public AuthenticationSuccessHandler successHandler() {
		SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
		// System.out.println("성공");
		
		return handler;
	}
}