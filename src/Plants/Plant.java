/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plants;
import Organism.Organism;
import java.util.Random;
import wirtualny_swiat_java.World;
/**
 *
 * @author Franciszek
 */
public class Plant extends Organism{
    
    protected boolean isSpread = false;
    public Plant(int power,int y, int x, World world){
            super(power,0,y,x,world);
    }    
    @Override
    public void action() {
        ++this.age;
        isSpread = false;
        Random rand = new Random();
        int spread = rand.nextInt(100);
        int tab[][] = world.returnPlaceToBorn(y, x);
        for(int i = 0;i<tab.length;++i){
            newY = tab[i][0];
            newX = tab[i][1];
            if(world.getOrganisms()[newY][newX] == null && spread > 79){
                isSpread = true;
                world.setOrg(world.showOrganism(this));
                world.setOrganism(newY,newX);          
                break;
            }
        }
/*        if(!isSpread){
                String type = world.showOrganism(this);
                world.showMessage("The organism type "+type+" from position ("+this.y+", "+this.x+") can't spread anymore!");
            }*/        
        
    }

    @Override
    public void collision(int newY, int newX, boolean isAttack) {
        if(!isAttack){
            String type = world.showOrganism(this);
            world.showMessage("Organism type "+type+" from position ("+this.getY()+", "+this.getX()+") has just been eaten!");
            world.getOrganisms()[y][x] = null;            
        }

    }
    
}
