package blog.next.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传测试
 */
@Slf4j
@Controller
@RequestMapping(path = {"/upload"})
public class FormController {

    /**
     * 上传页面
     * @return 返回form视图
     */
    @GetMapping("/formLayouts")
    public String formLayouts(){
        return "form";
    }


    @PostMapping("/upload")
    public String upload(@RequestParam("userName") String userName,
                         //单文件
                         @RequestPart("headerImg") MultipartFile headerImg,
                         //多文件
                         @RequestPart("photos") MultipartFile[] photos
                         ) throws IOException {


        //log变量看.class字节码，会在编译期间初始化
        log.info("上传信息: userName={}, headerImg={}, photos={}",userName,headerImg.getSize(),photos.length);

        if(!headerImg.isEmpty()){
            headerImg.transferTo(new File("D:\\upload\\" +  headerImg.getOriginalFilename()));
        }

        if(photos.length > 0){
            for(MultipartFile photo : photos){
                photo.transferTo(new File("D:\\upload\\" + photo.getOriginalFilename()));
            }
        }

        return "form";

    }
}
