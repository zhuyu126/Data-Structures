public class MaxHeap<E extends Comparable<E>> {
    private Arrays<E> arrays;

    public MaxHeap(int capacity) {
        arrays = new Arrays<>(capacity);
    }

    public MaxHeap() {
        arrays=new Arrays<>();
    }

    /**元素个数*/
    public int size(){
        return arrays.getSize();
    }

    /**判断堆中是否为空*/
    public boolean isEmpty(){
        return arrays.isEmpty();
    }

    /**从数组下标为零开始实现的堆*/

    /**
     * 完全二叉树的数组中该索引所表示的元素的父亲节点的索引
     * @param index 元素索引
     * @return 该索引元素的父亲节点索引
     */
    private int parent(int index){
        if(index==0){
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index-1)/2;
    }

    /**
     * 完全二叉树的数组中该索引所表示的元素的左孩子节点的索引
     * @param index 元素索引
     * @return 该索引元素的左孩子节点索引
     */
    private int leftChild(int index){
        return index*2+1;
    }

    /**
     * 完全二叉树的数组中该索引所表示的元素的右孩子节点的索引
     * @param index 元素索引
     * @return 该索引元素的右孩子节点索引
     */
    private int rightChild(int index){
        return index*2+2;
    }

    /**
     * 堆元素的添加，使用数组的末尾添加方法，需要注意的是添加进去的元素需要是个堆结构不发生改变
     * 即需要对添加的元素进行比较使之能够sift up
     * @param e
     */
    public void add(E e){
        arrays.addLast(e);
        /**对添加元素进行sift up操作*/
        siftUp(arrays.getSize()-1);
    }

    /**
     *siftUp 操作
     * @param index siftUp元素的索引
     */
    private void siftUp(int index){
        /**该索引需要大于0 并且该元素大于大父亲节点的元素才能执行sift up操作*/
        while (index>0&&arrays.get(index).compareTo(arrays.get(parent(index)))>0){
            arrays.swap(index,parent(index));
            index=parent(index);
        }
    }

    /**
     * 查找堆中最大元素
     * @return 最大元素值
     */
    public E findMax(){
        if (arrays.getSize()==0){
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return arrays.get(0);
    }

    /**
     * 取出最大元素
     * 将堆的收尾元素进行交换之后删除尾元素，因为此时取出前的尾元素位于堆首，为了不改变堆的性质
     * 需要对此时的堆首元素进行SiftDown操作
     * @return 最大元素值
     */
    public E extractMax(){
        E ret=findMax();
        arrays.swap(0,arrays.getSize()-1);
        arrays.removeLast();
        siftDown(0);
        return ret;
    }

    /**
     * SiftDown 操作
     * @param index SiftDown元素的索引
     */
    private void siftDown(int index){
        if(leftChild(index)>arrays.getSize()){
            return;
        }else if (rightChild(index)>arrays.getSize()){
            return;
        }else {
            if (arrays.get(leftChild(index)).compareTo(arrays.get(index))>=0){
                arrays.swap(leftChild(index),index);
                siftDown(leftChild(index));
            }else if (arrays.get(rightChild(index)).compareTo(arrays.get(index))>=0){
                arrays.swap(rightChild(index),index);
                siftDown(rightChild(index));
            }
        }
    }
}
