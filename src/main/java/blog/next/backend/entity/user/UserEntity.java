package blog.next.backend.entity.user;

/**
 * 用户实体类
 */
public class UserEntity {

    /**
     * 用户id
     */
    private int id;
    /**
     * 用户名字
     */
    private String name;
    
    public UserEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

