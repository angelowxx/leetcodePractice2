import java.util.Random;

/**
 * @Author: Wang Xinxiang
 * @Description: ways of sorting, Bucket and Radix
 * @DateTime: 7/27/2023 12:11 PM
 */

public class Sort {
    static int[] GenerateArray(int n, int k){
        Random random = new Random();
        int[] array = new int[n];
        for(int i=0; i<n; i++){
            array[i] = random.nextInt(k+1);
        }
        return array;
    }
    static void BucketSort(int[] array, int k){
        int[] count = new int[k+1];
        for(int i=0; i < array.length; i++){
            count[array[i]]++;
        }
        for(int i=0, j=0; i < array.length && j < k+1; j++){
            for(int n=0; n<count[j]; n++){
                array[i++] = j;
            }
        }
    }

    static void RadixSort(int[] array){

    }

}
