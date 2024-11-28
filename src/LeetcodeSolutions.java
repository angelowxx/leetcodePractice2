import java.util.*;

/**
 * @Author: Wang Xinxiang
 * @Description: 力扣练习题的解决函数
 * @DateTime: 2023/3/22 23:53
 */

public class LeetcodeSolutions {


    /**
    * @Author: Wang Xinxiang
    * @Description: 2217. 找到指定长度的回文数
    * @DateTime: 10/1/2023 10:29 AM
    * @Params:
    * @Return
    */
    public long[] kthPalindrome(int[] queries, int intLength) {
        long[] result = new long[queries.length];
        int max = 9;
        int maxL = intLength/2;
        if(intLength%2==0){
            maxL--;
        }
        for(int i=0; i<maxL; i++){
            max *= 10;
        }


        char[] cur = new char[intLength];
        cur[0] = '1';
        for(int i = 1; i < intLength-1; i++){
            cur[i] = '0';
        }
        for(int i=0; i<queries.length; i++){
            if(queries[i]>max){
                result[i] = -1;
            }
            else{
                result[i] = char2Long(nextPalindrome(queries[i], cur.clone()));
            }

        }
        return result;
    }

    public char[] nextPalindrome(int ind, char[] cur){
        ind--;
        int len = cur.length, i;
        if(len%2==0){
            i = len/2 - 1;
        }
        else{
            i = len/2;
        }
        for(; i>=0&&ind>0; i--, ind = ind/10){
            cur[i] += ind%10;
        }
        return cur;
    }
    public long char2Long(char[] cur){
        long result = 0;
        long low = 1;
        long high = 1;
        for(int i=0; i<cur.length-1; i++){
            high *= 10;
        }
        for(int i=0; i < cur.length/2; i++){
            result += (cur[i]-'0')*(low+high);
            high /= 10;
            low *= 10;
        }
        if(cur.length%2 == 1){
            result += (cur[cur.length/2]-'0')*high;
        }
        return result;
    }


    /**
    * @Author: Wang Xinxiang
    * @Description: 1802. 有界数组中指定下标处的最大值
    * @DateTime: 8/21/2023 5:14 PM
    * @Params: 
    * @Return 
    */
    public int maxValue(int n, int index, int maxSum) {
        if(maxSum<n){
            return -1;
        }
        if(n==1){
            return maxSum;
        }
        int result = 1;
        maxSum = maxSum-n;
        int r=index, l=index;
        while(l>=0&&r<n&&maxSum>=r-l+1){
            result++;
            maxSum = maxSum-r+l-1;
            r++;
            l--;
        }
        while(l>=0&&maxSum>=n-l){
            result++;
            maxSum = maxSum-n+l;
            l--;
        }
        while(r<n&&maxSum>=r+1){
            result++;
            maxSum = maxSum-r-1;
            r++;
        }
        result = result+maxSum/n;
        return result;
    }

    /**
    * @Author: Wang Xinxiang
    * @Description: 1869. 哪种连续子字符串更长
    * @DateTime: 8/21/2023 4:50 PM
    * @Params: 
    * @Return 
    */
    public boolean checkZeroOnes(String s) {
        int[] numbers = new int[2];
        int[] results = new int[2];
        Arrays.fill(numbers, 0);
        int old_pos = -1;
        for(char ch : s.toCharArray()){
            int new_pos = ch-'0' & 1;
            if(new_pos!=old_pos&&old_pos!=-1){
                results[old_pos] = Math.max(results[old_pos],numbers[old_pos]);
                numbers[new_pos] = 0;
            }
            numbers[new_pos]++;
            old_pos = new_pos;
        }
        results[old_pos] = Math.max(results[old_pos],numbers[old_pos]);
        return results[1]>results[0];
    }
    
    /**
    * @Author: Wang Xinxiang
    * @Description: 1671. 得到山形数组的最少删除次数
    * @DateTime: 8/19/2023 9:17 PM
    * @Params: 
    * @Return 
    */

