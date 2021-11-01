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
public class NumWaySplit {
    
    public int countSplit(String Binary){
        int Split;
        int count=0;
        char[] binary = Binary.toCharArray();
        
        for(int i=0; i<binary.length; i++){
            if (binary[i] == '1' &&  i == binary.length-1){
                Split = 0;
                return Split;
            }
            if (binary[i]=='0'){
                count = count+1;
            }
            
            }
        
        Split = (int) Math.pow(2, count);
        
        
        return Split/2;
    }
    
}
