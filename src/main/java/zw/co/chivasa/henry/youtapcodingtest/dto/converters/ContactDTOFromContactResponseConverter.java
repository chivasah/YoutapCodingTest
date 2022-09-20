package zw.co.chivasa.henry.youtapcodingtest.dto.converters;

import org.springframework.core.convert.converter.Converter;
import zw.co.chivasa.henry.youtapcodingtest.dto.ContactDTO;
import zw.co.chivasa.henry.youtapcodingtest.model.JsonPlaceHolderContactResponse;

import static java.util.Objects.isNull;
/**
 * @author henry
 */
public final class ContactDTOFromContactResponseConverter implements Converter<JsonPlaceHolderContactResponse, ContactDTO>
{
   @Override
   public ContactDTO convert(final JsonPlaceHolderContactResponse source)
   {
      if (isNull(source)) {
         return null;
      }

      return new ContactDTO(source.getId(), source.getEmail(), source.getPhone());
   }
}
