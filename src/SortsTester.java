import java.util.*;

public class SortsTester {
    Sorts<Integer> Sorting;
    int[] toSortArray;
    double[] toBucketArray;
    ArrayList<Integer> toSort;

    @org.junit.Before
    public void setUp() throws Exception {
        Sorting = new Sorts<>();
        toSortArray = new int[]{10, 2, 78, 4, 45, 32, 7, 11};
        toSort = new ArrayList<>();
        for (int j : toSortArray) {
            toSort.add(j);
        }
    }

    @org.junit.Test
    public void insertionSort() {
        System.out.println(toSort.toString());
        Sorting.InsertionSort(toSort, 0, 7);
        System.out.println(toSort.toString());
    }

    @org.junit.Test
    public void mergeSort() {
        System.out.println(toSort.toString());
        Sorting.MergeSort(toSort, 0, 7);
        System.out.println(toSort.toString());
    }

    @org.junit.Test
    public void quickSort() {
        System.out.println(toSort.toString());
        Sorting.QuickSort(toSort, 0, 7);
        System.out.println(toSort.toString());
    }

    @org.junit.Test
    public void modified_QuickSort() {
        System.out.println(toSort.toString());
        Sorting.Modified_QuickSort(toSort, 0, 7, 3);
        System.out.println(toSort.toString());

    }

    @org.junit.Test
    public void cocktailSort() {
        System.out.println(toSort);
        Sorting.cocktailSort(toSort, 0, 7);
        System.out.println(toSort);
    }

    @org.junit.Test
    public void bucketSort() {
        ArrayList inputDataArray = new ArrayList(Arrays.asList(10.0, 2.0, 78.0, 4.0, 45.0, 32.0, 7.0, 11.0));
        System.out.println(inputDataArray);
        Sorting.bucketSort(inputDataArray);
        System.out.println(inputDataArray);
    }
}