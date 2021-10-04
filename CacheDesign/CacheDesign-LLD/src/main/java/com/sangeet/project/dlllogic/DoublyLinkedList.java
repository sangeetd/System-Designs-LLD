package com.sangeet.project.dlllogic;

public class DoublyLinkedList<Key> {

    private Node<Key> head;
    private Node<Key> tail;

    public DoublyLinkedList(Key key) {
        Node<Key> node = new Node<>(key);
        head = node;
        tail = node;
    }

    public DoublyLinkedList() {

    }

    public void remove(Node<Key> node){

        if(node.getPrevious() == null){
            head = node.getNext();
        }else {
            node.getPrevious().setNext(node.getNext());
        }

        if(node.getNext() == null){
            tail = node.getPrevious();
        }else {
            node.getNext().setPrevious(node.getPrevious());
        }

    }

    public Node<Key> addAtHead(Node<Key> node){
        node.setPrevious(null);
        node.setNext(head);
        if(head != null){
            head.setPrevious(node);
        }

        head = node;

        if(tail == null){
            tail = node;
        }

        return node;

    }

    public Node<Key> getTail(){
        return tail;
    }

}
