package shop.mtcoding.springsecu.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.springsecu.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.springsecu.dto.user.UserReq.LoginReqDto;
import shop.mtcoding.springsecu.model.user.User;
import shop.mtcoding.springsecu.util.PasswordHash;


// F - DS - C - S - R - MyBatis - DB


@MybatisTest
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;


    @Test
    public void findByUsernameAndPassword_test() throws Exception {
        // given
        LoginReqDto loginReqDto = new LoginReqDto();
        loginReqDto.setUsername("ssar");
        loginReqDto.setPassword("1234");

        ObjectMapper om = new ObjectMapper();
        // when
        User user = userRepository.findByUsernameAndPassword(loginReqDto);
        String responseBody = om.writeValueAsString(user); 
        System.out.println("테스트 : " + responseBody);

        // then
        assertThat(user.getUsername()).isEqualTo("ssar");
        assertThat(user.getPassword()).isEqualTo("1234");
    }

    @Test
    public void insert_test() throws Exception {
        // given
        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setUsername("cos");
        joinReqDto.setPassword("1234");
        joinReqDto.setEmail("cos@nate.com");

        String pw = PasswordHash.getPasswordHash(joinReqDto.getPassword());
        joinReqDto.setPassword(pw);
        System.out.println("테스트2 pw : " + pw);

        ObjectMapper om = new ObjectMapper();
        // when
        int reuslt = userRepository.insert(joinReqDto);
        String responseBody = om.writeValueAsString(reuslt); 
        System.out.println("테스트 : " + responseBody);
       

        // then
        assertThat(reuslt).isEqualTo(1);

    }
}