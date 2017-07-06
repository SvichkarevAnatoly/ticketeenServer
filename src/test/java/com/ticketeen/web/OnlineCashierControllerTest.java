package com.ticketeen.web;

import com.ticketeen.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
// This will start the server on a random port
@SpringBootTest(classes = TestConfig.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OnlineCashierControllerTest {
    private final RestTemplate template = new RestTemplate();
    // This will hold the port number the server was started on
    @Value("${local.server.port}")
    int port;

    @Test
    public void containsShopName() {
        final String login = "+79523507654";
        final String password = "948346";

        String message = template.getForObject("http://localhost:" + port +
                "/online-cashier/" + login + "/" + password, String.class);

        assertThat(message, is(containsString("\"user\":\"Ашан\"")));
    }
}
