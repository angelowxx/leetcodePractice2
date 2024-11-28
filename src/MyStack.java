/**
 * @Author: Wang Xinxiang
 * @Description: my stack
 * @DateTime: 7/27/2023 10:50 AM
 */

public class MyStack {
    private int Nmax = 10;
    private int size = 0;
    private int[] array;
    MyStack(){
        array = new int[Nmax];
    }

    MyStack(int N){
        Nmax = N;
        array = new int[Nmax];
    }

    public void push(int x){
        if(size < Nmax){
            array[size++] = x;
        }
        else{
            Nmax = Nmax << 1;
            int[] newArray = new int[Nmax];
            for (int i=0; i<size; i++){
                newArray[i] = array[i];
            }
            array = newArray;
            push(x);
        }
    }

    public int pop(){
        if(size <= 0){
            throw new IndexOutOfBoundsException("栈为空");
        }
        return array[(size--)-1];
    }

    public boolean isEmpty(){
        return size == 0;
    }

}
