package blog.next.backend.entity.user;

import lombok.Data;

import java.util.Date;

@Data
public class Persion {
    private String userName;
    private Integer age;
    private Date birth;
    private Pet pet;
}

