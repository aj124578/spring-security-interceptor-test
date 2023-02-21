package shop.mtcoding.springsecu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import shop.mtcoding.springsecu.handler.ex.CustomException;
import shop.mtcoding.springsecu.model.user.User;

@Component
public class CustomInterceptor implements HandlerInterceptor {
    // preHandle -> Controller -> postHandle -> 화면처리 -> afterCompletion



    // 컨트롤러 가기 전 실행
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
            final Logger logger = LoggerFactory.getLogger("[preHandle]");
            logger.info("preHandle");

            HttpSession session = request.getSession();
            User principal = (User) session.getAttribute("principal");
            if (principal == null) {
                throw new CustomException("인증이 되지 않았습니다.", HttpStatus.UNAUTHORIZED);
            }
            
        return true;
    }

    // 컨트롤러 처리 끝난 뒤 실행
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    // 화면 처리가 끝난 뒤 실행
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("afterHandle");
    }
}
