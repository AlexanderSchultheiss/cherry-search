package cherry;

import util.Commit;

import java.util.Objects;

public final class CherrySource {
    private final Commit commit;

    CherrySource(Commit commit) {
        this.commit = commit;
    }

    public Commit commit() {
        return commit;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CherrySource) obj;
        return Objects.equals(this.commit, that.commit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commit);
    }

    @Override
    public String toString() {
        return "CherrySource[" +
                "commit=" + commit + ']';
    }

}
