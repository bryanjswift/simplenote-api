package com.bryanjswift.simplenote;

import com.bryanjswift.simplenote.model.Note;
import com.google.common.collect.ImmutableList;
import org.joda.time.DateTime;

import java.util.List;

/** @author bryanjswift */
public class Constants {
    // Defaults
    public static final String DEFAULT_KEY = "__SN_DEFAULT_KEY__";
    public static final int DEFAULT_VERSION = -12389;
    public static final List<Note> EMPTY_LIST = (new ImmutableList.Builder<Note>()).build();
    public static final String DEFAULT_INDEX_MARK = null;
    public static final DateTime DEFAULT_INDEX_SINCE = null;
    public static final int DEFAULT_INDEX_LENGTH = 100;
    // API Base URL
    public static final String BASE_URL = "https://simple-note.appspot.com";
    public static final String API_BASE_URL = BASE_URL + "/api";
    public static final String API2_BASE_URL = BASE_URL + "/api2";
    public static final String API_LOGIN_URL  = API_BASE_URL + "/login";
    public static final String API_REGISTER_URL = BASE_URL + "/create";
    public static final String API_NOTE_CREATE_URL = API2_BASE_URL + "/data?auth=%s&email=%s";
    public static final String API_LIST_URL = API2_BASE_URL + "/index?auth=%s&email=%s";
    private Constants() { }
}
