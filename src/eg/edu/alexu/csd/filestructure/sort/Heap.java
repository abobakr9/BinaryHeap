package eg.edu.alexu.csd.filestructure.sort;


import java.util.ArrayList;
import java.util.Collection;

public class Heap <T extends Comparable<T>> implements IHeap<T>
{
    ArrayList<INode> Nodes = new ArrayList<>();
    @Override
    public INode getRoot () {
        if (Nodes.size()==0){
            return null ;
        }
        return Nodes.get(0);
    }

    @Override
    public int size () {
        if(Nodes.size()!=0){
            return Nodes.size();
        }else {
            return 0 ;
        }
    }

    @Override
    public void heapify (INode node){
       heapifyWithHeapSize((Node)node,Nodes.size());
    }
    public void heapifyWithHeapSize(INode node,int n){
        if (node!=null&&Nodes.size()!=1) {
            Node node1 = (Node) node;
            int largest = node1.getIndex();
            int l = 2 * largest + 1;
            int r = 2 * largest + 2;
            if (l < n && Nodes.get(l).getValue().compareTo(Nodes.get(largest).getValue()) > 0) {
                largest = l;
            }
            if (r < n && Nodes.get(r).getValue().compareTo(Nodes.get(largest).getValue()) > 0) {
                largest = r;
            }
            if (largest != node1.getIndex()) {
                T swap = (T) Nodes.get(node1.getIndex()).getValue();
                Nodes.get(node1.getIndex()).setValue(Nodes.get(largest).getValue());
                Nodes.get(largest).setValue(swap);
                heapifyWithHeapSize(Nodes.get(largest),n);
            }
        }
    }

    @Override
    public T extract () {
        if (Nodes.size() == 0) {
            return null;
        } else if (Nodes.size() == 1) {
            T node1 = (T) Nodes.get(0).getValue();
            Nodes.clear();
            return node1 ;
        } else {
            T nodeT = (T) Nodes.get(0).getValue();
            T swap = (T) Nodes.get(0).getValue();
            Nodes.get(0).setValue(Nodes.get(Nodes.size() - 1).getValue());
            Nodes.get(Nodes.size() - 1).setValue(swap);
            Nodes.remove(Nodes.size() - 1);
            heapify(Nodes.get(0));
            return  nodeT;
        }
    }

    @Override
    public void insert (T element){
        if (Nodes.size()==0&&element!=null){
            Nodes.add(new Node(Nodes.size(), element));
        }else if (element!=null){
            Nodes.add(new Node(Nodes.size(), element));
            int n1 = Nodes.size() - 1;
            int n2 = ((n1-1) / 2);
            while (n1 != 0) {
                if (Nodes.get(n1).getValue().compareTo(Nodes.get(n2).getValue()) > 0) {
                    T swap = (T) Nodes.get(n1).getValue();
                    Nodes.get(n1).setValue(Nodes.get(n2).getValue());
                    Nodes.get(n2).setValue(swap);
                    n1 = n2;
                    n2 = (n1 -1) / 2;
                } else {
                    break;
                }
            }
        }
    }

    @Override
    public void build (Collection<T> unordered){
        if (unordered!=null) {

            int startIndx = (unordered.size() / 2) - 1;//the last non leaf node
            Object[] array = unordered.toArray();
            for (int i = 0; i < unordered.size(); i++) {
                Nodes.add((new Node(i, (T) array[i])));
            }
            for (int i = startIndx; i >= 0; i--) {
                heapify(Nodes.get(i));
            }
        }
    }
    public void show(){
    for (int i = 0 ; i < Nodes.size(); i++){
        System.out.print(Nodes.get(i).getValue()+" ");
    }
    }
    public class Node <T extends Comparable<T>> implements INode<T>{
        private int  CurrentIndex ;
        private T value;
        public Node(int n, T v){
            CurrentIndex = n;
            value = v;
        }
        @Override
        public INode getLeftChild() {
            if (Nodes.size()==0||Nodes.size()==1||((CurrentIndex * 2) + 1>Nodes.size()-1)){
                return null ;
            }
            return Nodes.get((CurrentIndex * 2) + 1);
        }

        @Override
        public INode getRightChild() {
            if (Nodes.size()==0||Nodes.size()==1||((CurrentIndex * 2) + 2>Nodes.size()-1)){
                return null ;
            }
            return Nodes.get((CurrentIndex * 2) + 2);
        }

        @Override
        public INode getParent() {
            if (Nodes.size()==0||Nodes.size()==1){
                return null ;
            }
            return Nodes.get(((CurrentIndex - 1)/ 2));
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public void setValue(T value) {
            this.value = value;
        }
        public int getIndex(){
            return CurrentIndex ;
        }
        public void setIndex(int indx) {
            this.CurrentIndex = indx;
        }
    }
}
