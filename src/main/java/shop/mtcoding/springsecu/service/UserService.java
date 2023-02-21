package shop.mtcoding.springsecu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.springsecu.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.springsecu.dto.user.UserReq.LoginReqDto;
import shop.mtcoding.springsecu.handler.ex.CustomException;
import shop.mtcoding.springsecu.model.UserRepository;
import shop.mtcoding.springsecu.model.user.User;
import shop.mtcoding.springsecu.util.PasswordHash;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Transactional 
    public void 회원가입(JoinReqDto joinReqDto){
        User sameUser = userRepository.findByUsername(joinReqDto.getUsername());
        String pw = PasswordHash.getPasswordHash(joinReqDto.getPassword());
        joinReqDto.setPassword(pw);
        System.out.println("테스트 : " + pw);

        if (sameUser != null) {
            throw new CustomException("동일한 username이 존재합니다");
        }
        int result = userRepository.insert(joinReqDto);
        if (result != 1) {
            throw new CustomException("회원가입실패");
        }
    }


    @Transactional(readOnly = true) 
    public User 로그인(LoginReqDto loginReqDto) {
        User principal = (User)userRepository.findByUsernameAndPassword(loginReqDto);

       
        if (principal == null) {
            throw new CustomException("유저네임 혹은 패스워드가 잘못 입력되었습니다");
        }
        
        return principal;
    }
}
