package com.bryanjswift.simplenote;

/** @author bryanjswift */
public class Constants {
    public static final String TAG = "SimplenoteApi";
    // API Base URL
    public static final String BASE_URL = "https://simple-note.appspot.com";
    public static final String CREATE_ACCOUNT_URL = BASE_URL + "/createaccount.html";
    public static final String API_BASE_URL   = BASE_URL + "/api";
    public static final String API_LOGIN_URL  = API_BASE_URL + "/login";                // POST
    public static final String API_REGISTER_URL = BASE_URL + "/create";                 // POST
    public static final String API_NOTES_URL  = API_BASE_URL + "/index";                // GET
    public static final String API_NOTE_URL   = API_BASE_URL + "/note";                 // GET
    public static final String API_UPDATE_URL = API_BASE_URL + "/note";                 // POST
    public static final String API_DELETE_URL = API_BASE_URL + "/delete";               // GET
    public static final String API_SEARCH_URL = API_BASE_URL + "/search";               // GET
    private Constants() { }
}
