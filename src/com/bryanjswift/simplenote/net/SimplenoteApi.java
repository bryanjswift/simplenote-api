package com.bryanjswift.simplenote.net;

import com.bryanjswift.simplenote.Constants;
import com.bryanjswift.simplenote.model.Credentials;

/** @author bryanjswift */
public class SimplenoteApi {
    private final String userAgent;

    /**
     * Create API instance with useragent identifier
     * @param ua User-Agent header string to identify client
     */
    public SimplenoteApi(final String ua) {
        userAgent = ua;
    }

    /**
     * Get auth token from API for given credentials
     * @param email of user
     * @param password of user
     * @return An ApiResponse with Credentials information containing the returned auth token
     */
    public ApiResponse<Credentials> login(final String email, final String password) {
        final String params = String.format("email=%s&password=%s", email, password);
        final String data = Api.encode(params);
        final String url = Constants.API_LOGIN_URL;
        final ApiResponse<String> response = Api.Post(userAgent, url, data);
        return new ApiResponse<Credentials>(response.status, new Credentials(response.payload, email));
    }
}
