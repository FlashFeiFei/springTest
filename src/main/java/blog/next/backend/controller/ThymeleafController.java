package blog.next.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/hello")
    public String success(Model model){

        model.addAttribute("msg","你好 Ly");
        model.addAttribute("link","https://ly.laughingzhu.cn");
        return "hello";
    }
}
