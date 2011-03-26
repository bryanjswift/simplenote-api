package com.bryanjswift.simplenote.net;

import com.bryanjswift.simplenote.Constants;
import com.bryanjswift.simplenote.model.Credentials;
import com.bryanjswift.simplenote.util.Base64;

/** @author bryanjswift */
public class SimplenoteApi {
    private SimplenoteApi() { }
    public static ApiResponse<Credentials> login(final String email, final String password) {
        final String params = String.format("email=%s&password=%s", email, password);
        final String data = Api.encode(params);
        final String url = Constants.API_LOGIN_URL;
        final ApiResponse<String> response = Api.Post(url, data);
        return new ApiResponse<Credentials>(response.status, new Credentials(response.payload, email));
    }
}
