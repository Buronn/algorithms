/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fernando
 */
import java.util.*; 
import java.util.Scanner;
class problema2 
{ 
    //----------------------------------------------------------------------
    static class Node  //NODO
    { 
        int value; 
        Node left;
        Node right;
    }; 
  
    static Node NuevoNodo(int key)  
    { 
        Node aux = new Node(); 
        aux.value = key; 
        aux.left = null; 
        aux.right = null; 
        return aux; 
    }
    //---------------------------------------------------------------------
    static Node BSTInsert(Node root, int key)  // ARBOL BST
    { 
        if(root == null) return NuevoNodo(key); 
        if (key < root.value) root.left = BSTInsert(root.left, key); 
        else root.right = BSTInsert(root.right, key); 
        return root; 
    } 
    
    //----------------------------------------------------------------------
    static void camaras(Node root, int sum)   //LLAMA A LA FUNCION BUSQUEDA 
    { 
        HashSet<Integer> set = new HashSet<>(); 
        
        if (busqueda(root, sum, set)==false) 
            System.out.println(false);
        else
            System.out.println(true);
    } 
    static boolean busqueda(Node root, int suma,HashSet<Integer> a)  
    {
        
        if (root == null) 
            return false; 
        if (busqueda(root.left, suma, a)) 
            return true; 
        if (a.contains(suma - root.value))  //retorna true si encuentra el valor que sumado con el root.value da la suma
        {    
            return true;
        }
        else
            a.add(root.value); //
        return busqueda(root.right, suma, a); 
    }
  
    
  
    
    public static void main(String[] args)  
    { 
        Scanner a = new Scanner(System.in);
        Node root = null; //root del arbol
        
        int largo=a.nextInt();
        
        int sum = a.nextInt();
        for(int i=0;i<largo;i++){
            root=BSTInsert(root,a.nextInt());
        }
        camaras(root, sum); 

    } 
} 
    

