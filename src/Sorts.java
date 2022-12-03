/*
 * NAME: Gino Angelici
 * PID:  A16779788
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Sorts class.
 * @param <T> Generic type
 * @author Gino Angelici
 * @since  ${10/30/22}
 */
public class Sorts<T extends Comparable<? super T>> {

    private static final int MIDDLE_IDX = 2;

    /**
     * This method performs insertion sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void InsertionSort(ArrayList<T> list, int start, int end) {
        for (int i = start+1; i <= end; i++) {
            int j = i;
            while (j > start && (list.get(j)).compareTo(list.get(j - 1)) < 0) {
                // Swap list[j] and list[j - 1]
                T temp = list.get(j);
                list.set(j, list.get(j - 1));
                list.set(j - 1, temp);
                j--;
            }
        }
    }

    /**
     * This method performs merge sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void MergeSort(ArrayList<T> list, int start, int end) {
        if (start < end)
        {
            int mid = start + (end - start) / MIDDLE_IDX;
            MergeSort(list, start, mid);
            MergeSort(list , mid + 1, end);

            merge(list, start, mid, end);
        }
    }

    /**
     * merge helper function for MergeSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param m the middle index we want to merge
     * @param r right-most index we want to merge
     */
    private void merge(ArrayList<T> arr, int l, int m, int r) {

        int mergedSize = r - l + 1;

        ArrayList<T> mergedNums = new ArrayList<>();
        int left = l, right = m + 1;
        while (left <= m && right <= r) {
            if (arr.get(left).compareTo(arr.get(right)) <= 0) {
                mergedNums.add(arr.get(left));
                left++;
            }
            else {
                mergedNums.add(arr.get(right));
                right++;
            }
        }

        while (left <= m) {
            mergedNums.add(arr.get(left));
            left++;
        }
        while (right <= r) {
            mergedNums.add(arr.get(right));
            right++;
        }
        for (int i = 0; i < mergedSize; i++) {
            arr.set(l + i, mergedNums.get(i));
        }
    }

    /**
     * This method performs quick sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void QuickSort(ArrayList<T> list, int start, int end) {
        // Only attempt to sort the array segment if there are
        // at least 2 elements
        if (end <= start) {
            return;
        }

        // Partition the array segment
        int high = partition(list, start, end);

        // Recursively sort the left segment
        QuickSort(list, start, high);

        // Recursively sort the right segment
        QuickSort(list, high + 1, end);
    }

    /**
     * partition helper function for QuickSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param h right-most index we want to merge
     */
    private int partition(ArrayList<T> arr, int l, int h) {
        // Select the middle value as the pivot.
        int midpoint = l + (h - l) / MIDDLE_IDX;
        T pivot = arr.get(midpoint);

        // "low" and "high" start at the ends of the array segment
        // and move towards each other.
        int low = l;
        int high = h;

        boolean done = false;
        while (!done) {
            // Increment low while numbers[low] < pivot
            while (arr.get(low).compareTo(pivot) < 0) {
                low = low + 1;
            }

            // Decrement high while pivot < arr.get(high)
            while (arr.get(high).compareTo(pivot) > 0) {
                high = high - 1;
            }

            // If low and high have crossed each other, the loop
            // is done. If not, the elements are swapped, low is
            // incremented and high is decremented.
            if (low >= high) {
                done = true;
            }
            else {
                T temp = arr.get(low);
                arr.set(low, arr.get(high));
                arr.set(high, temp);
                low++;
                high--;
            }
        }

        // "high" is the last index in the left segment.
        return high;
    }

    /**
     * This method performs a modified QuickSort that switches to insertion sort
     * after a certain cutoff
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     * @param cutoff the minimum length of an subsection of the arraylist
     *               such that we switch to Insertion Sort
     */
    public void Modified_QuickSort(ArrayList<T> list, int start, int end, int cutoff) {
        // Only attempt to sort the array segment if there are
        // at least 2 elements
        if (end <= start) {
            return;
        }

        // Partition the array segment
        int high = partition(list, start, end);

        // Recursively sort the left segment
        if (end - start <= 3) {
            InsertionSort(list, start, high);
            return;
        } else {
            Modified_QuickSort(list, start, high, cutoff);
        }

        // Recursively sort the right segment
        if (end - start <= 3) {
            InsertionSort(list, start, high);
            return;
        } else {
            Modified_QuickSort(list, high + 1, end, cutoff);
        }
    }

    /**
     * This method performs cocktail sort on the input list
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void cocktailSort(ArrayList<T> list, int start, int end){
        boolean swapped = true;

        while (swapped)
        {
            swapped = false;

            for (int i = start; i <= end - 1; ++i)
            {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    T temp = list.get(i);
                    list.set(i, list.get(i+1));
                    list.set(i+1, temp);
                    swapped = true;
                }
            }

            if (!swapped)
                break;

            swapped = false;

            end = end - 1;

            for (int i = end; i >= start; i--)
            {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    T temp = list.get(i);
                    list.set(i, list.get(i+1));
                    list.set(i+1, temp);
                    swapped = true;
                }
            }

            start = start + 1;
        }
    }

    /**
     * this helper finds the appropriate number of buckets you should allocate
     * based on the range of the values in the input list
     * @param list the input list to bucket sort
     * @return number of buckets
     */
    private int assignNumBuckets(ArrayList<T> list) {
        T max = Collections.max(list);
        T min = Collections.min(list);
        return (int) Math.sqrt((Double) max - (Double) min) + 1;
    }

    /**
     * this helper finds the appropriate bucket index that a data should be
     * placed in
     * @param data a particular data from the input list if you are using
     *             loop iteration
     * @param numBuckets number of buckets
     * @param listMin the smallest element of the input list
     * @return the index of the bucket for which the particular data should
     * be placed in
     */
    private int assignBucketIndex(T data, int numBuckets, T listMin) {
        return (int) (((Double) data - (Double) listMin) / numBuckets);
    }

    /**
     * This method performs bucket sort on the input list
     *
     * @param list The arraylist we want to sort
     */
    public ArrayList<T> bucketSort(ArrayList<T> list) {
        int numBuckets = assignNumBuckets(list);
        ArrayList<T>[] bucketArray = new ArrayList[numBuckets];
        T min = Collections.min(list);
        for (int i = 0; i < bucketArray.length; i++) {
            bucketArray[i] = new ArrayList<>();
        }
        for (int i = 0; i < list.size(); i++) {
            int index = assignBucketIndex(list.get(i), numBuckets, min);
            T add = list.get(i);
            bucketArray[index].add(add);
        }
        ArrayList<T> returnArrayList = new ArrayList<>();
        for (int i = 0; i < bucketArray.length; i++) {
            ArrayList toSort = bucketArray[i];
            InsertionSort(toSort, 0, toSort.size()-1);
            returnArrayList.addAll(toSort);
        }
        for (int i = 0; i < returnArrayList.size(); i++) {
            list.set(i, returnArrayList.get(i));
        }
        return list;
    }

}