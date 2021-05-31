//package blog.next.backend.controller.front;
//
//
//import blog.next.backend.entity.user.front.UserDetailEntity;
//import blog.next.backend.service.UserFrontService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = {"/front/user"})
//public class UserController {
//
//    @Autowired
//    private UserFrontService userFrontService;
//
//    @GetMapping("/getList")
//    public List<UserDetailEntity> getList() {
//        return userFrontService.getUserListByFront();
//    }
//
//
//    @GetMapping("/add")
//    @ResponseBody
//    public UserDetailEntity addUser(UserDetailEntity userDetailEntity) {
//        userFrontService.addUser(userDetailEntity);
//        return userDetailEntity;
//    }
//}
