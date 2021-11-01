
package test;


class Node

    {

        private int key;

        private int value;

        Node left;

        Node right;

        Node(int num)

        {

            value = num;

            left = null;

            right = null;

        }

    }

class Main
{       
        boolean palindromico(Node root){
	Node aux;
        aux=root;
        }
        Node verify(Node a,int largo){
            if(a.left==null && a.right==null){
               return a;
            }
            else if(a.left!=null && a.right==null){
                return verify(a.right);
            }
            else if(a.left==null && a.right!=null){
                return verify(a.left);
            }
            else()
        }
        

	public static void main (String[] args)
	{
		
		
		
		
		
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