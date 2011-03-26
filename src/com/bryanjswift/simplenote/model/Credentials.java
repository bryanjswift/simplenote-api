package com.bryanjswift.simplenote.model;

/** @author bryanjswift */
public class Credentials {
    public final String auth;
    public final String email;
    public final String password;
    public Credentials(final String auth, final String email, final String password) {
        this.auth = auth;
        this.email = email;
        this.password = password;
    }
    public Credentials(final String auth, final String email) {
        this(auth, email, null);
    }
}
