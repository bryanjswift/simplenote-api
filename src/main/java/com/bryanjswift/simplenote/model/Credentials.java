package com.bryanjswift.simplenote.model;

/** @author bryanjswift */
public class Credentials {
    public final String auth;
    public final String email;
    public final String password;

    /**
     * Construct credentials from all three fields
     * @param auth token for communicating securely with API
     * @param email user identifier sent to server
     * @param password sent during the login procedure
     */
    public Credentials(final String auth, final String email, final String password) {
        this.auth = auth;
        this.email = email;
        this.password = password;
    }

    /**
     * Create simple auth, without a password
     * @param auth token for communicating securely with API
     * @param email user identifier sent to server
     */
    public Credentials(final String auth, final String email) {
        this(auth, email, null);
    }
}
