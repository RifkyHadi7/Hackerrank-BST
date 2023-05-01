import java.util.Scanner;

class Node {
    int number;
    String content;
    Node left;
    Node right;

    Node(int number, String content) {
        this.number = number;
        this.content = content;
    }
}

class BST {
    Node root;
    int length;
    BST() {
        root = null;
        length = 0;
    }

    public void insert(int number, String string) {
        Node newNode = new Node(number, string);
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;

            while (true) {
                parent = current;
                if (number < current.number) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        break;
                    }
                } else if (number > current.number) {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        break;
                    }
                } else {
                    return;
                }
            }
        }
        setLength(string.length());
    }

    public void setLength(int stringLength){
        length += stringLength;
    }

    public int length(){
        return length;
    }

    public void traverse(Node node) {
        if (node != null) {
            traverse(node.left);
            System.out.print(node.content);
            traverse(node.right);
        }
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int expectedLength = scanner.nextInt();

        scanner.nextLine();

        BST bst = new BST();

        while (true) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ", 2);

            int number = Integer.parseInt(parts[0]);
            String string = parts[1].replace("\\n","\n");

            if (string.length() > expectedLength) {
                string = string.substring(0, expectedLength);
            }

            int bstLength = bst.length() + string.length();
            if (bstLength > expectedLength) {
                int deleteLength = bstLength - expectedLength;
                string = string.substring(0, string.length() - deleteLength);
            }

            bst.insert(number, string);

            if (bst.length() == expectedLength) {
                break;
            }
        }

        bst.traverse(bst.root);
    }
}
