package org.ninrod.app.stocks.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ninrod.app.stocks.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class ControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    GreetingService service;


    private final static String endpoint = "/greeting";
    private final static String parameter = "?name=";
    private final static String phrase = "hello, ";

    @Test
    public void normal() throws Exception {
        String expectedName = "World";
        when(service.phrase()).thenReturn(phrase);
        mvc.perform(get(endpoint).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.content", is(phrase + expectedName + "!")));
        verify(service, times(1)).phrase();
    }

    @Test
    public void comParametro() throws Exception {
        String name = "NiNRoD";
        when(service.phrase()).thenReturn(phrase);

        mvc.perform(get(endpoint + parameter + name).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is(phrase + name + "!")));
        verify(service, times(1)).phrase();
    }
}
