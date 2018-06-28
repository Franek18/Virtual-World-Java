/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plants;

import java.util.Random;
import wirtualny_swiat_java.World;

/**
 *
 * @author Franciszek
 */
public class Dandelion extends Plant{
        public Dandelion(int y, int x, World world){
            super(0,y,x,world);
        }
        
    @Override
    public void action() {
        isSpread = false;
        ++this.age;
        int i = 0;
        while(!isSpread && i < 3){
            super.action();
            --this.age;
            ++i;
        }
    }
    
}
