/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solemneparte2;

import java.util.Stack;

/**
 *
 * @author Fernando
 */
public class Queue {

    private Node first;
    private Node last;
    private int n;

    private static class Node{
        private Stack item;
        private Node next;
    }

    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public Stack peek() {
        Stack q=new Stack();
        if (isEmpty()) return q;
        return first.item;
    }

    public void enqueue(Stack item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }

    public Stack dequeue() {
        if (isEmpty()) return null;
        Stack item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        return item;
    }
}

