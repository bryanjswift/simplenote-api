package com.bryanjswift.simplenote.net;

import com.google.common.collect.ImmutableList;
import org.apache.http.Header;

import java.util.EmptyStackException;
import java.util.List;

/** @author bryanjswift */
public class Headers {
    public static final Headers EMPTY = new Headers(new Header[] {});
    private final Header[] headers;
    public Headers(final Header[] headers) {
        this.headers = headers;
    }
    public String get(final String name) {
        for (final Header h : headers) {
            if (h.getName().equals(name)) {
                return h.getValue();
            }
        }
        return null;
    }
    public List<String> getAll(final String name) {
        final ImmutableList.Builder<String> b = ImmutableList.builder();
        for (final Header h : headers) {
            if (h.getName().equals(name)) {
                b.add(h.getValue());
            }
        }
        return b.build();
    }
}
