package com.fsbs.src.utils;


import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import com.fsbs.src.model.ErrorResponse;
import com.fsbs.src.network.APIFsBs;

public class ErrorUtils {
    public static ErrorResponse parseError(Response<?> response){
        Converter<ResponseBody, ErrorResponse> converter = APIFsBs.getAPIVnProduct()
                .responseBodyConverter(ErrorResponse.class,new Annotation[0]);
        ErrorResponse errorResponse;
        try {
            assert response.errorBody() != null;
            errorResponse = converter.convert(response.errorBody());
        }catch (IOException e){
            return new ErrorResponse();
        }
        return errorResponse;
    }
}
