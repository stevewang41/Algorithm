package nowcoder.chapter3;

import com.example.algorithm.BinaryTreeUtils;
import com.example.algorithm.BinaryTreeUtils.Node;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by wangshiyi on 17/8/3.
 * <p>
 * Tarjan算法与并查集结合解决最近公共祖先（Least Common Ancestors, LCA）的批量查询问题
 * 二叉树的节点数为N，查询语句的条数为M，时间复杂度O(N+M)
 */

public class TarjanAndDisjointSetsForLCA {

    /**
     * 一个Query类的实例表示一条查询语句
     * 即要查询o1与o2的最近公共祖先
     */
    public static class Query {
        public Node o1;
        public Node o2;

        public Query(Node o1, Node o2) {
            this.o1 = o1;
            this.o2 = o2;
        }
    }

    /**
     * 主函数
     *
     * @param head     二叉树头结点
     * @param quries   Query数组
     * @return         Node[] ans，ans[i]代表quries[i]这条查询的答案
     */
    public static Node[] tarJanQuery(Node head, Query[] quries) {
        Node[] ans = new Tarjan().query(head, quries);
        return ans;
    }

    /**
     * Tarjan算法实现处理流程
     */
    public static class Tarjan {
        private HashMap<Node, LinkedList<Node>> queryMap;   // (node，与node之间存在查询任务的结点)
        private HashMap<Node, LinkedList<Integer>> indexMap;// (node，解决node与queryMap.get(node)中的每个结点之间查询任务的答案放在ans的什么位置)
        private HashMap<Node, Node> ancestorMap;    // (集合的代表元素，代表元素的祖先结点)
        private DisjointSets sets;

        public Tarjan() {
            queryMap = new HashMap<>();
            indexMap = new HashMap<>();
            ancestorMap = new HashMap<>();
            sets = new DisjointSets();
        }

        public Node[] query(Node head, Query[] ques) {
            Node[] ans = new Node[ques.length]; // ans[i]代表ques[i]这条查询的答案
            setQueryAndIndexMap(ques, ans);
            sets.init(head);
            getAnswers(head, ans);
            return ans;
        }

        /**
         * 由输入的Query数组生成queryMap和indexMap
         *
         * @param ques
         * @param ans
         */
        private void setQueryAndIndexMap(Query[] ques, Node[] ans) {
            Node o1;
            Node o2;
            for (int i = 0; i < ques.length; i++) {
                o1 = ques[i].o1;
                o2 = ques[i].o2;
                if (o1 == o2 || o1 == null || o2 == null) { // 可以直接得到答案的
                    ans[i] = o1 != null ? o1 : o2;
                } else {
                    if (!queryMap.containsKey(o1)) {
                        queryMap.put(o1, new LinkedList<>());
                        indexMap.put(o1, new LinkedList<>());
                    }
                    if (!queryMap.containsKey(o2)) {
                        queryMap.put(o2, new LinkedList<>());
                        indexMap.put(o2, new LinkedList<>());
                    }
                    queryMap.get(o1).add(o2);   // o1与o2之间互有查询任务
                    indexMap.get(o1).add(i);    // o1与o2之间的查询任务查询任务存放在ans[i]
                    queryMap.get(o2).add(o1);   // o2与o1之间互有查询任务（正反都存）
                    indexMap.get(o2).add(i);    // o2与o1之间的查询任务查询任务存放在ans[i]
                }
            }
        }

        private void getAnswers(Node head, Node[] ans) {
            if (head == null) {
                return;
            }
            getAnswers(head.left, ans); // 递归遍历左孩子
            sets.union(head.left, head);// 合并当前结点所在集合和其左孩子所在集合
            ancestorMap.put(sets.findFather(head), head);// 把当前结点和其左孩子所在集合（用代表元素表示）的祖先结点设置为当前结点
            
            getAnswers(head.right, ans);// 递归遍历右孩子
            sets.union(head.right, head);
            ancestorMap.put(sets.findFather(head), head);
            
            LinkedList<Node> queryList = queryMap.get(head);
            LinkedList<Integer> indexList = indexMap.get(head);
            Node queryNode;
            Node queryFather;
            int index;
            while (queryList != null && !queryList.isEmpty()) {
                // 无论是否遍历过queryNode，都从查询链表中删除这个查询任务
                // 如果没有遍历过queryNode，这个查询任务还会在遍历到queryNode时被重新发现
                queryNode = queryList.poll();
                index = indexList.poll();
                queryFather = sets.findFather(queryNode);
                if (ancestorMap.containsKey(queryFather)) {      // 之前已访问过queryFather集合
                    ans[index] = ancestorMap.get(queryFather);   // queryFather集合的祖先结点就是当前节点与queryNode的LCA
                }
            }
        }
    }

    /**
     * 并查集数据结构
     */
    public static class DisjointSets {
        public HashMap<Node, Node> fatherMap;
        public HashMap<Node, Integer> rankMap;

        public DisjointSets() {
            fatherMap = new HashMap<>(); // (B,A)
            rankMap = new HashMap<>();
        }

        /**
         * 初始化并查集
         * 
         * @param head
         */
        public void init(Node head) {
            fatherMap.clear();
            rankMap.clear();
            preOrderInit(head);
        }

        /**
         * 根据先序遍历的二叉树结点顺序初始化并查集
         * 
         * @param head
         */
        private void preOrderInit(Node head) {
            if (head == null) {
                return;
            }
            fatherMap.put(head, head);
            rankMap.put(head, 0);
            preOrderInit(head.left);
            preOrderInit(head.right);
        }

        /**
         * 查找
         *
         * @param n
         * @return
         */
        public Node findFather(Node n) {
            Node father = fatherMap.get(n);
            if (father != n) {
                father = findFather(father);    // 沿着查找路径递归向上直到找到根
            }
            fatherMap.put(n, father);           // 进行路径压缩
            return father;
        }

        /**
         * 合并
         *
         * @param a
         * @param b
         */
        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aFather = findFather(a);
            Node bFather = findFather(b);
            if (aFather != bFather) {
                int aFrank = rankMap.get(aFather);
                int bFrank = rankMap.get(bFather);
                if (aFrank < bFrank) {
                    fatherMap.put(aFather, bFather);
                } else if (aFrank > bFrank) {
                    fatherMap.put(bFather, aFather);
                } else {
                    fatherMap.put(bFather, aFather);
                    rankMap.put(aFather, aFrank + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.right.right.left = new Node(8);
        BinaryTreeUtils.printTree(head);
        System.out.println("===============");

        // 生成查询数组
        Query[] qs = new Query[7];
        qs[0] = new Query(head.left.right, head.right.left);
        qs[1] = new Query(head.left.left, head.left);
        qs[2] = new Query(head.right.left, head.right.right.left);
        qs[3] = new Query(head.left.left, head.right.right);
        qs[4] = new Query(head.right.right, head.right.right.left);
        qs[5] = new Query(head, head);
        qs[6] = new Query(head.left, head.right.right.left);

        // Tarjan算法结合并查集解决所有查询问题
        Node[] ans = tarJanQuery(head, qs);

        // 打印答案
        for (int i = 0; i != ans.length; i++) {
            System.out.println("o1 : " + qs[i].o1.value);
            System.out.println("o2 : " + qs[i].o2.value);
            System.out.println("ancestor : " + ans[i].value);
            System.out.println("===============");
        }
    }
}
