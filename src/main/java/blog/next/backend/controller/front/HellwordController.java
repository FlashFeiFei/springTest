package blog.next.backend.controller.front;


import blog.next.backend.config.MyConfig;
import blog.next.backend.entity.user.UserEntity;
import blog.next.backend.middleware.annotation.LoginAnnotation;
import blog.next.backend.middleware.annotation.MyAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

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

    private Logger logger = LoggerFactory.getLogger(HellwordController.class);


    @GetMapping("log")
    public String log(){

        //日志的级别
        //由低到高
        //springBoot默认是info级别的，会输出当前级别和比当前级别更高的日志
        logger.trace("这是trace日志....");
        logger.debug("这是debug日志....");
        logger.info("这是info日志，slf4j抽象层和logBack实现层，以及各种中间包，实现的统一日志处理");
        logger.warn("这是warn日志....");
        logger.error("这是error日志.....");
        return "日志";
    }

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
        System.out.println("ASDFASDF");

        return "hi:" + userEntity.getName() + " 你也太厉害了吧";
    }

    @GetMapping(value = {
            "/listToMap",
            "/listMapChange"
    })
    public Map<Integer, UserEntity> listToMap() {

        List<UserEntity> list = new ArrayList<>();
        list.add(new UserEntity(1, "ly"));
        list.add(new UserEntity(2, "laughingZhu"));
        list.add(new UserEntity(3, "365"));
        Map<Integer, UserEntity> map1 = list.stream().collect(Collectors.toMap(UserEntity::getId, itemUserEntity->itemUserEntity));
        System.out.println("Result 1 : " + map1);
        System.out.println(map1.containsKey(1));
        return map1;
    }

    @GetMapping("/listToGroupMap")
    public  Map<Integer,List<UserEntity>> listToGroupMap(){
        List<UserEntity> list = new ArrayList<>();
        list.add(new UserEntity(1, "ly"));
        list.add(new UserEntity(2, "laughingZhu"));
        list.add(new UserEntity(3, "cccc"));
        list.add(new UserEntity(1, "ly1"));
        list.add(new UserEntity(2, "laughingZhu2"));
        list.add(new UserEntity(3, "cccc3"));
        //按照id分组
        return list.stream().collect(Collectors.groupingBy(UserEntity::getId));
    }
}
