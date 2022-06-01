package hr.tvz.grgat.hardwareapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HardwareControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    public String obtainAccessToken() throws Exception {
        Map<String,Object> body = new HashMap<>();
        body.put("username", "admin");
        body.put("password", "admin");

        MvcResult result = mockMvc.perform(
                        post("/authentication/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body))
                )
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(response).get("jwt").toString();
    }

    @Test
    void getAllHardware() throws Exception {
        int arraySize = 7;


        this.mockMvc.perform(
                get("/hardware")
                        .header("Authorization", "Bearer " + obtainAccessToken())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(arraySize)));
    }

    @Test
    void getHardwareByCode() throws Exception {
        String TEST_CODE = "AAA";
        String TEST_NAME = "INTEL PENTIUM GOLD";

        this.mockMvc.perform(
                get("/hardware/" + TEST_CODE)
                        .header("Authorization", "Bearer " + obtainAccessToken())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.code").value(TEST_CODE))
                .andExpect(jsonPath("$.name").value(TEST_NAME));
    }

    @DirtiesContext
    @Test
    void save() throws Exception {

        String TEST_CODE = "BBB";
        String TEST_NAME = "novi";
        Double TEST_PRICE = 111.0;
        Type TEST_TYPE = Type.CPU;
        Integer TEST_STOCK = 200;

        HardwareCommand hardwareCommand = new HardwareCommand(
                TEST_CODE,
                TEST_NAME,
                TEST_TYPE,
                TEST_PRICE,
                TEST_STOCK
        );

        this.mockMvc.perform(
            post("/hardware")
                    .header("Authorization", "Bearer " + obtainAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(hardwareCommand))
                    .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andExpect(jsonPath("$.code").value(TEST_CODE))
                    .andExpect(jsonPath("$.name").value(TEST_NAME))
                    .andExpect(jsonPath("$.price").value(TEST_PRICE));
        }
}