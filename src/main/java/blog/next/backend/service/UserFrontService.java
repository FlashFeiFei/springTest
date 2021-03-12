package blog.next.backend.service;

import blog.next.backend.entity.user.front.UserDetailEntity;
import blog.next.backend.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserFrontService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户列表,用于前端
     *
     * @return List<UserDetailEntity>
     */
    @Transactional
    public List<UserDetailEntity> getUserListByFront() {
        List<UserDetailEntity> list;
        list = userMapper.getUserList();
//        if(true){
//            throw new BaseException("事务测试");
//        }

        return list;
    }


    /**
     * 创建一个用户
     *
     * @return int
     */
    @Transactional
    public void addUser(UserDetailEntity userDetailEntity) {
         userMapper.addUser(userDetailEntity);
    }
}
