package blog.next.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = {"/interception"})
public class InterceptionController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/json")
    @ResponseBody
    public String Json(){
        return "json";
    }

    @PostMapping("/test")
    @ResponseBody
    public String Test(){return "test";}
}
