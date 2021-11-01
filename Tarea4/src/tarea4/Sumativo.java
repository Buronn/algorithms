/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea4;

/**
 *
 * @author Fernando
 */
public class Sumativo {
    long isNumber(long numero){
        long top=numero; // nos serviran para la busqueda binaria
        long resta=numero/10;
        long bot=numero-resta;         //comienzo desde el numero más posible, por ejemplo si busco el 1634 comenzaría del 1634-163=1471 
        long center=bot+(top-bot)/2;   // y así ahorro tiempo de búsqueda binaria si comenzara por el 0
        if(EquivSumativo(bot)==numero){
            
            return bot;
        }
        else{
            bot++;
            if(EquivSumativo(bot)==numero){
           
                return bot;
            }   
            else{
                bot++;
        
                if(EquivSumativo(bot)==numero){
            
                    return bot;
                }
            }
        }
        return busquedaBinaria(bot,center,top,numero);
    }
    long busquedaBinaria(long bot,long center,long top,long n){
        if(bot==center && EquivSumativo(center)!=n){
            return -1;
        }
        if(top==center && EquivSumativo(center)!=n){
        return -1;
        }
        if(EquivSumativo(bot)==n){return center;}
        if(EquivSumativo(center)==n){return center;}
        else{
            if(EquivSumativo(center)<n){
            bot=center;
            long a=(top-bot)/2;
            center=a;
            if(EquivSumativo(center)==n){return center;}
            return busquedaBinaria(bot,center,top,n);
            }
            
            else if(EquivSumativo(center)>n){
            top=center;
            center=bot+((top-bot)/2);
            if(EquivSumativo(center)==n){return center;}
            return busquedaBinaria(bot,center,top,n);
            }
        
        }
        return n;
    }
    long EquivSumativo(long valor){
        long aux=0;
        while(valor!=0){
            aux=aux+valor;
            valor=valor/10;
        }
        return aux;
    }
    
   


    public static void main(String[] args) {
        Sumativo a=new Sumativo();
        long b=26;
        long c=3000;
        long d=1367758989;
        System.out.println(a.isNumber(b));
        System.out.println(a.isNumber(c));
        System.out.println(a.isNumber(d));
        
                                       
    }


}