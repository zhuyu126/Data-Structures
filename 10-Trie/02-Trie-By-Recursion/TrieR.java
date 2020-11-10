import java.util.TreeMap;

public class TrieR {
    private class Node{
        private boolean isWord;
        private TreeMap<Character,Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next=new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }
    private int size;
    private Node root;

    public TrieR() {
        this.root=new Node();
        this.size=0;
    }

    /**
     * 获得Trie中存储的单词数量
     * @return size
     */
    public int getSize(){
        return size;
    }
    /**
     * 向Trie中添加一个新的单词word 递归接口
     * @param word 新单词
     */
    public void addR(String word){
        Node cur=root;
        addR(root,word,0);
    }

    /**
     * 向Trie中添加一个新的单词word 递归实现
     * @param node 添加节点
     * @param word 新单词
     * @param index 添加单词索引
     */
    private void addR(Node node, String word, int index) {
        if (!node.isWord&&index==word.length()){
            node.isWord=true;
            size++;
        }
        if (index<word.length()){
            char c=word.charAt(index);
            if (node.next.get(c)==null){
                node.next.put(c,new Node());
            }
            addR(node.next.get(c),word,index+1);
        }
    }
}
