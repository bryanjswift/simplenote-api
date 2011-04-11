package com.bryanjswift.simplenote.model;

import com.bryanjswift.simplenote.Constants;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

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
        final ImmutableList.Builder<Note> notesBuilder = ImmutableList.builder();
        notesBuilder.addAll(notes);
        this.notes = notesBuilder.build();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.mark, this.count, this.notes);
    }

    @Override
    public boolean equals(Object o) {
        final boolean result;
        if (o instanceof NoteList) {
            final NoteList list = (NoteList) o;
            result = Objects.equal(this.mark, list.mark) && Objects.equal(this.count, list.count) && Objects.equal(this.notes, list.notes);
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public String toString() {
        final Objects.ToStringHelper helper = Objects.toStringHelper(this);
        helper.add("mark", this.mark);
        helper.add("count", this.count);
        helper.addValue(this.notes);
        return helper.toString();
    }
}
