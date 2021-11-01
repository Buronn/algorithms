/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea1;

/**
 *
 * @author Fernando
 */
public class TeamSoldier {
    public int swapSoldier(int[] start){
      
        for(int i = start.length-1; i>=0; i=i-1){
               int r;     
               if (i==0) {
                   return start[i];
               }
               else if (start[i]/2 >= 1){
                   r = start[i]/2;
                   start[i-1] = start[i-1] + r;
               }
        }
          return 0;
        
    }
}
  
        
   
    