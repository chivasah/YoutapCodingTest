package zw.co.chivasa.henry.youtapcodingtest.dto.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import zw.co.chivasa.henry.youtapcodingtest.AbstractTestData;
import zw.co.chivasa.henry.youtapcodingtest.controllers.ContactController;
import zw.co.chivasa.henry.youtapcodingtest.dto.ContactDTO;
import zw.co.chivasa.henry.youtapcodingtest.model.JsonPlaceHolderContactResponse;
import zw.co.chivasa.henry.youtapcodingtest.services.ContactService;
import zw.co.chivasa.henry.youtapcodingtest.services.impl.ContactServiceImpl;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ContactController.class)
@Import(ContactServiceImpl.class)
public class ContactDTOFromContactResponseConverterTest extends AbstractTestData {

    private ContactDTOFromContactResponseConverter contactResponseConverter;

    @Autowired
    private ObjectMapper objectMapper;

    private final String validResponse = "[{ \"id\": 1 , \"name\": \"Leanne Graham\" , \"username\": \"Bret\" , \"email\": \"Sincere@april.biz\", \"address\": { \"street\": \"Kulas Light\" , \"suite\": \"Apt. 556\" , \"city\": \"Gwenborough\" , \"zipcode\": \"92998-3874\" , \"geo\": { \"lat\": \"-37.3159\", \"lng\": \"81.1496\" } } , \"phone\": \"1-770-736-8031 x56442\" , \"website\": \"hildegard.org\" , \"company\": { \"name\": \"Romaguera-Crona\" , \"catchPhrase\": \"Multi-layered client-server neural-net\" , \"bs\": \"harness real-time e-markets\" } }]";
//    private final String invalidResponse = "[{  \"id\": 1 ,\"name\": \"Leanne Graham\" , \"username\": \"Bret\" , \"address\": { \"street\": \"Kulas Light\" , \"suite\": \"Apt. 556\" , \"city\": \"Gwenborough\" , \"zipcode\": \"92998-3874\" , \"geo\": { \"lat\": \"-37.3159\", \"lng\": \"81.1496\" } } , \"phone\": \"1-770-736-8031 x56442\" , \"website\": \"hildegard.org\" , \"company\": { \"name\": \"Romaguera-Crona\" , \"catchPhrase\": \"Multi-layered client-server neural-net\" , \"bs\": \"harness real-time e-markets\" } }]";

    @BeforeEach
    void setUp() {
        contactResponseConverter = new ContactDTOFromContactResponseConverter();
    }

    @Test
    void when_convertIsCalled_withNull_shouldReturnNull() {
        ContactDTO result = contactResponseConverter.convert(null);
        assertNull(result);
    }

    @Test
    void when_convertIsCalled_withValidResponse_shouldReturnContactDTO() throws JsonProcessingException {
        JsonPlaceHolderContactResponse[] response = objectMapper.readValue(validResponse, JsonPlaceHolderContactResponse[].class);
        JsonPlaceHolderContactResponse object = response[0];
        ContactDTO result = contactResponseConverter.convert(object);
        assertNotNull(result);
    }

//    @Test
//    void when_convertIsCalled_withInvalidResponse_shouldReturnContactDTO() throws JsonProcessingException {
//        JsonPlaceHolderContactResponse[] response = objectMapper.readValue(invalidResponse, JsonPlaceHolderContactResponse[].class);
//        JsonPlaceHolderContactResponse object = response[0];
//        ContactDTO result = contactResponseConverter.convert(object);
//        assertNotNull(result);
//    }
}
