package com.mentorship.program.data;

import java.util.ArrayList;
import java.util.List;

// https://stackoverflow.com/questions/3522454/java-tree-data-structure#17490124

// https://www.javagists.com/java-tree-data-structure

public class Node<T> {

    private T data;
    private final List<Node<T>> children;
    private Node<T> parent;

    public Node(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

//    public synchronized Node<T> addChild(Node<T> child) {
//        child.setParent(this);
//        this.children.add(child);
//        return child;
//    }
    
	public synchronized Node<T> addChild(T child) {
		Node<T> childNode = new Node<>(child);
		childNode.parent = this;
		children.add(childNode);
		return childNode;
	}

    public synchronized void addChildren(List<Node<T>> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public synchronized List<Node<T>> getChildren() {
        return children;
    }

    public synchronized T getData() {
        return data;
    }

    public synchronized void setData(T data) {
        this.data = data;
    }

    private synchronized void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public synchronized Node<T> getParent() {
        return parent;
    }
}