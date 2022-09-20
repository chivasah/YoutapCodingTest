package zw.co.chivasa.henry.youtapcodingtest.services;

import retrofit2.Call;
import retrofit2.http.GET;
import zw.co.chivasa.henry.youtapcodingtest.model.JsonPlaceHolderContactResponse;

import java.util.List;

/**
 * @author henry
 */
public interface JsonPlaceHolderApi
{
   @GET("/users")
   Call<List<JsonPlaceHolderContactResponse>> fetchUsers();
}
