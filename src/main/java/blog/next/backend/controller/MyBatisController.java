package blog.next.backend.controller;


import blog.next.backend.mapper.UserMapper;
import blog.next.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/mybatis")
public class MyBatisController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUser")
    public List<User> getUser() {
        List<User> users = userMapper.selectList(null);
        return users;
    }

//    @PostMapping("/insert")
//    public User insert(){
//
//    }
}
