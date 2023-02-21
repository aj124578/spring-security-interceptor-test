package shop.mtcoding.springsecu.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.springsecu.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.springsecu.model.user.User;

@Mapper
public interface UserRepository {

    public User findByUsername(String username);

    public List<User> findAll();

    public User findById(int id);

    public int insert(JoinReqDto joinReqDto);

    public int updateById(User user);

    public int deleteById(int id);

}
