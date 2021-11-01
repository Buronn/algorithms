
package test.spoj;


import java.util.*;
import java.lang.*;





public class TestSpoj 
{
	public static void main (String[] args){
	
		
		
		
		
		
	Node root = new Node(8);
        root.left = new Node(7);
        root.left.left = new Node(2);
        root.left.left.right = new Node(3);
        root.right = new Node(7);

        root.left.right = new Node(1);

 

        root.right.right = new Node(2);

        root.right.left = new Node(1);

        root.right.right.left = new Node(3);
	
}
}
