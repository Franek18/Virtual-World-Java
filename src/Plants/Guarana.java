/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plants;

import wirtualny_swiat_java.World;

/**
 *
 * @author Franciszek
 */
public class Guarana extends Plant{
    
    public Guarana(int y, int x, World world){
            super(0,y,x,world);
    }
    
    @Override
    public void collision(int newY, int newX, boolean isAttack) {
        if(!isAttack){
            String type = world.showOrganism(this);
            String type1 = world.showOrganism(world.getOrganisms()[newY][newX]);
            world.showMessage("Organism type "+type+" from position ("+this.getY()+", "+this.getX()+") has just been eaten!");
            world.showMessage("Organism type "+type1+" from position ("+newY+", "+newX+") increase its power by 3!");
            world.getOrganisms()[newY][newX].setPower(world.getOrganisms()[newY][newX].getPower() + 3);
            world.getOrganisms()[y][x] = null;            
        }

    }        
}
