/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animals;

import Organism.Organism;
import wirtualny_swiat_java.GridWorld;
import wirtualny_swiat_java.HexWorld;
import wirtualny_swiat_java.World;
/**
 *
 * @author Franciszek
 */
public abstract class Animal extends Organism{
    	

        
        public Animal(int power, int initiative, int y, int x, World world){
            super(power,initiative,y,x,world);
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
            else{
                collision(newY,newX,true);
                if(world.getOrganisms()[y][x] != null && world.getOrganisms()[newY][newX] == null){
                    world.showMessage("The organism of type "+world.showOrganism(this)+" move from position ("+y+", "+x+") to position ("+newY+", "+newX+")");
                    world.ChangeOrganism(y, x, newX, newY, this);
                }                
            }
        }
        @Override
	public void collision(int newY, int newX, boolean isAttack){
		if (world.getOrganisms()[newY][newX].getClass() == world.getOrganisms()[y][x].getClass()) {
                    int bornY = 0;
                    int bornX = 0; 
                    int tab[][] = null;
                    boolean isBorn = false;                    
                    tab = world.returnPlaceToBorn(y, x);
                    for(int i = 0;i<tab.length;++i){
                        bornY = tab[i][0];
                        bornX = tab[i][1];
                        if(world.getOrganisms()[bornY][bornX] == null){
                            isBorn = true;
                            break;
                        }
                    }
                        if(isBorn){
                            world.setOrg(world.showOrganism(this));
                            world.setOrganism(bornY,bornX);
                        }
                        else{
                            world.showMessage("The organisms can't proliferate!");                        
                            newX = x;
                            newY = y;
                        }
		}

		else if (isAttack) {
                        world.getOrganisms()[newY][newX].collision(y, x, false);
                        if(world.getOrganisms()[newY][newX] == null){
                            world.removeOrganism(newY, newX);
                        }
		}
		else if (!isAttack) {
                        Organism attacker = world.getOrganisms()[newY][newX];
                        if(world.getOrganisms()[newY][newX].getPower() > world.getOrganisms()[y][x].getPower()){
                            String type = world.showOrganism(this);
                            world.showMessage("Organism type "+type+" from position ("+this.getY()+", "+this.getX()+") is going to die!");
                            world.getOrganisms()[y][x] = null;
                        }
                        else{
                            String type = world.showOrganism(attacker);
                            world.showMessage("Organism type "+type+" from position ("+attacker.getY()+", "+attacker.getX()+") is going to die!");
                            attacker = null;                            
                        }
		}         
        }
}
