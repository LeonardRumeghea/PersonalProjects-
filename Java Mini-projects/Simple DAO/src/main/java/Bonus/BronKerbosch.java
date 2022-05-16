package Bonus;

import DAO.Cities;

import java.sql.SQLException;
import java.util.*;

public class BronKerbosch {
    private final RandomSistersTool rst;
    private final Cities cityDAO = new Cities();
    private Collection<Set<String>> cliques = new ArrayList<>();

    public BronKerbosch (RandomSistersTool rst) {
        this.rst = rst;
    }

    public Collection<Set<String>> getAllMaximalCliques()
    {
            List<String> potential_clique = new ArrayList<>();
        List<String> already_found = new ArrayList<>();
        List<String> candidates = new ArrayList<>(rst.cities);
        try {
            findCliques(potential_clique,candidates,already_found);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliques;
    }

    public Collection<Set<String>> getBiggestMaximalCliques()
    {
            cliques = getAllMaximalCliques();

            int maximum = 0;
            Collection<Set<String>> biggest_cliques = new ArrayList<>();
            for (Set<String> clique : cliques) {
                if (maximum < clique.size()) {
                    maximum = clique.size();
                }
            }
            for (Set<String> clique : cliques) {
                if (maximum == clique.size()) {
                    biggest_cliques.add(clique);
                }
            }
            return biggest_cliques;
    }

        private void findCliques(List<String> potential_clique, List<String> candidates, List<String> already_found) throws SQLException {
            System.out.println("Am intrat in functia recursiva.");
            List<String> candidates_array = new ArrayList<>(candidates);
            if (!end(candidates, already_found)) {
                for (String candidate : candidates_array) {
                    List<String> new_candidates = new ArrayList<>();
                    List<String> new_already_found = new ArrayList<>();

                    potential_clique.add(candidate);
                    candidates.remove(candidate);

                    for (String new_candidate : candidates) {
                        if (isNeighbor(candidate, new_candidate))
                        {
                            new_candidates.add(new_candidate);
                        }
                    }

                    for (String new_found : already_found) {
                        if (isNeighbor(candidate, new_found)) {
                            new_already_found.add(new_found);
                        }
                    }

                    if (new_candidates.isEmpty() && new_already_found.isEmpty()) {
                        cliques.add(new HashSet<>(potential_clique));
                    }
                    else {
                        findCliques(potential_clique, candidates, already_found);
                    }

                    already_found.add(candidate);
                    potential_clique.remove(candidate);
                }
            }
        }

        private boolean end(List<String> candidates, List<String> already_found) throws SQLException {
            // if a node in already_found is connected to all nodes in candidates
            boolean end = false;
            int edgecounter;
            for (String found : already_found) {
                edgecounter = 0;
                for (String candidate : candidates) {
                    if (isNeighbor(found, candidate)) {
                        edgecounter++;
                    }
                }
                if (edgecounter == candidates.size()) {
                    end = true;
                }
            }
            return end;
        }

        private boolean isNeighbor(String vertex1, String vertex2) throws SQLException {
            int id1 = cityDAO.findByName(vertex1).getId();
            int id2 = cityDAO.findByName(vertex2).getId();
            for(Map.Entry<Integer,Integer> entry : rst.sisters.entrySet()) {
                if((entry.getKey() == id1 && entry.getValue() == id2) || (entry.getKey() == id2 && entry.getValue() == id1)) {
                    return true;
                }
            }
            return false;
        }
}
