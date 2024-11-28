/**
 * @Author: Wang Xinxiang
 * @Description: my queue
 * @DateTime: 7/27/2023 11:14 AM
 */

public class MyQueue {
    private int Nmax = 10;
    private int size = 0;
    private int head = 0;
    private int[] array;

    MyQueue(){
        array = new int[Nmax];
    }

    MyQueue(int nmax){
        Nmax = nmax;
        array = new int[Nmax];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int x){
        if(size < Nmax){
            array[(head+size)%Nmax] = x;
            size++;
        }
        else{
            Nmax = Nmax << 1;
            int[] newArray = new int[Nmax];
            int start = head;
            for(int i=0; i<size; i++){
                newArray[i] = array[head++];
                head = head%size;
            }
            head = 0;
            array = newArray;
            add(x);
        }

    }

    public int get(){
        if(size>0){
            int result = array[head++];
            size--;
            if(head>=Nmax){
                head = head%Nmax;
            }
            return result;
        }
        else{
            throw new IndexOutOfBoundsException("队列为空");
        }
    }


}
