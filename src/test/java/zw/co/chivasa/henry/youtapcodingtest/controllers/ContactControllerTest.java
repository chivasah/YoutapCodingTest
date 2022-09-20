package zw.co.chivasa.henry.youtapcodingtest.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import zw.co.chivasa.henry.youtapcodingtest.AbstractTestData;
import zw.co.chivasa.henry.youtapcodingtest.services.impl.ContactServiceImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author henry
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ContactController.class)
public class ContactControllerTest extends AbstractTestData {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private ContactServiceImpl contactService;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(MockMvcResultHandlers.print()).build();
    }

    @Test
    public void when_findContactByUserIDIsCalled_withNoParameters_shouldReturnDefaultContactDTO() throws Exception {
        String uri = "/getusercontacts";

        when(contactService.findContactByUserID(null)).thenReturn(defaultContactDTO);

        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(defaultContactDTO.getId())))
                .andExpect(jsonPath("$.email",is(defaultContactDTO.getEmail())))
                .andExpect(jsonPath("$.phone",is(defaultContactDTO.getPhone())));

        verify(contactService, times(0)).findContactByUserID(validUserId);
    }

    @Test
    public void when_findContactByUserIDIsCalled_withValidId_shouldReturnValidContactDTO() throws Exception {
        String uri = "/getusercontacts";

        when(contactService.findContactByUserID(validUserId)).thenReturn(validContactDTO);

        mockMvc.perform(get(uri).param("userId", validUserId + ""))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validContactDTO.getId())))
                .andExpect(jsonPath("$.email",is(validContactDTO.getEmail())))
                .andExpect(jsonPath("$.phone",is(validContactDTO.getPhone())));

        verify(contactService).findContactByUserID(validUserId);
    }

    @Test
    public void when_findContactByUserIDIsCalled_withInvalidId_shouldReturnDefaultContactDTO() throws Exception {
        String uri = "/getusercontacts";

        when(contactService.findContactByUserID(invalidUserId)).thenReturn(defaultContactDTO);

        mockMvc.perform(get(uri).param("userId", invalidUserId + ""))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(defaultContactDTO.getId())))
                .andExpect(jsonPath("$.email",is(defaultContactDTO.getEmail())))
                .andExpect(jsonPath("$.phone",is(defaultContactDTO.getPhone())));

        verify(contactService).findContactByUserID(invalidUserId);
    }

    @Test
    public void when_findContactByUserIDIsCalled_withMalformedId_shouldExpectBadRequest() throws Exception {
        String uri = "/getusercontacts";

        when(contactService.findContactByUserID(invalidUserId)).thenReturn(defaultContactDTO);

        mockMvc.perform(get(uri).param("userId", "ABCDEFG"))
                .andExpect(status().isBadRequest());

        verify(contactService, times(0)).findContactByUserID(invalidUserId);
    }


    @Test
    public void when_findContactByUsernameIsCalled_withValidUsername_shouldReturnValidContactDTO() throws Exception {
        String uri = "/getusercontacts";

        when(contactService.findContactByUsername(validUserName)).thenReturn(validContactDTO);

        mockMvc.perform(get(uri).param("username", validUserName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validContactDTO.getId())))
                .andExpect(jsonPath("$.email",is(validContactDTO.getEmail())))
                .andExpect(jsonPath("$.phone",is(validContactDTO.getPhone())));

        verify(contactService).findContactByUsername(validUserName);
    }

    @Test
    public void when_findContactByUsernameIsCalled_withInvalidUsername_shouldReturnDefaultContactDTO() throws Exception {
        String uri = "/getusercontacts";

        when(contactService.findContactByUsername(invalidUserName)).thenReturn(defaultContactDTO);

        mockMvc.perform(get(uri).param("username", invalidUserName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(defaultContactDTO.getId())))
                .andExpect(jsonPath("$.email",is(defaultContactDTO.getEmail())))
                .andExpect(jsonPath("$.phone",is(defaultContactDTO.getPhone())));

        verify(contactService).findContactByUsername(invalidUserName);
    }

}
