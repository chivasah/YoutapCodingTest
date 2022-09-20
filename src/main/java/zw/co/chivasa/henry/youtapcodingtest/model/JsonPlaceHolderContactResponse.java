package zw.co.chivasa.henry.youtapcodingtest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

/**
 * @author henry
 */
@Data
@NoArgsConstructor
public class JsonPlaceHolderContactResponse {
    private Integer id;
    @Nullable
    private String name;
    private String username;
    private String email;
    @Nullable
    private JsonPlaceHolderContactAddress address;
    private String phone;
    @Nullable
    private String website;
    @Nullable
    private JsonPlaceHolderContactCompany company;
}
