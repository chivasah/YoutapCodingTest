package zw.co.chivasa.henry.youtapcodingtest.services;

import zw.co.chivasa.henry.youtapcodingtest.dto.ContactDTO;

import java.io.IOException;

/**
 * @author henry
 */
public interface ContactService {
    ContactDTO findContactByUserID(Integer id) throws IOException;

    ContactDTO findContactByUsername(String username) throws IOException;
}
