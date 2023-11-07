package ru.urfu.SecondLabTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.urfu.SecondLabTask.controllers.EventController;
import ru.urfu.SecondLabTask.models.Response;
import ru.urfu.SecondLabTask.service.ResponseService;
import ru.urfu.SecondLabTask.validation.RequestValidationService;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class EventControllerTest {


    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    private final Map<String, Object> requestJsonBody = new HashMap<>();


    @BeforeEach
    void setup() {
        RequestValidationService requestValidationService = Mockito.mock(RequestValidationService.class);
        ResponseService responseService = Mockito.mock(ResponseService.class);
        EventController eventController = new EventController(requestValidationService, responseService);
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
        requestJsonBody.put("uid", "");
        requestJsonBody.put("operationUid", "");
        requestJsonBody.put("systemName", "");
        requestJsonBody.put("systemTime", "");
        requestJsonBody.put("source", "");
        requestJsonBody.put("communicationId", 0);
        requestJsonBody.put("templateId", 0);
        requestJsonBody.put("productCode", 0);
        requestJsonBody.put("smsCode", 0);
    }

    private Response executeCorrectPostRequest(String endpoint, String requestJsonBody) throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJsonBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String responseContent = mvcResult.getResponse().getContentAsString();
        return objectMapper.readValue(responseContent, Response.class);
    }


    @Test
    void whenPostRequestToFeedback_thenCorrectResponse() throws Exception {
        requestJsonBody.put("uid", "1233");
        requestJsonBody.put("operationUid", "1233");
        requestJsonBody.put("systemName", "Enterprise Resource Planning");
        requestJsonBody.put("systemTime", "2023-10-29T12:34:56");
        requestJsonBody.put("source", "Web");
        requestJsonBody.put("communicationId", 123456);
        requestJsonBody.put("templateId", 1);
        requestJsonBody.put("productCode", 1001);
        requestJsonBody.put("smsCode", 5678);

        Gson gson = new Gson();
        Type requestType = new TypeToken<Map<String, Object>>() {}.getType();
        String requestBody = gson.toJson(requestJsonBody, requestType);
        Response resultResponse = executeCorrectPostRequest("/feedback", requestBody);

        Map<String, String> expectedResponseMap = new HashMap<>();
        expectedResponseMap.put("uid", "1233");
        expectedResponseMap.put("operationUid", "1233");
        expectedResponseMap.put("systemTime", "2023-11-06T21:11:12.562Z");
        expectedResponseMap.put("code", "success");
        expectedResponseMap.put("errorCode", "");
        expectedResponseMap.put("errorMessage", "");
        String jsonResponse = objectMapper.writeValueAsString(expectedResponseMap);
        Response expectedResponse = objectMapper.readValue(jsonResponse, Response.class);

        Assert.assertEquals(resultResponse, expectedResponse);

    }

}
