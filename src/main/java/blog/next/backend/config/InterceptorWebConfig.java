package blog.next.backend.config;

import blog.next.backend.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//拦截器配置
@Configuration
public class InterceptorWebConfig implements WebMvcConfigurer {
    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/interception/**")
                //放行
                .excludePathPatterns("/","/static/**")
                .excludePathPatterns("/interception/login");
    }
}
