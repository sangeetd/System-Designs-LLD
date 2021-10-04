package com.sangeet.project.dlllogic;

public class Node<Key> {

    private Key key;
    private Node<Key> prev;
    private Node<Key> next;

    public Node(Key key) {
        this.key = key;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Node<Key> getPrevious() {
        return prev;
    }

    public void setPrevious(Node<Key> prev) {
        this.prev = prev;
    }

    public Node<Key> getNext() {
        return next;
    }

    public void setNext(Node<Key> next) {
        this.next = next;
    }
}
