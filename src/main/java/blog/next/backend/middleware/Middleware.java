package blog.next.backend.middleware;

import blog.next.backend.entity.user.UserEntity;
import blog.next.backend.exception.BaseException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

//spring boot 提供的aop变成编码方式
@Aspect
@Component
public class Middleware {


    //注入userEntity
    @Autowired
    private UserEntity userEntity;

    //获取响应
//    @Autowired
//    private HttpServletResponse response;



    //中间件，判断http请求是否可以继续向下执行
    @Around("@annotation(blog.next.backend.middleware.annotation.LoginAnnotation)")
    public Object IsLogin(ProceedingJoinPoint pjp) throws Throwable {
        // start stopwatch
        System.out.println("函数处理前login中间件");
//        System.out.println("请求的参数");
//        System.out.println(request.getParameter("name"));
//        OutputStream os = response.getOutputStream();
//        os.write("中国123123".getBytes("UTF-8"));
        //中间件结束请求
//        String name;
//        name = request.getParameter("name");
//        if(name.equals("ly")){
//            throw new BaseException("自定义异常");
//        }
//        userEntity.setName(name);
//        System.out.println("userEntity的地址");
//        System.out.println(userEntity);
        Object retVal = pjp.proceed();
        System.out.println("函数处理后login中间件");
        // stop stopwatch
        return retVal;
    }

    //两个中间件测试
    @Around("@annotation(blog.next.backend.middleware.annotation.MyAnnotation)")
    public Object MyTest(ProceedingJoinPoint pjp) throws Throwable {
        // start stopwatch
        System.out.println("函数处理前myTest中间件");
        Object retVal = pjp.proceed();
        System.out.println("函数处理后myTest中间件");
        // stop stopwatch
        return retVal;
    }
}
