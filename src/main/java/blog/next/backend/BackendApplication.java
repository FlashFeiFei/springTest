package blog.next.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.module.Configuration;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        //1. 得到ioc容器
        ConfigurableApplicationContext run = SpringApplication.run(BackendApplication.class);
        //2. 查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names){
            System.out.println(name);
        }
    }

}
