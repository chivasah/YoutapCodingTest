package zw.co.chivasa.henry.youtapcodingtest.services.impl;


import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import zw.co.chivasa.henry.youtapcodingtest.dto.ContactDTO;
import zw.co.chivasa.henry.youtapcodingtest.dto.converters.ContactDTOFromContactResponseConverter;
import zw.co.chivasa.henry.youtapcodingtest.model.JsonPlaceHolderContactResponse;
import zw.co.chivasa.henry.youtapcodingtest.services.ContactService;
import zw.co.chivasa.henry.youtapcodingtest.services.JsonPlaceHolderApi;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;
import static java.util.Objects.isNull;

/**
 * @author henry
 */
@Service
public class ContactServiceImpl implements ContactService {
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(ContactServiceImpl.class);

    private final String baseUrl;
    private final ContactDTOFromContactResponseConverter contactResponseConverter = new ContactDTOFromContactResponseConverter();
    private Retrofit retrofit = null;

    public ContactServiceImpl(
            @Value("${application.jsonplaceholder.baseUrl:http://localhost:8080}") final String baseUrl$
    ) {
        this.baseUrl = baseUrl$;
    }

    @Override
    public ContactDTO findContactByUserID(final Integer userId) throws IOException {
        if(isNull(userId)){
            return new ContactDTO(-1, null, null);
        }
        JsonPlaceHolderApi service;
        try {
            final Retrofit retrofit$ = getRetrofitClientInstance();
            service = retrofit$.create(JsonPlaceHolderApi.class);
        } catch (final Exception exception$) {
            LOGGER.error("{}", exception$.getLocalizedMessage());
            throw new RuntimeException("FATAL ERROR GENERATING REST CLIENT: " + exception$.getLocalizedMessage());
        }

        final Response<List<JsonPlaceHolderContactResponse>> response = service.fetchUsers().execute();
        if (!response.isSuccessful()) {
            throw new IOException(Objects.requireNonNull(response.errorBody()).string());
        }

        final List<JsonPlaceHolderContactResponse> fetchedUsers = response.body();
        final Optional<ContactDTO> optionalContactResponse = fetchedUsers.stream()
                .filter(o$ -> o$.getId().equals(userId))
                .map(contactResponseConverter::convert)
                .findFirst();

        return optionalContactResponse.orElseGet(() -> new ContactDTO(-1, null, null));
    }

    @Override
    public ContactDTO findContactByUsername(final String username) throws IOException {
        if(!hasText(username)){
            return new ContactDTO(-1, null, null);
        }
        JsonPlaceHolderApi service;
        try {
            final Retrofit retrofit$ = getRetrofitClientInstance();
            service = retrofit$.create(JsonPlaceHolderApi.class);
        } catch (final Exception exception$) {
            LOGGER.error("{}", exception$.getLocalizedMessage());
            throw new RuntimeException("FATAL ERROR GENERATING REST CLIENT: " + exception$.getLocalizedMessage());
        }

        final Response<List<JsonPlaceHolderContactResponse>> response = service.fetchUsers().execute();
        if (!response.isSuccessful()) {
            throw new IOException(Objects.requireNonNull(response.errorBody()).string());
        }
        final List<JsonPlaceHolderContactResponse> fetchedUsers = response.body();
        final Optional<ContactDTO> optionalContactResponse = fetchedUsers.stream()
                .filter(o$ -> o$.getUsername().equalsIgnoreCase(username))
                .map(contactResponseConverter::convert)
                .findFirst();

        return optionalContactResponse.orElseGet(() -> new ContactDTO(-1, null, null));
    }

    private Retrofit getRetrofitClientInstance() throws IllegalArgumentException {
        if(isNull(retrofit)){
            final ConnectionPool pool = new ConnectionPool();
            final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

            final OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectionPool(pool)
                    .build();
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(client)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
