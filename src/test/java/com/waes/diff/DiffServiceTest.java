package com.waes.diff;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Tests diff service
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */
public class DiffServiceTest {

    private DiffService diffService;

    @Before
    public void setUp() throws Exception {
        DiffRepository diffRepository = Mockito.mock(DiffRepository.class);
        diffService = new DiffService(diffRepository);
    }

    @Test
    public void saveLeft() throws Exception {
        ComparisonData<String> comparisonData = new ComparisonData<>("1111");
        Mockito.when(diffService.findComparisonDataById("1111")).thenReturn(comparisonData);

        diffService.saveLeft("1111","Nishanthan");
        assertEquals("Nishanthan",diffService.findComparisonDataById("1111").getLeft());
    }

    @Test(expected = LeftExistException.class)
    public void saveLeft_withLeftExist() throws Exception {
        ComparisonData<String> comparisonData = new ComparisonData<>("1111");
        comparisonData.setLeft("Nishanthan");
        Mockito.when(diffService.findComparisonDataById("1111")).thenReturn(comparisonData);
        diffService.saveLeft("1111","Nishanthan");
    }

    @Test
    public void saveRight() throws Exception {
        ComparisonData<String> comparisonData = new ComparisonData<>("1111");
        Mockito.when(diffService.findComparisonDataById("1111")).thenReturn(comparisonData);

        diffService.saveRight("1111","Jayanthan");


        assertEquals("Jayanthan",diffService.findComparisonDataById("1111").getRight());
    }

    @Test(expected = RightExistException.class)
    public void saveRight_withRightExist() throws Exception {
        ComparisonData<String> comparisonData = new ComparisonData<>("1111");
        comparisonData.setRight("Nishanthan");
        Mockito.when(diffService.findComparisonDataById("1111")).thenReturn(comparisonData);

        diffService.saveRight("1111","Jayanthan");
    }

    @Test
    public void findDiffById_withSameSizeDifferentWord() throws Exception {
        ComparisonData<String> comparisonData = new ComparisonData<>("1111");
        comparisonData.setLeft("Nishanthan");
        comparisonData.setRight("Nishanyttn");
        Mockito.when(diffService.findComparisonDataById(Mockito.anyString())).thenReturn(comparisonData);

        Result result = diffService.findDiffById("1111");

        assertTrue(result.isAreSizeEqual());
        assertFalse(result.isAreEqual());
        assertEquals(3,result.getDiffList().size());
    }

    @Test
    public void findDiffById_withDifferentSizeWord() throws Exception {
        ComparisonData<String> comparisonData = new ComparisonData<>("1111");
        comparisonData.setLeft("Monkey");
        comparisonData.setRight("Cat");
        Mockito.when(diffService.findComparisonDataById("1111")).thenReturn(comparisonData);

        Result result = diffService.findDiffById("1111");

        assertFalse(result.isAreSizeEqual());
        assertFalse(result.isAreEqual());
    }


    @Test(expected = ComparisonNotFoundException.class)
    public void findDiffById_withNonExistId() throws Exception {
        ComparisonData<String> comparisonData = new ComparisonData<>("1111");
        comparisonData.setLeft("Monkey");
        comparisonData.setRight("Cat");
        Mockito.when(diffService.findComparisonDataById("1111")).thenReturn(comparisonData);

        Result result = diffService.findDiffById("2222");
    }
}