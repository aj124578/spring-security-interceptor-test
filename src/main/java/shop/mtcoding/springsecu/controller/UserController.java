package shop.mtcoding.springsecu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.springsecu.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.springsecu.handler.ex.CustomException;

@Controller
public class UserController {

    @PostMapping("/join")
    public String join(JoinReqDto joinReqDto){
        System.out.println(joinReqDto.getUsername());
        System.out.println(joinReqDto.getPassword());
        System.out.println(joinReqDto.getEmail());

        if(joinReqDto.getUsername() == null || joinReqDto.getUsername().isEmpty()){
            throw new CustomException("username을 작성해주세요");
        }
        if (joinReqDto.getPassword() == null || joinReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 작성해주세요");
        }
        if (joinReqDto.getEmail() == null || joinReqDto.getEmail().isEmpty()) {
            throw new CustomException("email을 작성해주세요");
        } 

        // userService.회원가입(joinReqDto); 
      
        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping({"/", "/loginForm"})
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/main")
    public String mainForm() {
        return "user/main";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }



}
