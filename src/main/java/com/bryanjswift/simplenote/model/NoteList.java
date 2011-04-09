package com.bryanjswift.simplenote.model;

import com.bryanjswift.simplenote.Constants;

import java.util.List;

/**
 * Represents the result of a call to the index API
 * @author bryanjswift
 */
public class NoteList {
    public final String mark;
    public final int count;
    public final List<Note> notes;

    public static final NoteList EMPTY = new NoteList(null, 0, Constants.EMPTY_LIST);

    public NoteList(final String mark, final int count, final List<Note> notes) {
        this.mark = mark;
        this.count = count;
        this.notes = notes;
    }
}
