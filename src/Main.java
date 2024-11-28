import java.util.*;

public class Main {
    public static void main(String[] args) {
        LeetcodeSolutions leetcodeSolutions = new LeetcodeSolutions();
        int[] ss = {58,29,358732919,149198876,246393513,104605183,18363825};
        long[] rr = leetcodeSolutions.kthPalindrome(ss, 9);
        System.out.println();
    }

    public static void PrintArray(int[] array){
        for (int i=0; i< array.length; i++){
            System.out.print(array[i]+",");
        }
        System.out.println();
    }

    public static int getMax(Map<Integer, List<int[]>> mapBackward, int i){
        int result = 0;
        if(mapBackward.containsKey(i)){
            List<int[]> cur = mapBackward.get(i);
            for(int[] curs : cur){
                result = Math.max(curs[2]+getMax(mapBackward, curs[1]), result);
            }
        }
        return result;
    }

    public void problem3(){
        Scanner in = new Scanner(System.in);
        int nTotal = in.nextInt();
        int eTotal = in.nextInt();
        int[][] Es = new int[nTotal][nTotal];
        for(int i=0; i<eTotal; i++){
            int ii = in.nextInt();
            int jj = in.nextInt();
            int value = in.nextInt();
            Es[ii][jj] = value;
        }
        int result = 0;
        int[] maxValue = new int[nTotal];
        for(int j=0; j<nTotal; j++){
            for(int i=0; i<nTotal; i++){
                maxValue[j] = Math.max(maxValue[j], Es[i][j]+maxValue[i]);
                result = Math.max(maxValue[j], result);
            }
        }
        System.out.println(result);
    }

    public void problem2(){
        Scanner in = new Scanner(System.in);
        List<Integer> equation1 = new ArrayList();
        List<Integer> equation2 = new ArrayList();
        String sigh = new String();
        if(in.hasNextLine()){
            String a = in.nextLine();
            String[] aset = a.substring(1, a.length()-1).split(" ");
            for(int i=0; i<aset.length; i++){
                equation1.add(Integer.parseInt(aset[i]));
            }
        }
        if(in.hasNextLine()){
            String a = in.nextLine();
            String[] aset = a.substring(1, a.length()-1).split(" ");
            for(int i=0; i<aset.length; i++){
                equation2.add(Integer.parseInt(aset[i]));
            }
        }
        if(in.hasNextLine()){
            sigh = in.nextLine();
        }

        List<Integer> result;
        if(sigh.equals("+")){
            result = plus(equation1, equation2);
        }
        else if(sigh.equals("-")){
            for(int i=0; i<equation2.size(); i++){
                equation2.set(i, -1*equation2.get(i));
            }
            result = plus(equation1, equation2);
        }
        else if(sigh.equals("*")){
            result = multiply(equation1, equation2);
        }
        else{
            result = new ArrayList<>();
        }

        System.out.print("[");
        int i=0;
        for(; i<result.size()&&result.get(i)==0; i++){

        }
        if(i==result.size()){
            System.out.println("0");
        }
        for(; i<result.size(); i++){
            System.out.print(result.get(i));
            if(i<result.size()-1){
                System.out.print(" ");
            }
        }
        System.out.print("]");
    }

    public static List<Integer> multiply(List<Integer> paras1, List<Integer> paras2){
        List<Integer> result = new ArrayList<>();
        int ind1 = paras1.size()-1;
        int ind2 = paras2.size()-1;
        for(int i=0; i<=ind1+ind2; i++){
            result.add(0);
        }
        for(int i=0; i<=ind1; i++){
            for(int j=0; j<=ind2; j++){
                result.set(i+j, paras1.get(i)*paras2.get(j)+result.get(i+j));
            }
        }
        return result;
    }

    public static List<Integer> plus(List<Integer> paras1, List<Integer> paras2){
        int ind1 = paras1.size()-1;
        int ind2 = paras2.size()-1;
        List<Integer> result = new ArrayList<>();
        while(ind1>=0&&ind2>=0){
            result.add(0, paras1.get(ind1)+paras2.get(ind2));
            ind1--;
            ind2--;
        }
        while(ind1>=0){
            result.add(0, paras1.get(ind1));
            ind1--;
        }
        while(ind2>=0){
            result.add(0, paras2.get(ind2));
            ind2--;
        }
        return result;
    }


    public void problem1(){
        Scanner in = new Scanner(System.in);
        int totalNum = in.nextInt();
        int[] nums = new int[totalNum];
        for(int i=0; in.hasNextInt()&&i<totalNum; i++){
            nums[i] = in.nextInt();
        }
        int errorFile = -1;
        if(in.hasNextInt()){
            errorFile = in.nextInt();
        }
        int l=-1,r=-1;
        int i=0;
        for(; i<totalNum; i++){
            if(nums[i]==errorFile){
                l=i;
                while(i<totalNum&&nums[i]==errorFile){
                    i++;
                }
                r=i-1;
                break;
            }
        }
        System.out.println(l+" "+r);
        for(; i<totalNum; i++){
            if(nums[i]==errorFile){
                l=i;
                break;
            }
        }
        System.out.println(l+" "+r);
    }


}