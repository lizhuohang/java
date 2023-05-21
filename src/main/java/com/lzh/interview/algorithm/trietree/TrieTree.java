package com.lzh.interview.algorithm.trietree;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-21
 */
public class TrieTree {

    private Node head;

    public TrieTree() {
        this.head = createNode('s');
    }

    public void init(Set<String> strSet) {
        strSet.forEach(e -> {
            Node nowNode = this.head;
            for (int i = 0; i < e.length(); i++) {
                nowNode = addNext(nowNode, e.charAt(i));
            }
            setEndFlag(nowNode);
        });
    }

    public boolean find(String s) {
        Node nowNode = this.head;
        for (int i = 0; i < s.length(); i++) {
            if (nowNode.isEnd()) {
                return true;
            }
            if (!nowNode.getNextMap().containsKey(s.charAt(i))) {
                return false;
            }
            nowNode = nowNode.getNextMap().get(s.charAt(i));
        }
        return nowNode.isEnd();
    }

    private static Node createNode(Character c) {
        return new Node(new HashMap<Character, Node>(), c, false);
    }

    private static void setEndFlag(Node node) {
        node.setEnd(true);
    }

    /**
     * @return the next node
     */
    private static Node addNext(Node thisNode, Character charter) {
        Map<Character, Node> nextMap = thisNode.getNextMap();
        if (nextMap.containsKey(charter)) {
            return nextMap.get(charter);
        }
        Node nextNode = createNode(charter);
        nextMap.put(charter, nextNode);
        return nextNode;
    }

    private static class Node {
        private Map<Character, Node> nextMap;
        private Character value;
        private boolean isEnd;

        public Node(Map<Character, Node> nextMap, Character value, boolean isEnd) {
            this.nextMap = nextMap;
            this.value = value;
            this.isEnd = isEnd;
        }

        public Map<Character, Node> getNextMap() {
            return nextMap;
        }

        public void setNextMap(Map<Character, Node> nextMap) {
            this.nextMap = nextMap;
        }

        public Character getValue() {
            return value;
        }

        public void setValue(Character value) {
            this.value = value;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }
    }
}
