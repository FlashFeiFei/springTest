package blog.next.backend.controller.front;

import blog.next.backend.entity.user.Persion;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RequestController {

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request) {
        request.setAttribute("msg", "成功了......");
        request.setAttribute("code", 200);
        return "forward:/success";  //转发到success请求
    }

    //注入的map 、model。spring boot 会注入到HttpServletRequest中的请求域中
    @GetMapping("/params")
    public String TestParam(Map<String,Object> map, Model model, HttpServletRequest request, HttpServletResponse response){

        //map的值会最终会设置到请求域中
        map.put("hello","world666");
        //model的值最终会设置到请求域中
        model.addAttribute("world","hello663");
        request.setAttribute("message","HelloWorld");

        Cookie cookie = new Cookie("c1","v1");
        response.addCookie(cookie);

        return "forward:/success";
    }

    @GetMapping("/success")
    @ResponseBody
    public Map<String, Object> success(@RequestAttribute(value = "msg",required = false) String msg,
                                       @RequestAttribute(value = "code",required = false) Integer code, HttpServletRequest request) {

        Object msg1 = request.getAttribute("msg");
        Object code1 = request.getAttribute("code");

        Object hello = request.getAttribute("hello");
        Object world = request.getAttribute("world");
        Object message = request.getAttribute("message");

        Map<String, Object> map = new HashMap<>();
        map.put("reqMethod_msg", msg1);
        map.put("annotation_msg", msg);
        map.put("reqMethod_code", code1);
        map.put("annotation_code", code);
        map.put("hello",hello);
        map.put("world",world);
        map.put("message",message);

        return map;
    }

    //矩阵变量的方式接收参数
//1.语法    /cars/{sell;low=34;brand=byd,audi,yd}
//2. springBoot默认是禁用矩阵变量的功能，需要手动开启
    @GetMapping("/cars/{path}")
    @ResponseBody
    public Map<String, Object> carsSell(@MatrixVariable("low") Integer low, @MatrixVariable("brand") List<String> brand, @PathVariable("path") String path) {
        Map<String, Object> map = new HashMap<>();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

    //    /boss/1;age=20;/2;age=10
    @GetMapping("boss/{bossId}/{empId}")
    @ResponseBody
    public Map<String, Object> boss(@MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge, @MatrixVariable(value = "age", pathVar = "empId") Integer empAge) {
        Map<String, Object> map = new HashMap<>();
        map.put("bossAge", bossAge);
        map.put("empAge", empAge);
        return map;
    }



    //数据绑定测试
    @PostMapping("/saveuser")
    @ResponseBody
    public Persion saveuser(Persion persion){
        if(Persion.class == persion.getClass()){
            System.out.println(".class可以直接用 == 比较是否是同一个类");
        }
        return persion;
    }


}
