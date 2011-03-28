package com.bryanjswift.simplenote;

/** @author bryanjswift */
public class Constants {
    public static final String TAG = "SimplenoteApi";
    // API Base URL
    public static final String BASE_URL = "https://simple-note.appspot.com";
    public static final String API_BASE_URL = BASE_URL + "/api";
    public static final String API2_BASE_URL = BASE_URL + "/api2";
    public static final String API_LOGIN_URL  = API_BASE_URL + "/login";
    public static final String API_REGISTER_URL = BASE_URL + "/create";
    public static final String API_NOTE_CREATE_URL = API2_BASE_URL + "/data?auth=%s&email=%s";
    private Constants() { }
}
