package cherry;

import cherry.CherrySource;
import cherry.CherryTarget;
import util.Commit;

import java.util.Objects;

public final class CherryPick {
    private final CherrySource source;
    private final CherryTarget target;

    CherryPick(CherrySource source, CherryTarget target) {
        this.source = source;
        this.target = target;
    }

    public CherrySource source() {
        return source;
    }

    public CherryTarget target() {
        return target;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CherryPick) obj;
        return Objects.equals(this.source, that.source) &&
                Objects.equals(this.target, that.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }

    @Override
    public String toString() {
        return "CherryPick[" +
                "source=" + source + ", " +
                "target=" + target + ']';
    }

    /**
     * Determines CherrySource and CherryTarget based on timestamps
     */
    public static CherryPick determineSourceAndTarget(Commit commit1, Commit commit2) {
        return commit1.after(commit2) ? createCherryPick(commit2, commit1) : createCherryPick(commit1, commit2);
    }

    /**
     * Computes new CherryPick from given source commit and target commit
     */
    public static CherryPick createCherryPick(Commit src, Commit target) {
        return new CherryPick(new CherrySource(src), new CherryTarget(target));
    }
}
