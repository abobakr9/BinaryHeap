package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        // write your code here
        Heap heap = new Heap() ;
       /* heap.insert(1);
        INode<Integer> root = heap.getRoot();
        heap.insert(2);
        heap.insert(4);
        heap.insert(3);
        heap.insert(0);
        heap.insert(5);
        heap.*/
        ArrayList<Integer> arr =new ArrayList<>();
        for(int i =0;i<6;i++){
            arr.add(6-i+1);
        }
        Sort sort = new Sort();
       //heap.build(arr);
         heap = (Heap) sort.heapSort(arr);
       // heap.extract();
        heap.show();

    }
}
