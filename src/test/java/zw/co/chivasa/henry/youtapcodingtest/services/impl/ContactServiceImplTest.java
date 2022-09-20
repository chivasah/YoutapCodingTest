package zw.co.chivasa.henry.youtapcodingtest.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import zw.co.chivasa.henry.youtapcodingtest.AbstractTestData;
import zw.co.chivasa.henry.youtapcodingtest.dto.ContactDTO;
import zw.co.chivasa.henry.youtapcodingtest.services.ContactService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {"application.jsonplaceholder.baseUrl=https://jsonplaceholder.typicode.com"})
@Import(ContactServiceImpl.class)
public class ContactServiceImplTest extends AbstractTestData {

    @Autowired
    private ContactService contactService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void when_findContactByUserIDIsCalled_withValidId_shouldReturnValidResponse() throws IOException {
        ContactDTO result = contactService.findContactByUserID(validUserId);
        assertNotNull(result);
        assertEquals(validUserId, result.getId());
        assertEquals(validEmail, result.getEmail());
    }

    @Test
    void when_findContactByUserIDIsCalled_withInvalidId_shouldReturnValidResponse() throws IOException {
        ContactDTO result = contactService.findContactByUserID(invalidUserId);
        assertNotNull(result);
        assertEquals(defaultContactDTO.getId(), result.getId());
        assertEquals(defaultContactDTO.getEmail(), result.getEmail());
        assertEquals(defaultContactDTO.getPhone(), result.getPhone());
    }


    @Test
    void when_findContactByUserIDIsCalled_withNullId_shouldReturnValidResponse() throws IOException {
        ContactDTO result = contactService.findContactByUserID(null);
        assertNotNull(result);
        assertEquals(defaultContactDTO.getId(), result.getId());
        assertEquals(defaultContactDTO.getEmail(), result.getEmail());
        assertEquals(defaultContactDTO.getPhone(), result.getPhone());
    }

    @Test
    void when_findContactByUsernameIsCalled_withValidUsername_shouldReturnValidResponse() throws Exception {
        ContactDTO result = contactService.findContactByUsername(validUserName);
        assertNotNull(result);
        assertEquals(validUserId, result.getId());
        assertEquals(validEmail, result.getEmail());
    }

    @Test
    void when_findContactByUsernameIsCalled_withInvalidUsername_shouldReturnValidResponse() throws IOException {
        ContactDTO result = contactService.findContactByUsername(invalidUserName);
        assertNotNull(result);
        assertEquals(defaultContactDTO.getId(), result.getId());
        assertEquals(defaultContactDTO.getEmail(), result.getEmail());
        assertEquals(defaultContactDTO.getPhone(), result.getPhone());
    }

    @Test
    void when_findContactByUsernameIsCalled_withNullUsername_shouldReturnValidResponse() throws IOException {
        ContactDTO result = contactService.findContactByUsername(null);
        assertNotNull(result);
        assertEquals(defaultContactDTO.getId(), result.getId());
        assertEquals(defaultContactDTO.getEmail(), result.getEmail());
        assertEquals(defaultContactDTO.getPhone(), result.getPhone());
    }
}
