/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;
import java.util.ArrayList;
import java.util.*;
/**
 *
 * @author Fernando
 */
public class RepeatChain {
    public static void main(String[] arg){
        RepeatChain ejemplo=new RepeatChain();
        System.out.println(ejemplo.count(1));
        System.out.println(ejemplo.count(55));
        System.out.println(ejemplo.count(4));
        System.out.println(ejemplo.count(10));
        System.out.println(ejemplo.count(30));
    }
    
    boolean esprimo(int num){
        int aux=0;
        for(int i=1;i<=num;i++){
            if(num%i==0){
                aux++;
            }
        }
        if(aux==2){
            return true;
        }else{
            return false;
        }
    }
    
    int frequent(int divisor){
        int suma=0;
        if(esprimo(divisor)==false){
            for(int i=2;i<divisor;i++){
                if(divisor%i==0){
                    if(esprimo(i)==true){
                        suma=suma+(int)Math.pow(2,i)-2;
                    }
                    else{
                        suma=suma+frequent(i);
                    }
                }
            }
        }
        return suma; 
    }
    
    int count(int largo){
        if(largo==1){
            return 0;
        }else{
        List<Integer> L=new ArrayList();
        for(int i=2;i<largo;i++){
            if(largo%i==0){
                L.add(i);
            }
        }
        for(int j:L){

            }
        return resolver(L,0,0,0);
        }
    }
    int resolver(List<Integer> div,int res,int repe,int pos){
        if(pos==div.size()){
            res=res-(pos-1)*2;
            return res-repe;
        }
        else{
            res=(int)Math.pow(2,div.get(pos))+res;
            if(esprimo(div.get(pos))==false){
                repe=repe+frequent(div.get(pos));
            }
        pos++;
        return resolver(div,res,repe,pos);
        }
    } 
}
