package zw.co.chivasa.henry.youtapcodingtest.dto;

import lombok.ToString;
import lombok.Value;

import javax.annotation.Nonnull;

/**
 * @author henry
 */
@Value
@ToString
public class ContactDTO{
    @Nonnull
    Integer id;
    String email;
    String phone;
}
