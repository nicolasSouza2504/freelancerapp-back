package utils;

import com.google.gson.Gson;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UtilErrorRest {

    public static void throwResponseError(String message) {
        throwResponseError(message, 400);
    }

    public static void throwResponseError(String message, Integer status) {
        throwResponseError(List.of(message), status);
    }

    public static void throwResponseError(List<String> messages, Integer status) {

        if (CollectionUtils.isNotEmpty(messages)) {

            List<ResponseDto> responseDtos = new ArrayList<>();

            for (String message : messages) {
                responseDtos.add(new ResponseDto(message, true));
            }

            throw new WebApplicationException(Response.status(status).entity(new Gson().toJson(responseDtos)).build());

        }

    }

}
