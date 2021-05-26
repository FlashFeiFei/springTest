package blog.next.backend.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RequestController {

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request){
        request.setAttribute("msg","成功了......");
        request.setAttribute("code",200);
        return "forward:/success";  //转发到success请求
    }

    @GetMapping("/success")
    @ResponseBody
    public Map<String,Object> success(@RequestAttribute("msg") String msg, @RequestAttribute("code") Integer code,HttpServletRequest request){

        Object msg1 = request.getAttribute("msg");
        Object code1 = request.getAttribute("code");

        Map<String,Object> map = new HashMap<>();
        map.put("reqMethod_msg",msg1);
        map.put("annotation_msg",msg);
        map.put("reqMethod_code",code1);
        map.put("annotation_code",code);

        return map;
    }

    //矩阵变量的方式接收参数
//1.语法    /cars/sell;low=34;brand=byd,audi,yd
//2. springBoot默认是禁用矩阵变量的功能，需要手动开启
    @GetMapping("/cars/{path}")
    @ResponseBody
    public  Map carsSell(@MatrixVariable("low") Integer low, @MatrixVariable("brand") List<String> brand, @PathVariable("path") String path){
        Map<String,Object> map = new HashMap<>();
        map.put("low",low);
        map.put("brand",brand);
        map.put("path",path);
        return map;
    }

//    /boss/1;age=20;/2;age=10
    @GetMapping("boss/{bossId}/{empId}")
    @ResponseBody
    public Map boss(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge,@MatrixVariable(value = "age",pathVar = "empId") Integer empAge){
        Map<String,Object> map = new HashMap<>();
        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        return map;
    }



}
