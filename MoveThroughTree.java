package datastructures;

public class MoveThroughTree {

    public static void main(String[] args) {

        Tree treeRoot = new MyTree((int) (Math.random() * 50));

        System.out.println("Root tree value is: " + treeRoot.getKey());

        int runs = (int) (Math.random() * 100);

        System.out.println("Number of trees created is: " + runs);
        for (int i = 0; i < runs; i++) {
            treeRoot.addLeaf((int) (Math.random() * (i)));
        }

        treeRoot.addLeaf(4);

        Tree foundTree = treeRoot.findTree(4);

        String response = "No tree found";

        if (foundTree != null) {
            response = "Found a tree with value of four";
        }

        System.out.println(response);
        if (foundTree != null) {

            System.out.println("Tree was found at height: " + foundTree.getHeight());
        }
    }
}
