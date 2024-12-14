/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication34;

/**
 *
 * @author mohammmed
 */
public class JavaApplication34 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int booket;
        double step=0.6;
        int upper =20;
        int lower =21;
        int res;
    if(true) {
        booket = upper;
        upper = lower;
        lower = booket;

        upper = upper * (-1);
        lower = lower * (-1);
         res=50-upper;
    }
        System.out.println(res);
    }
    
}
