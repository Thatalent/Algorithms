package datastructures;

public interface Tree {

    int getKey();
    Tree[] getTrees();
    void addLeaf(int value);
    void addLeaf(int value, int height);
    boolean removeLeaf(int value);
    void setKey(int value);
    Tree findTree(int value);
    int getHeight();
    void setHeight(int value);
}
