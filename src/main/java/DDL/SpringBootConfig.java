package DDL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@Configuration
public class SpringBootConfig implements WebMvcConfigurer {

    // BCryptPasswordEncoder를 Bean으로 등록
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Spring Security 설정: 로그인 화면 및 CSRF 비활성화
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin().disable().csrf().disable();
        return httpSecurity.build();
    }

    // RestTemplate을 Bean으로 등록
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // SessionInterceptor 주입
    @Autowired
    private SessionInterceptor sessionInterceptor;

    // 인터셉터 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**") // 모든 요청에 적용
                .excludePathPatterns("/",
                					"/login/**",	// 로그인 창
                					"/static/**", 	//css, js, img 등을 가져오기 위해 static 폴더 제외
                					"/regist/**", 	//회원 가입 - 회원 정보 입력 창
                					"/shop/shopList",
                					"/shop/shopDetail",
                					"/wish/**",
                					"/cart/cartInsert",
                					"/stock/**",
                					"/community/communityList",
                					"/community/communityDetail",
                					"/help/**",
                					"/userConfirm",
                					"/user/welcome"); // 제외 경로 수정
    }
}
