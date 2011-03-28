package com.bryanjswift.simplenote.net;

import com.bryanjswift.simplenote.Constants;
import com.bryanjswift.simplenote.model.Credentials;
import com.bryanjswift.simplenote.model.Note;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @author bryanjswift */
public class SimplenoteApi {
    private static final Logger logger = LoggerFactory.getLogger(SimplenoteApi.class);
    private final String userAgent;
    private final Credentials creds;

    /**
     * Create API instance with useragent identifier
     * @param ua User-Agent header string to identify client
     */
    public SimplenoteApi(final String ua) {
        this(ua, null);
    }

    /**
     * Create API instance with useragent and account information
     * @param ua User-Agent header string to identify client
     * @param credentials to use when accessing the API
     */
    public SimplenoteApi(final String ua, final Credentials credentials) {
        this.userAgent = ua;
        this.creds = credentials;
    }

    /**
     * Retrieve an update API instance with passed in creds
     * @param credentials to use when accessing the API
     * @return a SimplenoteApi instance
     */
    public SimplenoteApi setCredentials(final Credentials credentials) {
        return new SimplenoteApi(userAgent, credentials);
    }

    /**
     * Get auth token from API for given creds
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

    /**
     * Send a note to the API to create it for the user identified by instance's credentials
     * @param toSave Note to create
     * @return ApiResponse containing Note contained in server response
     */
    public ApiResponse<Note> create(final Note toSave) {
        final String url = String.format(Constants.API_NOTE_CREATE_URL, creds.auth, creds.email);
        String data = "";
        try {
            data = toSave.json().toString();
        } catch (JSONException jsone) {
            logger.error("Disaster while getting JSON representation", jsone);
        }
        final ApiResponse<String> response = Api.Post(userAgent, url, data);
        Note note = new Note();
        try {
            note = new Note(response.payload);
        } catch (JSONException jsone) {
            logger.error("Unable to create Note from response {}", response.payload, jsone);
        }
        final ApiResponse<Note> result = new ApiResponse<Note>(response.status, note, response.headers);
        return result;
    }
}
