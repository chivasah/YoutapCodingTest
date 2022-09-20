package zw.co.chivasa.henry.youtapcodingtest.controllers;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zw.co.chivasa.henry.youtapcodingtest.dto.ContactDTO;
import zw.co.chivasa.henry.youtapcodingtest.services.ContactService;

import static org.springframework.util.StringUtils.hasText;
import static java.util.Objects.isNull;

/**
 * @author henry
 */
@RestController("/")
public class ContactController {
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

//    @Autowired
//    public void setContactService(final ContactService contactService$) {
//        contactService = contactService$;
//    }

    //    @Inject
//    public ContactController(
//            final ContactService contactService$
//    ){
//        this.contactService = contactService$;
//    }

    @GetMapping("getusercontacts")
    public ContactDTO getUserContacts(
            @RequestParam(name = "userId", required = false) final Integer userId,
            @RequestParam(name = "username", required = false) final String username
    ) {
        try {
            if (isNull(userId) && !hasText(username)) {
                throw new IllegalArgumentException("A required parameter is missing");
            } else if (hasText(username)) {
                return contactService.findContactByUsername(username);
            } else {
                return contactService.findContactByUserID(userId);
            }
        } catch (final Exception exception$) {
            LOGGER.error("Failed to getUserContacts: {}", exception$.getLocalizedMessage());
        }

        return new ContactDTO(-1, null, null);
    }
}
