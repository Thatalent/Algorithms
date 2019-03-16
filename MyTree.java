package datastructures;

public class MyTree implements Tree {

    private int key;
    private MyTree leftNode;
    private MyTree rightNode;
    private int height;

    /**
    * Default constructor
    * */
    public MyTree (){

    }

    /**
     * Constructor
     *
     * @param key
     */
    public MyTree (int key){
        this.key = key;
    }

    /**
     *
     * @return key
     */
    @Override
    public int getKey() {
        return key;
    }

    @Override
    public int getHeight(){
        return this.height;
    }

    @Override
    public void setHeight(int value){
        this.height = value;
    }

    /**
     * Sets value to key of this node
     * @param value
     */
    @Override
    public void setKey(int value) {
        this.key = value;
    }

    /**
     * returns both left and right tree
     * @return
     */
    @Override
    public Tree[] getTrees() {

        Tree[] trees = {leftNode, rightNode};
        return trees;
    }

    /**
     * Adds value to tree if value is unique;
     * @param value
     */
    @Override
    public void addLeaf(int value) {

        if(key >= value){
            if(leftNode == null){
                leftNode = new MyTree(value);
                leftNode.setHeight(height+1);
            }
            else{
                leftNode.addLeaf(value, height+1);
            }
        }
        else {
            if(rightNode == null){
                rightNode = new MyTree(value);
                rightNode.setHeight(height+1);
            }
            else{
                rightNode.addLeaf(value, height+1);
            }
        }
    }

    public void addLeaf(int value, int height) {

        if(key >= value){
            if(leftNode == null){
                leftNode = new MyTree(value);
                leftNode.setHeight(height);
            }
            else{
                leftNode.addLeaf(value);
            }
        }
        else {
            if(rightNode == null){
                rightNode = new MyTree(value);
                rightNode.setHeight(height);
            }
            else{
                rightNode.addLeaf(value);
            }
        }
    }

    /**
     * Removes value from tree. Returns ture if removed. Returns false if tree has no value or value is equal to root.
     * @param value
     * @return
     */
    @Override
    public boolean removeLeaf(int value) {

        if(key == value){
            if(leftNode != null){
                this.key = leftNode.getKey();
                leftNode = null;
            }
            else if (rightNode != null){
                this.key = rightNode.getKey();
                rightNode = null;
            }
            else {
                return false;
            }
            return true;
        }
        else if (key > value && leftNode != null){
            return leftNode.removeLeaf(value);
        }
        else if (key < value && rightNode != null){
            return rightNode.removeLeaf(value);
        }
        return false;
    }

    @Override
    public Tree findTree(int value) {

        Tree lostTree = null;

        if( this.key == value){

            if (leftNode != null) {

                lostTree = leftNode.findTree(value);
            }
            if (lostTree == null) {

                lostTree = this;
            }
        }
        else if( this.key > value){
            if(leftNode != null) {
                lostTree = leftNode.findTree(value);
            }
            else return null;
        }
        else {
            if(rightNode != null) {

                lostTree = rightNode.findTree(value);
            }
            else {
                return null;
            }
        }

        return lostTree;
    }
}
