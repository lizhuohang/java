package com.lzh.interview.algorithm.trietree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2022-05-11
 * 在实际使用的时候可能考虑到语序问题，"熙来&瓜瓜" 这种在构建树的时候可能需要倒序再构建一遍"瓜瓜&熙来"，也可以在敏感词库中增加"瓜瓜&熙来"
 * 还有很多可以优化的地方，比如字符串处理、EOF节点公用等，&其实可以不用单独设置一个节点，可以通过它下一个节点的type来代替它
 */
public class A {

    private final static String EOF = "EOF";
    private final static String AND = "&";

    public static void main(String[] args) {
        String[] sensitiveWords = new String[] {
                "狗子",
                "大大",
                "熙来&瓜瓜",
                "狗娃子",
                "今天&工卡&核酸",
                "可惜了",
                "知己知彼",
                "百战不殆",
                "是个人才",
                "天天",
                "给我&整这些",
                "比较好玩",
                "的问题",
                "明天",
                "吃蛋糕",
                "开心",
                "完了",
                "跑步",
                "五公里",
                "睡觉",
                "八小时",
                "非常的",
                "健康",
                "可惜",
                "不是",
                "你",
                "陪我",
                "到",
                "最后",
                "感谢",
                "那是你",
                "这一刻",
                "我们",
                "曾",
                "经的",
                "时刻关注",
                "好的哥",
                "好的姐",
                "嗯嗯嗯"
        };
        System.out.println("敏感词：");
        for (String s : sensitiveWords) {
            System.out.print("    " + s);
        }
        System.out.println("");
        String[] queryArray = new String[] {
                "今天我吃了个大大的瓜瓜",
                "今天我吃了个大的瓜瓜",
                "这个狗狗不是狗子",
                "狗娃小子是个狗狗",
                "狗娃小子是个狗娃子",
                "够娃小子是个狗娃",
                "今天有个大瓜有马熙来",
                "有个熙来的瓜瓜",
                "有个瓜瓜的马熙来",
                "我今天上班忘带工卡核酸也没做",
                "我今天上班忘带工卡好惨",
                "我上班忘带工卡也没做核酸",
        };
        TrieTree sensitiveTree = new TrieTree(sensitiveWords);
        long m = System.currentTimeMillis();
        // 如果交给线程池做速度起飞
        for (int i = 0; i < 10000; i++) {
            for (String query : queryArray) {
                sensitiveTree.containSensitiveWords(query);
            }
        }
        System.out.println("以下结果循环匹配1万次耗时：" + (System.currentTimeMillis() - m) + "ms");
        for (String query : queryArray) {
            System.out.println(query + ":" + sensitiveTree.containSensitiveWords(query));
        }
    }

    // 树
    public static class TrieTree {
        private Node start;

        private TrieTree() {
        }

        public TrieTree(String[] sensitiveWords) {
            start = new Node("", -1);
            for (String sensitiveWord : sensitiveWords) {
                String[] split = sensitiveWord.split("");
                Node tmp = start;
                int deep = 0;
                for (String w : split) {
                    if (!tmp.getNextNodes().containsKey(w)) {
                        Node newNode = new Node(w, deep++);
                        tmp.getNextNodes().put(w, newNode);
                        tmp = newNode;
                    } else {
                        tmp = tmp.getNextNodes().get(w);
                    }
                }
                tmp.getNextNodes().put(EOF, new Node(EOF, -999));
            }
        }

        // 这里不要把query处理成StringArray后传到下面的方法中处理，会影响耗时
        public boolean containSensitiveWords(String query) {
            return containSensitiveWords(query, 0, this.start);
        }

        // 如果还想提高效率，可以不要使用递归
        private boolean containSensitiveWords(String query, int index, Node node) {
            if (index >= query.length()) {
                return false;
            }
            String w = query.substring(index++, index);
            if (!node.getNextNodes().containsKey(w)) {
                if (node.getValue().equals(AND)) {
                    return containSensitiveWords(query, index, node);
                }
                // 需要进行指针回溯
                return containSensitiveWords(query, index - node.deep - 1, this.start);
            }

            // 匹配上
            Node tmp = node.getNextNodes().get(w);
            if (tmp.getNextNodes().containsKey(EOF)) {
                // 完整匹配
                return true;
            }
            // 未到敏感词结尾
            if (tmp.getNextNodes().containsKey(AND)) {
                // 多词
                if (containSensitiveWords(query, index, tmp.getNextNodes().get(AND))) {
                    return true;
                }
                // 多词匹配不中
                return containSensitiveWords(query, index, this.start);
            }
            return containSensitiveWords(query, index, tmp);
        }
    }

    // 树节点
    public static class Node {
        private String value;
        private int deep;
        private Map<String, Node> nextNodes;

        private Node() {
        }

        public Node(String value, int deep) {
            this.value = value;
            this.deep = deep;
            nextNodes = new HashMap<>();
        }

        public String getValue() {
            return value;
        }

        public int getDeep() {
            return deep;
        }

        public Map<String, Node> getNextNodes() {
            return nextNodes;
        }
    }
}