    public int minimumMountainRemovals(int[] nums) {
        int len = nums.length;
        int result = 0;
        int[] increaseArray = new int[len];
        int[] increase = new int[len];
        int[] decrease = new int[len];
        int len1=1, len2=1;

        increaseArray[0] = nums[0];
        increase[0] = 1;
        for(int i=1; i<len; i++){
            int cur = binarySearch(increaseArray, len1, nums[i]);
            if(cur==len1){
                len1++;
            }
            increaseArray[cur] = nums[i];
            increase[i] = cur+1;
        }

        increaseArray[0] = nums[len-1];
        decrease[len-1] = 1;
        for(int i=len-1; i>=0; i--){
            int cur = binarySearch(increaseArray, len2, nums[i]);
            if(cur==len2){
                len2++;
            }
            increaseArray[cur] = nums[i];
            decrease[i] = cur+1;
        }

        for(int i=1; i<len-1; i++){
            if(increase[i]>1&&decrease[i]>1){
                result = Math.max(result, increase[i]+decrease[i]-1);
            }
        }

        return len-result;
    }

    public int binarySearch(int[] nums, int len, int target){
        int l=0, r=len;
        while(l<r){
            int mid = (l+r)/2;
            if(target==nums[mid]){
                return mid;
            }
            else if(target>nums[mid]){
                l=mid+1;
            }
            else {
                r = mid;
            }
        }
        return l;
    }




