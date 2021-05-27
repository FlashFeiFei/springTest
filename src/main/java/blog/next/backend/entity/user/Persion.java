package blog.next.backend.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Persion implements Serializable {
    private String userName;
    private Integer age;
    private Date birth;
    private Pet pet;
}

