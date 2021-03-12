package blog.next.backend.config;

import blog.next.backend.entity.user.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class ApplicationConfig {

    @Bean
    //每次请求都是新的
    @Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserEntity queryUserEntity(){ return new UserEntity();
    }
}
