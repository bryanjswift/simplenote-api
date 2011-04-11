package com.bryanjswift.simplenote.net;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.apache.http.Header;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Set;

/**
 * Meant to mimic the interface of getting Header data from a org.apache.http.HttpResponse
 *
 * @author bryanjswift
 */
public class Headers {
    public static final Headers EMPTY = new Headers(new Header[] {});
    private final Header[] headers;
    private Set<String> keys;

    /**
     * Create an instance of Headers from an Array of HTTP Headers
     * @param headers to be stored for later access
     */
    public Headers(final Header[] headers) {
        this.headers = headers;
    }

    /**
     * Check if there is a value for a given header name
     * @param name of the header to look for
     * @return true if this contains a header with name
     */
    public boolean contains(final String name) {
        return keys().contains(name);
    }

    /**
     * Get the value of the first Header encountered with a given name
     * @param name of Header to retrieve value for
     * @return first Header value encountered for provided name
     */
    public String get(final String name) {
        for (final Header h : headers) {
            if (h.getName().equals(name)) {
                return h.getValue();
            }
        }
        return null;
    }

    /**
     * Retrieve a List of values for a given Header name
     * @param name of Headers to look for
     * @return List of String values for a given header name
     */
    public List<String> getAll(final String name) {
        final ImmutableList.Builder<String> b = ImmutableList.builder();
        for (final Header h : headers) {
            if (h.getName().equals(name)) {
                b.add(h.getValue());
            }
        }
        return b.build();
    }

    /**
     * Retrieve the set of header names contained
     * @return unique set of names represented by this Headers instance
     */
    public Set<String> keys() {
        final Set<String> result;
        if (keys != null) {
            final ImmutableSet.Builder<String> b = ImmutableSet.builder();
            for (final Header h : headers) {
                b.add(h.getName());
            }
            result = b.build();
        } else {
            result = keys;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode((Object[]) headers);
    }

    @Override
    public boolean equals(final Object o) {
        final boolean result;
        if (o instanceof Headers) {
            final Headers h = (Headers) o;
            result = Objects.equal(this.headers, h.headers);
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public String toString() {
        final Objects.ToStringHelper helper = Objects.toStringHelper(this);
        helper.addValue(keys());
        return helper.toString();
    }
}
