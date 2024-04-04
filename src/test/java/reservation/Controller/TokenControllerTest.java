package reservation.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(reservation.Controller.TokenController.class)
class TokenControllerTest {

    @Autowired private MockMvc mockMvc;

    @Test
    void test_토큰발급() throws Exception {
        mockMvc.perform(post("/token"))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                "token:" + "token" +"}")
                );
    }
}