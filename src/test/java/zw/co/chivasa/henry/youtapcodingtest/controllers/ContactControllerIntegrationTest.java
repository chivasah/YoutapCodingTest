package zw.co.chivasa.henry.youtapcodingtest.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import zw.co.chivasa.henry.youtapcodingtest.AbstractTestData;
import zw.co.chivasa.henry.youtapcodingtest.services.ContactService;
import zw.co.chivasa.henry.youtapcodingtest.services.impl.ContactServiceImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author henry
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ContactController.class)
@TestPropertySource(properties = {"application.jsonplaceholder.baseUrl=https://jsonplaceholder.typicode.com"})
@Import(ContactServiceImpl.class)
public class ContactControllerIntegrationTest extends AbstractTestData {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ContactService contactService;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(MockMvcResultHandlers.print()).build();
    }

    @Test
    public void when_findContactByUserIDIsCalled_withNoParameters_shouldReturnDefaultContactDTO() throws Exception {
        String uri = "/getusercontacts";

        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(defaultContactDTO.getId())))
                .andExpect(jsonPath("$.email", is(defaultContactDTO.getEmail())))
                .andExpect(jsonPath("$.phone", is(defaultContactDTO.getPhone())));
    }

    @Test
    public void when_findContactByUserIDIsCalled_withValidId_shouldReturnValidContactDTO() throws Exception {
        String uri = "/getusercontacts";


        mockMvc.perform(get(uri).param("userId", validUserId + ""))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validContactDTO.getId())))
                .andExpect(jsonPath("$.email", is(validContactDTO.getEmail())))
                .andExpect(jsonPath("$.phone", is(validContactDTO.getPhone())));

    }

    @Test
    public void when_findContactByUserIDIsCalled_withInvalidId_shouldReturnDefaultContactDTO() throws Exception {
        String uri = "/getusercontacts";


        mockMvc.perform(get(uri).param("userId", invalidUserId + ""))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(defaultContactDTO.getId())))
                .andExpect(jsonPath("$.email", is(defaultContactDTO.getEmail())))
                .andExpect(jsonPath("$.phone", is(defaultContactDTO.getPhone())));

    }

    @Test
    public void when_findContactByUserIDIsCalled_withMalformedId_shouldReturnDefaultContactDTO() throws Exception {
        String uri = "/getusercontacts";


        mockMvc.perform(get(uri).param("userId", "ABCDEFG"))
                .andExpect(status().isBadRequest());

    }


    @Test
    public void when_findContactByUsernameIsCalled_withValidUsername_shouldReturnValidContactDTO() throws Exception {
        String uri = "/getusercontacts";


        mockMvc.perform(get(uri).param("username", validUserName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validContactDTO.getId())))
                .andExpect(jsonPath("$.email", is(validContactDTO.getEmail())))
                .andExpect(jsonPath("$.phone", is(validContactDTO.getPhone())));

    }

    @Test
    public void when_findContactByUsernameIsCalled_withInvalidUsername_shouldReturnValidContactDTO() throws Exception {
        String uri = "/getusercontacts";

        mockMvc.perform(get(uri).param("username", invalidUserName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(defaultContactDTO.getId())))
                .andExpect(jsonPath("$.email", is(defaultContactDTO.getEmail())))
                .andExpect(jsonPath("$.phone", is(defaultContactDTO.getPhone())));
    }
}
