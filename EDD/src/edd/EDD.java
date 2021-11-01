/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd;

/**
 *
 * @author Pipe
 */

class LinkedList{
    private Node first;     
    private int n;

    // helper linked list class
    private class Node {
        private int item;
        private Node next;
    }
    
    public LinkedList() {
        first = null;
        n = 0;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return n;
    }

    public void push(int item) 
    {
        Node nodo = new Node();
        nodo.item = item;
        if(isEmpty()) first = nodo;
        else
        {
            Node temp = first;
            while(temp.next != null) temp = temp.next;
            temp.next = nodo;
            n++;
        }
    }

    public int pop(int pos) {
        int sol;
        if (isEmpty()) return -1;
        else if(first.item == pos)
        {
            sol = first.item;
            first = first.next;
            return sol;
        }
        Node prev = null;
        Node current = first;
        while(current.next != null && current.item != pos)
        {
            prev = current;
            current = current.next;
        }
        if(current == null) return -1;
        else
        {
            sol = current.item;
            prev.next = current.next;
            return sol;
        }
    }
    public String print()
    {
        String s = "";
        Node current = first;
        while(current != null)
        {
            s += current.item + " ";
            current = current.next;
        }
        return s;
    }
    
    public int find_the_chosen_one(){
        
        Node nodo = new Node();
        nodo=first;
        Node elegido = new Node();
        int suma_izq=0;
        int suma_der=0;
        int sumaT=0;
        int p=0;
        elegido=first;
        int size=n;
        if(nodo==null){
            return -1;
        }
        else{
            if(size<3){
                return -1;
            }
            else{
                for(int i=0;i<=size;i++){
                    sumaT=sumaT+nodo.item;
                    nodo=nodo.next;
                }
                nodo=first;
                //{3, 6, 1, 10, 7, 2, 1}
                //{8, 1, 9, 2, 5, 1, 1}
                //{102, 58, 21, 45, 226, 2, 14, 31, 79, 5, 7, 19, 9, 10, 47, 3}
                //{10, 21, 3, 5, 45, 26, 12}
 
                for(int i=1;i<=size;i++){ 
                    p=(sumaT-elegido.item)/2;
                    if(suma_izq == p){
                        
                        nodo=nodo.next;
                        suma_der=suma_der+nodo.item;
                    
                    }
                    if (suma_izq != p){
                        suma_izq=suma_izq+elegido.item;
                        nodo=nodo.next;
                        elegido=nodo; 
                        
                    }
                }
                
                
                if(suma_izq==suma_der){
                    return elegido.item;
                }
                else{
                    return -1;
                }
                
            }
        }
    }
    
    
    
}

class Stack{
    private Node first;
    private int n;

    private static class Node {
        private int item;
        private Node next;
    }

    public Stack() {
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void push(int item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public int pop() {
        if (isEmpty()) return Integer.MIN_VALUE;
        int item = first.item;
        first = first.next;
        n--;
        return item;
    }


    public int peek() {
        if (isEmpty()) return Integer.MIN_VALUE;
        return first.item;
    }

}

class Queue {
    private Node first;
    private Node last;
    private int n;

    private static class Node{
        private int item;
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

    public int peek() {
        if (isEmpty()) return Integer.MIN_VALUE;
        return first.item;
    }

    public void enqueue(int item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }

    public int dequeue() {
        if (isEmpty()) return Integer.MIN_VALUE;
        int item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        return item;
    }
}


public class EDD {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList L1=new LinkedList();
        L1.push(3);
        L1.push(6);
        L1.push(1);
        L1.push(10);
        L1.push(7);
        L1.push(2);
        L1.push(1);
        System.out.println(L1.find_the_chosen_one());
        LinkedList L2=new LinkedList();
        L2.push(8);
        L2.push(1);
        L2.push(9);
        L2.push(2);
        L2.push(5);
        L2.push(1);
        L2.push(1);
        System.out.println(L2.find_the_chosen_one());
        LinkedList L3=new LinkedList();
        L3.push(102);
        L3.push(58);
        L3.push(21);
        L3.push(45);
        L3.push(226);
        L3.push(2);
        L3.push(14);
        L3.push(31);
        L3.push(79);
        L3.push(5);
        L3.push(7);
        L3.push(19);
        L3.push(9);
        L3.push(10);
        L3.push(47);
        L3.push(3);
        System.out.println(L3.find_the_chosen_one());
        LinkedList L4=new LinkedList();
        L4.push(10);
        L4.push(21);
        L4.push(3);
        L4.push(5);
        L4.push(45);
        L4.push(26);
        L4.push(12);
        System.out.println(L4.find_the_chosen_one());
        /*LinkedList L5=new LinkedList();
        L5.push(1);
        L5.push(6);
        L5.push(0);
        L5.push(1);
        L5.push(6);
        System.out.println(L5.find_the_chosen_one());*/
        
        

        
        
        
        
        // TODO code application logic here
    }
    
}

