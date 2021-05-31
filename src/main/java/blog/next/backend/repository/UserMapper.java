//package blog.next.backend.repository;
//
//import blog.next.backend.entity.user.front.UserDetailEntity;
//import org.apache.ibatis.annotations.*;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Mapper
//@Repository
//public interface UserMapper {
//
//    //获取用户列表
//    @Results(
//            value = {
//                    @Result(property = "id", column = "id", id = true),
//                    @Result(property = "name", column = "name"),
//                    @Result(property = "address", column = "address")
//            }
//    )
//    @Select("select * from user")
//    List<UserDetailEntity> getUserList();
//
//
//    //添加一个用户
//    @Insert("insert into user (name,address) values(#{name}, #{address})")
//    //获取自增id
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
//    void addUser(UserDetailEntity UserDetailEntity);
//
//
//    //更新一个用户
//    int updateUser(UserDetailEntity userDetailEntity);
//
//    //删除一个用户
//    int deleteUser(int id);
//}
