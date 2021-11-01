/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea5;

/**
 *
 * @author Fernando
 * 
 * Aquí uso la clase Node1
 */
public class Recorre {
    
    void replace(Node1 Raiz){
        
    }
    void inorden(Node1 root){
        if(root.left!=null){
            inorden(root.left);
            System.out.println(root.value);
        }
        if(root.right!=null){
            
            inorden(root.right);
            
        }
        else{
            System.out.println(root.value);
            
        }
    }
            
    
    
    
    
    
    public static void main(String[] args) {
     Recorre test=new Recorre();
     Node1 a1=new Node1(5);
        Node1 a2=new Node1(2);
        Node1 a3=new Node1(3);
        Node1 a4=new Node1(6);
        Node1 a5=new Node1(7);
        Node1 a6=new Node1(1);
        Node1 a7=new Node1(4);
        a4.right=a6;
        a4.left=a2;
        a6.right=a7;
        a6.left=a5;
        a2.right=a3;
        a2.left=a1;
     test.inorden(a4);
     
     
     }
     
}
