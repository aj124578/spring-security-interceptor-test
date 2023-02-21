package shop.mtcoding.springsecu.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import shop.mtcoding.springsecu.model.user.User;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK) // MOCK 라고 하면 가짜 환경의 Ioc 컨테이너가 존재하게 되는 것
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    private MockHttpSession mockSession;

    @BeforeAll
    public static void 테이블차리기(){ // 컨트롤러 테스트가 실행되기 직전에 한번 실행됨
        // 테이블 만들고
        
    }

    @AfterEach
    public void teardown() { // 메서드가 실행된 직후 매번 실행됨
        // 데이터 지우고(trumcate로 데이터만 날림), 다시 insert

    }

    @BeforeEach // Test 메서드 실행 직전 마다 호출됨
    public void setUp(){
        // 테이블 insert

        // 세션 주입
        User user = new User();
        user.setId(1);
        user.setUsername("ssar");
        user.setPassword("1234");
        user.setEmail("ssar@nate.com");
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        
        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", user);
    }

    @Test
    public void join_test() throws Exception {
        // given
        String requestBody = "username=cos&password=1234&email=cos@nate.com";
        
        // when
        ResultActions resultActions = mvc.perform(post("/join").content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        // then
        resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    public void login_test() throws Exception {
        // given
        String requestBody = "username=ssar&password=1234"; 

        // when
        ResultActions resultActions = mvc.perform(post("/login").content(requestBody) 
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)); 

                HttpSession session = resultActions.andReturn().getRequest().getSession();
                User principal = (User) session.getAttribute("principal");
                System.out.println(principal.getUsername());

        // then
        assertThat(principal.getUsername()).isEqualTo("ssar");
        resultActions.andExpect(status().is3xxRedirection());
    }
}