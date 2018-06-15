/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualny_swiat_java;

import java.util.Random;

/**
 *
 * @author Franciszek
 */
public class HexWorld extends World{
   
    public  HexWorld(int N, int M,Visualize visualize){
        super(N,M,visualize);
    }
    
    public int[][] returnPlaceToBorn(int y, int x){
        int tab[][] = null;
        int N = this.getN();
        int M = this.getM();
        if ((y > 0 && y < N - 1) && (x > 0 && x < M - 1)) {
            tab = new int[6][2];
            tab[0][0] = y - 1;
            tab[0][1] = x;

            tab[1][0] = y - 1;
            tab[1][1] = x + 1;

            tab[2][0] = y;
            tab[2][1] = x + 1;

            tab[3][0] = y + 1;
            tab[3][1] = x;

            tab[4][0] = y + 1;
            tab[4][1] = x - 1;

            tab[5][0] = y;
            tab[5][1] = x - 1;

            return tab;
        } else if (y == 0) {
            if (x == 0) {
                tab = new int[2][2];
                tab[0][0] = y;
                tab[0][1] = x + 1;

                tab[1][0] = y + 1;
                tab[1][1] = x;

                return tab;
            } else if (x == M - 1) {
                tab = new int[3][2];
                tab[0][0] = y + 1;
                tab[0][1] = x;

                tab[1][0] = y + 1;
                tab[1][1] = x - 1;

                tab[2][0] = y;
                tab[2][1] = x - 1;
                
                return tab;
            }
            tab = new int[4][2];

            tab[0][0] = y;
            tab[0][1] = x + 1;

            tab[1][0] = y + 1;
            tab[1][1] = x;

            tab[2][0] = y + 1;
            tab[2][1] = x - 1;

            tab[3][0] = y;
            tab[3][1] = x - 1;

            return tab;
        } else if (y == N - 1) {
            if (x == 0) {
                tab = new int[3][2];
                tab[0][0] = y - 1;
                tab[0][1] = x;

                tab[1][0] = y - 1;
                tab[1][1] = x + 1;

                tab[2][0] = y;
                tab[2][1] = x + 1;
                return tab;
            } else if (x == M - 1) {
                tab = new int[2][2];
                tab[0][0] = y - 1;
                tab[0][1] = x;

                tab[1][0] = y;
                tab[1][1] = x - 1;

                return tab;
            }
            tab = new int[4][2];

            tab[0][0] = y - 1;
            tab[0][1] = x;

            tab[1][0] = y - 1;
            tab[1][1] = x + 1;

            tab[2][0] = y;
            tab[2][1] = x + 1;

            tab[3][0] = y;
            tab[3][1] = x - 1;
            return tab;
        } else if (x == 0 && (y > 0 && y < N - 1)) {
            tab = new int[4][2];

            tab[0][0] = y - 1;
            tab[0][1] = x;

            tab[1][0] = y - 1;
            tab[1][1] = x + 1;

            tab[2][0] = y;
            tab[2][1] = x + 1;

            tab[3][0] = y + 1;
            tab[3][1] = x;
            return tab;
        } else if (x == M - 1 && (y > 0 && y < N - 1)) {
            tab = new int[4][2];

            tab[0][0] = y - 1;
            tab[0][1] = x;

            tab[1][0] = y + 1;
            tab[1][1] = x;

            tab[2][0] = y + 1;
            tab[2][1] = x - 1;

            tab[3][0] = y;
            tab[3][1] = x - 1;
            return tab;
        }
        return tab;
    }
    @Override
    public int[] setMove(int y,int x){
	int N = this.getN();
	int M = this.getM();        
        int[] tab= new int[2];
        int move = 10;
        Random rand = new Random();
        /*
            Maksymalny zasieg kierunków ruchu to 6 i wyglądają one następująco:
            dla danego (y,x)
            Góra: (y-1,x),(y-1,x+1)
            Środek: (y,x-1), (y,x+1)
            Dół: (y+1,x-1), (y+1,x)
        */ 
        if((y > 0 && y < N - 1) && (x > 0 && x < M - 1)){
		move = rand.nextInt(6);            
        }
	else if (y == 0) {
		do {
			move = rand.nextInt(6);
		} while (move == 0 || move == 1);
		if (x == 0) {
			do {
				move = rand.nextInt(6);
			} while (move == 0 || move == 1 || move == 4 || move == 5);
		}
		else if (x == M - 1) {
			do {
				move = rand.nextInt(6);
			} while (move == 0 || move == 1 || move == 2);
		}
	}
	else if (y == N - 1) {
		do {
			move = rand.nextInt(6);
		} while (move == 3 || move == 4);
		if (x == 0) {
			do {
				move = rand.nextInt(6);
			} while (move == 3 || move == 4 || move == 5);
		}
		else if (x == M - 1) {
			do {
				move = rand.nextInt(6);
			} while (move == 1 || move == 2 || move == 3 || move == 4);
		}
	}

	else if (x == 0 && (y > 0 && y < N - 1)) {
		do {
			move = rand.nextInt(6);
		} while (move == 4 || move == 5);
	}
	else if (x == M - 1 && (y > 0 && y < N - 1)) {
		do {
			move = rand.nextInt(6);
		} while (move == 1 || move == 2);
	}  
	switch (move) {
	case 0:
		if (y > 0) {
			y = y - 1;
			x = x;
		}
		break;
	case 1:
		if (y > 0 && x < M - 1) {
			y= y - 1;
			x = x + 1;
		}
		break;
	case 2:
		if (x < M - 1) {
			x = x + 1;
			y = y;
		}
		break;
	case 3:
		if (y < N - 1) {
			x = x;
			y = y + 1;
		}
		break;
        case 4:
		if (x > 0 && y < N - 1) {
			x = x - 1;
			y = y + 1;
		}
		break;                  
        case 5:
		if (x > 0 ) {
			x = x - 1;
			y = y;
		}
		break;                
	}   
        tab[0] = y;
        tab[1] = x;
        return tab;
    }
}
