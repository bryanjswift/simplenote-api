package com.bryanjswift.simplenote.model;

import com.bryanjswift.simplenote.Constants;
import com.google.common.collect.ImmutableList;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Note {
    private static final Logger logger = LoggerFactory.getLogger(Note.class);

    public final String key;
    public final boolean deleted;
    public final DateTime modifydate;
    public final DateTime createdate;
    public final int syncnum;
    public final int version;
    public final int minversion;
    public final String sharekey;
    public final String publishkey;
    public final List<String> systemtags;
    public final List<String> tags;
    public final String content;

    /**
     * Create an empty Note
     */
    public Note() {
        this(null, false, null, null, 0, 0, 0, null, null, null, null, null);
    }

    /**
     * Create a Note instance from provided values
     * @param key to set
     * @param deleted to set
     * @param modifydate to set
     * @param createdate to set
     * @param syncnum to set
     * @param version to set
     * @param minversion to set
     * @param sharekey to set
     * @param publishkey to set
     * @param systemtags to copy and set
     * @param tags to copy and set
     * @param content to set
     */
    public Note(final String key, final boolean deleted, final DateTime modifydate,
                final DateTime createdate, final int syncnum, final int version,
                final int minversion, final String sharekey, final String publishkey,
                final List<String> systemtags, final List<String> tags, final String content) {
        this.key = key;
        this.deleted = deleted;
        this.modifydate = modifydate;
        this.createdate = createdate;
        this.syncnum = syncnum;
        this.version = version;
        this.minversion = minversion;
        this.sharekey = sharekey;
        this.publishkey = publishkey;
        ImmutableList.Builder<String> systemtagsBuilder = ImmutableList.builder();
        systemtagsBuilder.addAll(systemtags);
        this.systemtags = systemtagsBuilder.build();
        ImmutableList.Builder<String> tagsBuilder = ImmutableList.builder();
        tagsBuilder.addAll(tags);
        this.tags = tagsBuilder.build();
        this.content = content;
    }

    /**
     * Create a Note instance from provided values
     * @param deleted to set
     * @param modifydate to set
     * @param createdate to set
     * @param systemtags to copy and set
     * @param tags to copy and set
     * @param content to set
     */
    public Note(final boolean deleted, final DateTime modifydate, final DateTime createdate,
                final List<String> systemtags, final List<String> tags, final String content) {
        this.key = Constants.DEFAULT_KEY;
        this.deleted = deleted;
        this.modifydate = modifydate;
        this.createdate = createdate;
        this.syncnum = Constants.DEFAULT_VERSION;
        this.version = Constants.DEFAULT_VERSION;
        this.minversion = Constants.DEFAULT_VERSION;
        this.sharekey = null;
        this.publishkey = null;
        ImmutableList.Builder<String> systemtagsBuilder = ImmutableList.builder();
        systemtagsBuilder.addAll(systemtags);
        this.systemtags = systemtagsBuilder.build();
        ImmutableList.Builder<String> tagsBuilder = ImmutableList.builder();
        tagsBuilder.addAll(tags);
        this.tags = tagsBuilder.build();
        this.content = content;
    }

    /**
     * Create a note from a JSON string
     * @param json to pass to JSONObject constructor
     * @throws JSONException if a problem occurs while creating or reading JSONObject
     */
    public Note(final String json) throws JSONException {
        this(new JSONObject(json));
    }

    /**
     * Create a Note from a JSONObject by fetching values out of it
     * @param o JSONObject containing values to populate the note
     * @throws JSONException if an exception is thrown getting data from o
     */
    public Note(final JSONObject o) throws JSONException {
        this.key = o.optString("key", null);
        this.deleted = intAsBoolean(o.optInt("deleted", 0));
        this.modifydate = longAsDate(o.optLong("modifydate", 0));
        this.createdate = longAsDate(o.optLong("createdate", 0));
        this.syncnum = o.optInt("syncnum", 0);
        this.version = o.optInt("version", 0);
        this.minversion = o.optInt("minversion", 0);
        this.sharekey = o.optString("sharekey", null);
        this.publishkey = o.optString("publishkey", null);
        this.systemtags = jsonArrayAsList(o.optJSONArray("systemtags"));
        this.tags = jsonArrayAsList(o.optJSONArray("tags"));
        this.content = o.optString("content", null);
    }

    /**
     * Convert a number of seconds into a DateTime
     * @param seconds since epoch
     * @return DateTime representing the instant seconds since epoch
     */
    private static DateTime longAsDate(final long seconds) {
        return new DateTime(seconds * 1000);
    }

    /**
     * Use Simplenote logic to coerce an integer to a boolean
     * @param i to treat as an integer
     * @return false if i != 0, true otherwise
     */
    private static boolean intAsBoolean(final int i) {
        return i != 0;
    }

    /**
     * Convert a JSONArray to an ImmutableList of Strings
     * @param a JSONArry to converto to a list
     * @return immutable list of Strings of the values in key's array
     * @throws JSONException if the underlying JSONObject does
     */
    private static List<String> jsonArrayAsList(final JSONArray a) throws JSONException {
        final ImmutableList.Builder<String> builder = ImmutableList.builder();
        if (a != null) {
            final int size = a.length();
            for (int i = 0; i < size; i++) {
                if (!a.isNull(i)) {
                    builder.add(a.optString(i));
                }
            }
        }
        return builder.build();
    }

    /**
     * Get a JSONObject representation of Note that can be sent to Simplenote servers
     * @return JSONObject with the modifiable properties of a note populated
     */
    public JSONObject json() {
        JSONObject o = null;
        try {
            o = new JSONObject();
            o.put("key", key);
            o.put("deleted", deleted ? 1 : 0);
            o.put("modifydate", modifydate.getMillis() / 1000);
            o.put("createdate", createdate.getMillis() / 1000);
            o.put("systemtags", systemtags);
            o.put("tags", tags);
            o.put("content", content);
        } catch (JSONException jsone) {
            logger.error("Unable to create Note from response {}", jsone);
        }
        return o;
    }
}
