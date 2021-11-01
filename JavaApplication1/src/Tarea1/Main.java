/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea1;


public class Main {
    public static void main(String[] args){
       
        NumWaySplit First = new NumWaySplit();
        int a = First.countSplit("100");
        System.out.println(a);
        int b = First.countSplit("110");
        System.out.println(b);
        int c = First.countSplit("10111010101010");
        System.out.println(c);
        int d = First.countSplit("10010100010");
        System.out.println(d);
        int e = First.countSplit("100101101000111001");
        System.out.println(e);
        int f = First.countSplit("10010");
        System.out.println(f);
        
        System.out.println("----------------------------------------");

        
        TeamSoldier Second = new TeamSoldier(); 
        int[] start ={0,2};
        int ej1 = Second.swapSoldier(start);
        int[] start1 ={10,3};
        int ej2 = Second.swapSoldier(start1);
        int[] start2 ={0,0,0,8};
        int ej3 = Second.swapSoldier(start2);
        int[] start3 ={0,1,1,2};
        int ej4 = Second.swapSoldier(start3);
        int[] start4 ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,123456};
        int ej5 = Second.swapSoldier(start4);
        int[] start5 ={1000,2000,3000,4000,5000,6000,7000,8000};
        int ej6 = Second.swapSoldier(start5);
        
        System.out.println(ej1);
        System.out.println(ej2);
        System.out.println(ej3);
        System.out.println(ej4);
        System.out.println(ej5);
        System.out.println(ej6);
        
        
        
    }
}
    