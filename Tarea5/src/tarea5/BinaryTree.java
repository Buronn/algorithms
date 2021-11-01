
package tarea5;
import java.util.Queue;
import java.util.LinkedList;

/**
 *
 * @author Fernando
 */

/*                       USO Class Node en este ejercicio, en el otro uso Node1                               */
public class BinaryTree {
    int Max(Node root,int n){
        
        
        return caminos(root,n);
    }
    
    
    
    int caminos(Node root, int n){
        int Inicio;
        int Final;
        
        int sum=-1;
        Queue<Integer> vacia=new LinkedList();
        for(int i=1;i<=n;i++){
            Inicio=i;
            for(int j=1;j<=n;j++){
                int aux1=0;
                int aux2=0;
                Final=j;
                System.out.println("------"+Inicio+"  "+Final+"-------");
                recorrido(root,Inicio,Final,0,vacia);
                while(!vacia.isEmpty()){
                    aux1=aux1+vacia.remove();
                    aux2=aux2+aux1;
                    
                }
                System.out.println("Camino "+Inicio+"-"+Final +" = "+aux2);
                if(aux2>sum){
                    
                    sum=aux2;
                    
                }
                
                
                
            }
        }
        return sum;
    }
    void recorrido(Node root,int start,int end,int a,Queue<Integer> b){
        /*casos:
            a=0 no encontró el inicio ni el final
            a=1 encontró el inicio
            a=2 encontró el final
            a=5 caso especial
        */
        //System.out.println("                                 "+root.key);
        if(root.key==start && a!=1){ //ENCUENTRA EL INICIO EN LA RAÍZ
            a=1;
            b.add(root.value);
            System.out.println("Inicio: "+root.key+" "+root.value);
            if(root.key<end && root.right!=null){
                
                //System.out.println("pasa x aki");
                recorrido(root.right,start,end,a,b);
            }
            else if(root.key>end && root.left!=null){
                //System.out.println("pasa x aki*");
                recorrido(root.left,start,end,5,b);
            }
            
            else if(root.key<end && root.right==null){
                
            }
            else if(root.key>end && root.left==null){
                
            }
            else if(root.key==end){
                a=2;
                
            }
        }
        else if(root.key>start && a==0){ //START A LA IZQ
            //System.out.println("X "+root.key);
            recorrido(root.left,start,end,a,b);
            a=1;
            //System.out.println("               Actual key: "+root.key);
            if(root.key==end && root.key!=start){
                a=2;
                b.add(root.value);
                System.out.println("Final*="+root.key+" "+root.value);
            }
            if(root.key<end && root.right!=null && a!=2 && root.key!=start){
                //System.out.println("**");
                b.add(root.value);
                System.out.println("encola: "+root.key+" "+root.value);
                recorrido(root.right,start,end,a,b);
                
                a=2;
            }
            if(root.key>end && root.left!=null && a!=2 && root.key<start){
                //System.out.println("***");
                a=2;
                
                
            }
            else if(root.key>end && root.left!=null && a==1 && root.key>start){
                if(end<start && root.key-1<=start && start-end>2){
                b.add(root.value);
                System.out.println("encola: "+root.key+" "+root.value);
                }
                else if(end<start && root.key-1==start && root.key-2>end && root.left!=null){
                    if(root.left.key!=end && root.left.key==start){
                        b.add(root.value);
                        System.out.println("encolao: "+root.key+" "+root.value);
                    }
                }
                else if(start-end==1 && root.left!=null && end+2==root.key){
                    if(root.left.key==start){
                        b.add(root.value);
                        System.out.println("eNcola: "+root.key+" "+root.value);
                    }
                }
                
                //System.out.println("***o");
                a=2;
                
                
            }
            
            
        }
        else if(root.key<start && a==0){ //START A LA DER
            //System.out.println("XX "+root.key);
            recorrido(root.right,start,end,a,b);
            
            a=1;
            //System.out.println("                Actual Keyy: "+root.key);
            if(root.key==end && root.key!=start){
                a=2;
                b.add(root.value);
                System.out.println("Final*"+root.key+" "+root.value);
            }
            if(root.key<end && root.right!=null && a==1 && root.key<start){
                //System.out.println("**'");
                if(root.key+1!=end){
                    if(end-start==1 && root.key+2==end && root.right!=null){
                        if(root.right.key!=end){
                        b.add(root.value);
                        System.out.println("encolaR: "+root.key+" "+root.value);
                        }
                        else{
                            a=2;
                        }
                    }
                    else if(end-start>0 && root.right!=null){
                        if(root.right.key+1!=end){
                        b.add(root.value);
                        System.out.println("encolaR*: "+root.key+" "+root.value);
                        }
                    }
                    //System.out.println("lol");
                    if(root.right.key!=start && end!=start && root.right.key!=end){
                    recorrido(root.right,start,end,a,b);
                    }
                }
                a=2;
            }
            else if(root.key<end && root.right!=null && a==1 && root.key>start){
                //System.out.println("**o");
                
                recorrido(root.right,start,end,a,b);
                a=2;
            }
            if(root.key>end && root.left!=null && a==1 && root.key>start){
                //System.out.println("++");
                recorrido(root.left,start,end,a,b);
                a=2;
            }
            else if(root.key>end && root.left!=null && a==1 && root.key<start){
                b.add(root.value);
                System.out.println("encolar: "+root.key+" "+root.value);
                //System.out.println("++o");
                recorrido(root.left,start,end,a,b);
                a=2;
            }
            
            
            
        }
        if(root.key>end && a==1 && root.key!=start){
            System.out.println("               key: "+root.key);
            if(root.left!=null){
                b.add(root.value);
                System.out.println("encola:** "+root.key+" "+root.value);
                recorrido(root.left,start,end,a,b);
            }
            
            
        }
        else if(root.key<end && a==1 && root.key>start){
            System.out.println("                 key: "+root.key);
            if(root.right!=null && end-start!=2){
                b.add(root.value);
                System.out.println("encola:++"+root.key+" "+root.value);
                recorrido(root.right,start,end,a,b);
            }
        }
        else if(root.key<end && root.key<start && a==1 && start-end>=2){
            if(root.right!=null){
                b.add(root.value);
                System.out.println("encola2: "+root.key+" "+root.value);
                recorrido(root.right,start,end,a,b);
            }
        }
        else if(root.key<end && a==5 && root.key!=start){
            System.out.println("                 key: "+root.key+"              a=5");
            if(root.right!=null){
                b.add(root.value);
                System.out.println("encola:++"+root.key+" "+root.value);
                recorrido(root.right,start,end,a,b);
            }
            
            
        }
        else if(root.key>end && a==5 && root.key!=start){
            //System.out.println("                 key: "+root.key+"              a=5");
            if(root.left!=null){
                b.add(root.value);
                System.out.println("encola:++"+root.key+" "+root.value);
                recorrido(root.left,start,end,a,b);
            }
            
            
        }
        
        
        
        if(root.key==end && a!=2){
            
            b.add(root.value);
            System.out.println("Final: "+root.key+" "+root.value);
            
        }
                
    }
        
    
    
   
    public static void main(String[] args) {
        // TODO code application logic here
        BinaryTree test=new BinaryTree();
        Node a1=new Node(1,12);
        Node a2=new Node(2,2);
        Node a3=new Node(3,25);
        Node a4=new Node(4,4);
        Node a5=new Node(5,11);
        Node a6=new Node(6,6);
        Node a7=new Node(7,15);
        a4.right=a6;
        a4.left=a2;
        a6.right=a7;
        a6.left=a5;
        a2.right=a3;
        a2.left=a1;
        
        test.Max(a4, 7);
        
        System.out.println("||||||||||||RESULTADO: "+test.Max(a4, 7)+"||||||||||||");
        
    
        
    }
    
        
      
        
        
        
        
        
    }
    

