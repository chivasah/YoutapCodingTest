package zw.co.chivasa.henry.youtapcodingtest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

/**
 * @author henry
 */
@Data
@NoArgsConstructor
public class JsonPlaceHolderContactCompany {
    private String name;
    @Nullable
    private String catchPhrase;
    @Nullable
    private String bs;
}
