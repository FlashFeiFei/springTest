package blog.next.backend.config;

import blog.next.backend.entity.user.UserEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * 1. 配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单例实例
 * 2. 配置类本身也是组件
 * 3. proxyBeanMethods: 代理bean的方法,默认是true，多次调用获取bean的方法或者是依赖注入，都是ioc容器中的单例那个实例，
 * 如果是设置为 false,每次都是生成一个新的实例
 * @Configuration(proxyBeanMethods=true)
 *
 * 4. @Import({User.class,DBHelper.class}) 给ioc容器中自动创建这两个类型的组件,默认组件的名字就是全类名
 * 5. @ImportResource("classpath:beans.xml") 导入spring的配置文件，给ioc容器注入组件
 */
@Configuration
public class ApplicationConfig {

    @Bean
    //每次请求都是新的
    @Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserEntity queryUserEntity(){
        UserEntity userEntity =  new UserEntity();

        System.out.println("UserEntity地址123123");
        System.out.println( userEntity);
        System.out.println("UserEntity地址123123");
        return userEntity;
    }


    //存在某个bean的时候，才注入Ly bean
    @ConditionalOnBean(name = "queryUserEntity")
    @Bean
    public String Ly(){
        return "Ly";
    }


    //让spring boot支持矩阵变量传参
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            //启动矩阵变量传参
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }
        };
    }
}
