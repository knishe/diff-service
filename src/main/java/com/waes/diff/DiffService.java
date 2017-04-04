package com.waes.diff;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores, Compare and find the difference between two text
 *
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */
public class DiffService {
    private DiffRepository repository;

    public DiffService() {
        repository = MemoryDiffRepository.getInstance();
    }

    public DiffService(DiffRepository repository) {
        this.repository = repository;
    }


    /**
     * Save left data for comparison
     *
     * @param id the id of the comparison data stored
     * @param leftText left data to be stored
     * @throws LeftExistException throws when a data exist for left
     */
    void saveLeft(String id, String leftText) throws LeftExistException {
        ComparisonData<String> comparison = getDefaultOnEmpty(id);

        if (comparison.isLeftEmpty()) {
            comparison.setLeft(leftText);
        } else {
            throw new LeftExistException();
        }
        repository.save(comparison);
    }

    private ComparisonData getDefaultOnEmpty(String id) {
        ComparisonData comparison = repository.findTextDataById(id);
        return comparison != null ? comparison : new ComparisonData<>(id);
    }

    /**
     * Save right data for comparison
     *
     * @param id the id of the comparison data to be stored
     * @param rightText right data to be stored
     * @throws RightExistException throws when a data exist for right
     */
    void saveRight(String id, String rightText) throws RightExistException {
        ComparisonData existingComparison = getDefaultOnEmpty(id);

        if (existingComparison.isRightEmpty()) {
            existingComparison.setRight(rightText);
        } else {
            throw new RightExistException();
        }
        repository.save(existingComparison);
    }

    ComparisonData findComparisonDataById(String id) {
        return repository.findTextDataById(id);
    }

    /**
     * Provides the comparison information
     * @param id the id of the comparison data
     * @return the results consists the comparison informations as {@link Result}
     * @throws ComparisonNotFoundException throw when a comparison is not exist
     */
    Result findDiffById(String id) throws ComparisonNotFoundException {
        ComparisonData<String> data = repository.findTextDataById(id);

        if(data == null){
            throw new ComparisonNotFoundException(id);
        }

        String decodedLeft = Base64Utils.getDecodedString(data.getLeft());
        String decodedRight= Base64Utils.getDecodedString(data.getRight());

        if (decodedLeft.equals(decodedRight)) {
            return new Result(Boolean.TRUE, Boolean.TRUE);
        } else if (decodedLeft.length() != decodedRight.length()) {
            return new Result(Boolean.FALSE, Boolean.FALSE);
        } else {
            return getResult(decodedLeft,decodedRight);
        }
    }

    private Result getResult(String decodedLeft,String decodedRight) {
        Result result = new Result(Boolean.FALSE, Boolean.TRUE);
        char[] left = decodedLeft.toCharArray();
        char[] right = decodedRight.toCharArray();

        result.setDiffList(getDiffList(left, right));

        return result;
    }

    private List<Diff> getDiffList(char[] left, char[] right) {
        List<Diff> diffList = new ArrayList<>();
        int length = 0;
        for (int i = 0; i < left.length; i++) {
            if (left[i] == right[i]) {
                length = 0;
            } else {
                length++;
                diffList.add(new Diff(length, i));
            }
        }
        return diffList;
    }


}
