package blog.next.backend.entity.user.front;

import blog.next.backend.entity.user.UserEntity;

/**
 * 用户详情
 */
public class UserDetailEntity extends UserEntity {
    /**
     * 闲情添加多一个地址
     */
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
