package zw.co.chivasa.henry.youtapcodingtest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

/**
 * @author henry
 */
@Data
@NoArgsConstructor
public class JsonPlaceHolderContactAddress {
    @Nullable
    private String street;
    @Nullable
    private String suite;
    @Nullable
    private String city;
    @Nullable
    private String zipcode;
    @Nullable
    private JsonPlaceHolderContactGeo geo;
}
