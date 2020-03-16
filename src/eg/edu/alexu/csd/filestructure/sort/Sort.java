package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class Sort <T extends Comparable<T>> implements ISort<T>{

    @Override
    public IHeap<T> heapSort(ArrayList<T> unordered) {
        Heap heap = new Heap();
        if (unordered==null){
            return heap ;
        }
        heap.build(unordered);
        for (int i = unordered.size()-1 ; i>=0 ; i--) {
            Heap.Node swap = (Heap.Node) heap.Nodes.get(0);
            heap.Nodes.set(0, heap.Nodes.get(i));
            ((Heap.Node) heap.Nodes.get(0)).setIndex(0);
            heap.Nodes.set(i,swap);
            ((Heap.Node) heap.Nodes.get(i)).setIndex(i);
            heap.heapifyWithHeapSize((INode) heap.Nodes.get(0),i);
        }
        return heap;
    }

    @Override
    public void sortSlow(ArrayList<T> unordered) {
        if (unordered != null) {
            int n = unordered.size();
            boolean sorted = false;
            int last = n - 1;
            for (int i = 0; (i < last) && !sorted; i++) {
                sorted = true;
                for (int j = last; j > i; j--)
                    if (unordered.get(j - 1).compareTo(unordered.get(j)) > 0) {
                        T swap = unordered.get(j);
                        unordered.set(j, unordered.get(j - 1));
                        unordered.set(j - 1, swap);
                        sorted = false; // signal exchange
                    }
            }
        }
    }

    @Override
    public void sortFast(ArrayList<T> unordered) {
        if (unordered != null && unordered.size() != 0) {
            mergeSort(0, unordered.size() - 1, unordered, new ArrayList<T>(unordered));        }
    }
    private void mergeSort(int low, int high, ArrayList<T> values, ArrayList<T> aux) {

        if(low < high){
            int mid = low + (high - low) / 2;
            mergeSort(low, mid, values, aux);
            mergeSort(mid+1, high, values, aux);
            merge(low, mid, high, values, aux);
        }
    }

    private void merge(int low, int mid, int high, ArrayList<T> values, ArrayList<T> aux) {

        int left = low;
        int right = mid + 1;

        for(int i = low; i <= high; i++){
            aux.set(i, values.get(i));
        }

        while(left <= mid && right <= high){
            values.set(low++, aux.get(left).compareTo(aux.get(right)) < 0 ? aux.get(left++) : aux.get(right++));
        }

        while(left <= mid){
            values.set(low++, aux.get(left++));
        }
    }
}
