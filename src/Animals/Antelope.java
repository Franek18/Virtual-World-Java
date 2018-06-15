/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animals;

import Organism.Organism;
import java.util.Random;
import wirtualny_swiat_java.World;

/**
 *
 * @author Franciszek
 */
public class Antelope extends Animal{

        public Antelope(int y, int x, World world){
            super(4,4,y,x,world);
        } 
        
        @Override
        public void action(){
            ++this.age;
            int tab[] = new int[2];
            tab = world.setMove(y, x);
            newY = tab[0];
            newX = tab[1];
            
            do{
                tab = world.setMove(newY, newX);
            } while(tab[0] == y && tab[1] == x);
            
            newY = tab[0];
            newX = tab[1];
            
            if(world.getOrganisms()[newY][newX] == null){
                world.showMessage("The organism of type "+world.showOrganism(this)+" move from position ("+y+", "+x+") to position ("+newY+", "+newX+")");
                world.ChangeOrganism(y, x, newX, newY, this);
            }
            else{
                collision(newY,newX,true);
                if(world.getOrganisms()[newY][newX] == null){
                    world.showMessage("The organism of type "+world.showOrganism(this)+" move from position ("+y+", "+x+") to position ("+newY+", "+newX+")");
                    world.ChangeOrganism(y, x, newX, newY, this);
                }                
            }            
        }
        @Override
	public void collision(int newY, int newX, boolean isAttack){
            Random rand = new Random();
            int miss = rand.nextInt(100);
            int moveY = 0;
            int moveX = 0;            
                if (!isAttack && miss > 49) {
                    boolean isRun = false;
                   int tab[][] = world.returnPlaceToBorn(y, x);
                   for(int i = 0;i<tab.length;++i){
                       moveY = tab[i][0];
                       moveX = tab[i][1];
                       if(world.getOrganisms()[moveY][moveX] == null){
                           isRun = true;
                           break;
                       }
                   }
                   if(isRun){
                    world.showMessage("The Antelope is running away on position ("+moveY+", "+moveX+")!"); 
                    world.ChangeOrganism(y, x, moveX, moveY, this);                                             
                   }
                   else{
                    super.collision(newY, newX, isAttack);                       
                   }

		}
                else{
                    super.collision(newY, newX, isAttack);
                }
        }        
}
