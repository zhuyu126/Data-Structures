import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树具有天然的递归结构
 *    每个节点的左子树也是二叉树
 *    每个节点的右子树也是二叉树
 * 二叉树不一定是"满"的
 *    一个节点也可以是二叉树
 *    空也是二叉树
 *
 * 二分搜索树是二叉树
 * 二分搜索树的每个节点值：
 *    大于其左子树的所有节点的值
 *    小于其右子树的所有节点的值
 * 每一颗子树也是二分搜索树
 * 存储元素必须具有可比较性（栈队列链表不具该性质）
 * 二分搜索树不包含重复元素
 */
public class BST<E extends Comparable<E>> {
    /**
     * 节点定义
     */
    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }
    /**根节点*/
    private Node root;
    /**元素存储个数*/
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    /**
     * 得到存储元素的个数即大小
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断二分搜索树是否为空
     * @return
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**添加新元素e*/
    public void add(E e){
        root=add(root,e);
    }

    /**
     * 在根节点为node的二叉树添加新元素e 递归算法
     * @param node 节点
     * @param e 插入元素
     * @return 插入的新节点的二分搜索树的根
     */

    private Node add(Node node,E e){
        if(node==null){
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e)>0){
            node.right=add(node.right,e);
        }else if (e.compareTo(node.e)<0){
            node.left=add(node.left,e);
        }
        return node;
    }

    /**
     * 二叉树中是否含有元素E
     * @param e 查找元素
     * @return
     */
    public boolean contains(E e){
        return contains(root,e);
    }

    /**
     * 以node为根节点查找是否含有元素e 递归实现
     * @param node 节点
     * @param e 查找元素
     * @return true/false
     */
    private boolean contains(Node node,E e){
        if(node==null){
            return false;
        }
        if(e.compareTo(node.e)>0){
            return contains(node.right,e);
        }else if (e.compareTo( node.e)<0){
            return contains(node.left,e);
        }
        return true;
    }

    /**
     * 前序遍历递归实现 属于DFS深度优先遍历
     * 前序遍历：根—>左—>右
     */
    public void preOrder(){
        preOrder(root);
    }

    /**
     * 以node为根的前序遍历,递归实现
     * @param node
     */
    private void preOrder(Node node){
        if (node==null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历:左->根->右 属于DFS深度优先遍历
     * 结果是顺序的
     */
    public void inOrder(){
        inOrder(root);
    }

    /**
     * 以node为根的中序遍历, 递归算法
     * @param node
     */
    private void inOrder(Node node){
        if (node==null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 后序遍历：右->左->根 属于DFS深度优先遍历
     * 运用：内存释放
     */
    public void postOrder(){
        postOrder(root);
    }

    /**
     * 以node为根的后序遍历, 递归算法
     * @param node
     */
    private void postOrder(Node node){
        if (node==null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 非递归前序遍历
     * 可以借助栈来访问节点 利用栈 后进先出 的特性 将父节点保存到栈中，出栈时的节点优先为子节点
     * 根节点首先入栈和出栈然后再执行子树的操作
     * 入栈按照先入栈右子树再入左子树
     */
    public void preOrderNR(){
        if(root==null){
            return;
        }
        Stack<Node> stack=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node tempNode=stack.pop();
            System.out.println(tempNode.e);
            if (tempNode.right!=null){
                stack.push(tempNode.right);
            }
            if (tempNode.left!=null){
                stack.push(tempNode.left);
            }
        }
    }

    /**
     * 非递归中序遍历
     * 分析:
     * 判断该节点是否存在或栈已经为空，若该节点存在则该节点入栈
     *  步骤1：判断该节点是否有左子树，节点如果有左叶子节点，该节点入栈，
     *      如果该节点没有左叶子节点，访问该节点
     *  步骤2：判断该节点是否有右子树，如果节点有右叶子节点，重复步骤1
     *      如果节点没有右叶子节点（说明访问完毕）回退，让栈顶元素出栈，并且访问栈顶元素的右叶子树，重复步骤1
     *  步骤3：当栈为空时，遍历结束
     */
    public void inOrderNR(){
        if(root==null){
            return;
        }
        Stack<Node>stack=new Stack<>();
        Node cur=root;
        // 判断 当前节点 是否为空，并且 栈是否遍历完结
        while (cur!=null||!stack.empty()){
            if (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }else {
                cur=stack.pop();
                System.out.println(cur.e);
                cur=cur.right;
            }
        }
    }

    /**
     * 非递归后序遍历
     * 采用两个栈来模拟
     * 需要注意的是：节点的所有右孩子节点访问完毕后，该节点才可以出栈
     * 通过debug来加深理解
     */
    public void  postOrderNR(){
        if (root==null){
            return;
        }
        Stack<Node>stack=new Stack<>();
        Stack<Node>outstack=new Stack<>();

        stack.push(root);
        while (!stack.empty()){
            Node cur=stack.pop();
            outstack.push(cur);
            if(cur.left!=null){
                stack.push(cur.left);
            }
            if(cur.right!=null){
                stack.push(cur.right);
            }
        }
        while (!outstack.empty()){
            System.out.println(outstack.pop().e);
        }
    }

    /**
     * 二分搜索树的层次遍历BFS
     * 遍历顺序根->左孩子->右孩子
     * 层次遍历是一个线性的遍历过程
     * 采用队列来进行遍历（队列先进先出）从左到右入队
     * BFS 的意义：更快的找到问题的解；常用于求解最短路径
     */
    public void levelOrder(){
        if(root==null){
            return;
        }
        Queue<Node>queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node cur=queue.poll();
            System.out.println(cur.e);
            if (cur.left!=null){
                queue.offer(cur.left);
            }
            if (cur.right!=null){
                queue.offer(cur.right);
            }
        }
    }

    /**
     * 打印输出BST
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    /**
     * 以前序遍历的方法
     * 生成以node为根节点，深度为depth的描述二叉树的字符串
     * @param node 节点
     * @param depth 深度(层次)
     * @param res 结果
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if(node==null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth)+node.e+"\n");
        generateBSTString(node.left,depth+1,res);
        generateBSTString(node.right,depth+1,res);

    }
    private String generateDepthString(int depth){
        StringBuilder res=new StringBuilder();
        for(int i=0;i<depth;i++){
            res.append("-");
        }
        return res.toString();
    }
}
