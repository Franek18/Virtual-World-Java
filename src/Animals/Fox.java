/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animals;

import java.util.Random;
import wirtualny_swiat_java.World;

/**
 *
 * @author Franciszek
 */
public class Fox extends Animal{

        public Fox(int y, int x, World world){
            super(3,7,y,x,world);
        } 
        
        @Override
	public void action(){
            ++this.age;
            int tab[] = new int[2];
            tab = world.setMove(y, x);
            newY = tab[0];
            newX = tab[1];
            
            if(world.getOrganisms()[newY][newX] == null){
                world.showMessage("The organism of type "+world.showOrganism(this)+" move from position ("+y+", "+x+") to position ("+newY+", "+newX+")");
                world.ChangeOrganism(y, x, newX, newY, this);
            }
            else if(world.getOrganisms()[newY][newX].getPower() > this.getPower()){
                world.showMessage("The Fox is staying in the same place in ware of stronger organism!"); 
            }
            else{
                collision(newY,newX,true);
                if(world.getOrganisms()[newY][newX] == null){
                    world.showMessage("The organism of type "+world.showOrganism(this)+" move from position ("+y+", "+x+") to position ("+newY+", "+newX+")");
                    world.ChangeOrganism(y, x, newX, newY, this);
                }                
            }
        }        
}
