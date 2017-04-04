package com.waes.diff;

/**
 * In memory storage to keep comparison data
 *
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */
public interface DiffRepository {
    /**
     * Finds the Comparison data by the id
     *
     * @param id the id of comparison data
     * @return the comparison data as {@link ComparisonData}
     */
    ComparisonData findTextDataById(String id);

    /**
     * Saves the comparison data
     *
     * @param comparison the comparison data as {@link ComparisonData}
     */
    void save(ComparisonData comparison);
}
