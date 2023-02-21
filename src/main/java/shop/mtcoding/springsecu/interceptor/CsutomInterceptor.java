package shop.mtcoding.springsecu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CsutomInterceptor implements HandlerInterceptor {
    // preHandle -> Controller -> postHandle -> 화면처리 -> afterCompletion

    // 컨트롤러 가기 전 실행
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                
        System.out.println("preHandle");
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
