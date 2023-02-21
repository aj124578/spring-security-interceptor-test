package shop.mtcoding.springsecu.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import shop.mtcoding.springsecu.interceptor.CustomInterceptor;

@Configuration
public class SpringSecurityConfig implements WebMvcConfigurer {

    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor())
                .addPathPatterns("/user/main") 
                .excludePathPatterns("/login", "/join"); 

    }
}