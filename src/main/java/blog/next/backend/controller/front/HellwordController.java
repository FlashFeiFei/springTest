package blog.next.backend.controller.front;


import blog.next.backend.config.MyConfig;
import blog.next.backend.entity.user.UserEntity;
import blog.next.backend.middleware.annotation.LoginAnnotation;
import blog.next.backend.middleware.annotation.MyAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = {"/front"})
//启用配置自定义配置文件
@EnableConfigurationProperties(MyConfig.class)
public class HellwordController {

    @Autowired
    private UserEntity userEntity;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MyConfig myConfig;

    @GetMapping("/hi")
    @LoginAnnotation//添加自定义的路由中间件，利用spring提供的aop
    @MyAnnotation
    public String hi(@RequestParam String name) {

        System.out.println(this);
        System.out.println(request);
        System.out.println(userEntity);
        System.out.println("自定义配置文件");
        System.out.println(myConfig.getName());
        System.out.println(myConfig.getAge());
        System.out.println(myConfig.getDetail());

        return "hi:" + userEntity.getName() + " 你也太厉害了吧";
    }
}