    /**
    * @Author: Wang Xinxiang
    * @Description: LCR 086 分割回文字符串
    * @DateTime: 8/15/2023 4:54 PM
    * @Params:
    * @Return
    */
    public String[][] partition(String s) {
        if(s==null){
            return null;
        }
        if(s.length()==0){
            return new String[0][0];
        }
        Map<Integer, String[][]> map = new HashMap<>();
        List<List<Integer>> reverseSub = new ArrayList<>();
        for(int i=0; i<s.length(); i++){
            reverseSub.add(new ArrayList<>());
            int l = i, r = i;
            for(;l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r);l--, r++){
                reverseSub.get(l).add(r);
            }
            l = i;
            r = i+1;
            for(;l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r);l--, r++){
                reverseSub.get(l).add(r);
            }
        }
        return twoPartition(0, s, reverseSub, map);
    }

    public String[][] twoPartition(int start, String s, List<List<Integer>> reverseSub, Map<Integer, String[][]> map){
        List<String[]> list = new ArrayList<>();
        for(int i : reverseSub.get(start)){
            if(i>=s.length()-1){
                list.add(new String[]{s.substring(start,i+1)});
            }
            else{
                String[][] next;
                if(map.containsKey(i+1)){
                    next = map.get(i+1);
                }
                else{
                    next = twoPartition(i+1, s, reverseSub, map);
                }
                for(int j=0; j<next.length; j++){
                    String[] temp = new String[next[j].length+1];
                    temp[0] = s.substring(start, i+1);
                    for(int m=1; m<next[j].length+1; m++){
                        temp[m] = next[j][m-1];
                    }
                    list.add(temp);
                }
            }
        }
        String[][] result = new String[list.size()][];
        for(int i=0; i< result.length; i++){
            result[i] = list.get(i);
        }
        map.put(start, result);
        return result;
    }


    /**
    * @Author: Wang Xinxiang
    * @Description: String matching
    * @DateTime: 8/2/2023 11:56 AM
    * @Params:
    * @Return
    */
    public int repeatedStringMatch(String b, String a) {
        if(b==null){
            return -2;
        }
        if (b.equals("")){
            return 0;
        }
        int len = a.length();
        int[] partial_matched = new int[len+1];
        partial_matched[0] = -1;
        partial_matched[1] = 0;
        int[] matched = new int[len];
        int[] unmatched = new int[len];
        for(int i=2; i<len+1; i++){
            int h = partial_matched[i-1];
            while(h>=0){
                if(a.charAt(i-1)==a.charAt(h)){
                    partial_matched[i] = h+1;
                    h=-2;
                }
                else{
                    h = partial_matched[h];
                }
            }
            if(h==-1){
                partial_matched[i] = 0;
            }

        }
        for(int i=0; i<len; i++){
            unmatched[i] = i-partial_matched[i]+partial_matched[i+1];
            matched[i] = i-partial_matched[i];
        }
        int start = 0;
        int llen = b.length();
        int i=0;
        while(start<llen){
            for(;i<len&&a.charAt(i)==b.charAt((start+i)%llen);i++);
            if(i<len){
                start = start+matched[i];
            }
            else{
                return (start+i-1)/llen + 1;
            }
            if(partial_matched[i]>=0){
                i = partial_matched[i];
            }
            else{
                i=0;
            }
        }
        return -1;
    }

    /**
    * @Author: Wang Xinxiang
    * @Description: generate the NEXT array for K-M-P Algorithm
    * @DateTime: 7/25/2023 2:58 PM
    * @Params:
    * @Return
    */

    public int[] generateNextArray(String s){
        char[] sc = s.toCharArray();
        int len = s.length();
        int[] result = new int[len];
        result[0] = -1;
        for(int i=0, j=1; j<len-1; ){
            if(sc[i]==sc[j]){
                result[j+1] = i+1;
                i++;
                j++;
            }
            else if(i==0) {
                result[j + 1] = 0;
                j++;
            }
            else{
                i=result[i];  //sc[i] and sc[j] not matching && i!=0 means that there are some matching before result[i]
                /**
                 * index :  0,1,2,3,4,5,6,7,8,9,10
                 * sc    :  a,c,a,b,d,a,c,a,c,a,c
                 *                ↑         ↑
                 *                i         j
                 * result: -1,0,0,1,0,0,1,2,3
                 * when i==3 && j==8
                 * sc[i]!=sc[j], we can re-match from i==result[i], which is 1
                 */
            }
        }
        return result;
    }

    /**
    * @Author: Wang Xinxiang
    * @Description:
    * @DateTime: 2023/4/19 14:15
    * @Params:
    * @Return
    */

    public int[] findMinKNumbers(int k, int[] arr){
        if(k>arr.length){
            return arr;
        }
        if(k<=0||arr==null){
            return new int[0];
        }
        int[] result = new int[k];
        int i=0;
        for(; i<k; i++){
            result[i] = arr[i];
        }
        for(;i<arr.length;i++){
            plugIn(result,arr[i]);
        }
        return result;
    }

    public void plugIn(int[] arr, int n){
        if(arr==null){
            return;
        }
        for(int i=0; i<arr.length; i++){
            if(n<arr[i]){
                int temp = arr[i];
                arr[i] = n;
                n = temp;
            }
        }
    }



    /**
    * @Author: Wang Xinxiang
    * @Description: 
    * @DateTime: 2023/3/23 0:00
    * @Params: 
    * @Return 
    */
    public List<Integer> getRow(int rowIndex) {
        if(rowIndex<0){
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<>();
        result.add(1);
        if(rowIndex==0){
            return result;
        }
        for(int row = 0; row <rowIndex; row++){
            result.add(1);
            for(int cur = 0; cur < row; cur++){
                result.add(result.get(0)+result.get(1));
                result.remove(0);
            }
            result.remove(0);
            result.add(1);
        }
        return result;
    }

    public List<Integer> getRow2(int rowIndex) {
        if(rowIndex<0){
            return new ArrayList<Integer>();
        }
        int[][] rows = new int[rowIndex+1][rowIndex+1];
        for(int i=0; i<rowIndex+1; i++){
            rows[i][0]=1;
            rows[i][i]=1;
        }
        for(int i=2; i<rowIndex+1; i++){
            for(int j=1; j<rowIndex; j++){
                rows[i][j] = rows[i-1][j-1]+rows[i-1][j];
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<rowIndex+1;i++){
            result.add(rows[rowIndex][i]);
        }
        return result;
    }
}
