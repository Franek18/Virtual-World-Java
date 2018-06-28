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
public class Turtle extends Animal{

        public Turtle(int y, int x, World world){
            super(2,1,y,x,world);
        }  
        @Override
	public void action(){
            Random rand = new Random();
            int move = rand.nextInt(100);
            
            if(move > 74){
                super.action();
            }
            else{
                ++this.age;
                world.showMessage("Turtle isn't moving!");
            }

        }
        @Override
	public void collision(int newY, int newX, boolean isAttack){
		if (!isAttack) {
                        Organism.Organism attacker = world.getOrganisms()[newY][newX];
                        if(attacker.getPower() < 5){
                            attacker.missAttack(newY, newX);
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
