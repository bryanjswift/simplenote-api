package com.bryanjswift.simplenote.model;

import com.google.common.base.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hashCode(auth, email, password);
    }

    @Override
    public boolean equals(final Object o) {
        final boolean result;
        if (o instanceof Credentials) {
            final Credentials creds = (Credentials) o;
            result = Objects.equal(this.auth, creds.auth) && Objects.equal(this.email, creds.email)
                      && Objects.equal(this.password, creds.password);
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public String toString() {
        final Objects.ToStringHelper helper = Objects.toStringHelper(this);
        helper.add("email", this.email);
        helper.addValue(this.auth == null ? "has password" : "has auth");
        return helper.toString();
    }
}
