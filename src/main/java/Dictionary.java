import java.util.HashMap;


public class Dictionary {
    private Node root;

    Dictionary() {
        root = new Node();
    }

    private class Node {
        boolean endOfWord;
        private HashMap<Character, Node> children = new HashMap<Character, Node>();

        public void setEndOfWord(boolean endOfWord) {
            this.endOfWord = endOfWord;
        }

        public HashMap<Character, Node> getChildren() {
            return children;
        }

        public boolean isEndOfWord() {
            return endOfWord;
        }

    }

    public void insert(String word) {
        Node current = root;

        for (int i = 0; i < word.length(); i++) {
            current = current.getChildren()
                    .computeIfAbsent(word.charAt(i), c -> new Node());
        }
        current.setEndOfWord(true);
    }

    public boolean find(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            Node node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }

}
