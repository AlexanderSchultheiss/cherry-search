package util;

import java.util.Date;
import java.util.Objects;

public final class Commit {
    private final String id;
    private final Branch branch;
    private final String message;
    private final Date timestamp;

    Commit(String id, Branch branch, String message, Date timestamp) {
        this.id = id;
        this.branch = branch;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String id() {
        return id;
    }

    public Branch branch() {
        return branch;
    }

    public String message() {
        return message;
    }

    public Date timestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Commit[" +
                "id=" + id + ", " +
                "branch=" + branch + ", " +
                "message=" + message + ", " +
                "timestamp=" + timestamp + ']';
    }

    public boolean after(Commit commit) {
        return this.timestamp().after(commit.timestamp());
    }

    public boolean before(Commit commit) {
        return this.timestamp().before(commit.timestamp());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commit commit = (Commit) o;
        return Objects.equals(id, commit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, timestamp);
    }
}
