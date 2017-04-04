package com.waes.diff;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */
public class MemoryDiffRepository implements DiffRepository {
    private Map<String,ComparisonData> comparisonDataMap = new ConcurrentHashMap<>();
    private static final MemoryDiffRepository memoryDiffRepository = new MemoryDiffRepository();

    public static MemoryDiffRepository getInstance(){
        return memoryDiffRepository;
    }

    @Override
    public ComparisonData findTextDataById(String id) {
        return comparisonDataMap.get(id);
    }

    @Override
    public void save(ComparisonData comparisonData) {
        comparisonDataMap.put(comparisonData.getId(),comparisonData);
    }
}
