package shop.mtcoding.springsecu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.springsecu.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.springsecu.handler.ex.CustomException;
import shop.mtcoding.springsecu.model.UserRepository;
import shop.mtcoding.springsecu.model.user.User;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Transactional // insert가 동시에 안되고 메서드 자체는 동시에 가능함. 하지만 동시접근 불가능으로 생각하면
    public void 회원가입(JoinReqDto joinReqDto){
        User sameUser = userRepository.findByUsername(joinReqDto.getUsername());
        if (sameUser != null) {
            throw new CustomException("동일한 username이 존재합니다");
        }
        int result = userRepository.insert(joinReqDto);
        if (result != 1) {
            throw new CustomException("회원가입실패");
        }
    }


}
