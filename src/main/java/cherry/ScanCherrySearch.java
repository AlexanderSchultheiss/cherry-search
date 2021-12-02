package cherry;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Commit;
import util.Repository;

import java.io.IOException;
import java.util.*;

public class ScanCherrySearch implements CherrySearch {
    final Logger LOGGER = LoggerFactory.getLogger(ScanCherrySearch.class);
    private Repository repository;

    public ScanCherrySearch(Repository repo) throws IOException {
        repository = repo;
    }

    @Override
    public List<CherryPick> findAllCherryPicks() throws GitAPIException, IOException {
        List<CherryPick> cherryPicks = new ArrayList<>();
        Map<String, Set<Commit>> patchid2commits = new HashMap<>();

        Set<Commit> commits = repository.getAllCommits();

        for (Commit c : commits) {
            Optional<String> patchID = repository.getPatchId(c);

            if(patchID.isPresent()){
                if (patchid2commits.containsKey(patchID.get())) {
                    patchid2commits.get(patchID.get()).add(c);
                } else {
                    Set<Commit> similarCommits = new HashSet<>();
                    similarCommits.add(c);
                    patchid2commits.put(patchID.get(), similarCommits);
                }
            }
        }

        for (Set<Commit> commitSet : patchid2commits.values()) {
            if (commitSet.size() > 1) {
                final List<Commit> commitList = new ArrayList<Commit>(commitSet);

                for (int i = 0; i < commitList.size() - 1; ++i) {
                    for (int j = i+1; j < commitList.size(); ++j){
                        cherryPicks.add(CherryPick.determineSourceAndTarget(commitList.get(i), commitList.get(j)));
                    }
                }
            }
        }

        return cherryPicks;
    }
}