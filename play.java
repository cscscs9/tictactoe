package tictactoe;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class play extends BasicGameState {
//menu
    Image background;
    Image o;
    Image x;
    int player = 1; //1 for player1, 2 for player2
    public static int winner; //1 for player1, 2 for player2, 0 for tie
    public static int board[][] = {{0,0,0},{0,0,0},{0,0,0}};
    
    @Override
    public int getID() {
        return 2;   
    }
    
    @Override
    public void enter(GameContainer gc, StateBasedGame sg) throws SlickException{
        for (int c = 0; c < 3; c++) {
            for (int r = 0; r < 3; r++) {
                play.board[r][c] = 0;
            }
        }
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background=new Image("play.jpg");
        x = new Image("x.png");
        o = new Image("o.png");        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(background,0,0);
        if(player==1)g.drawImage(o, 605,115);
        else g.drawImage(x, 605,115);
        
        for(int c=0;c<3;c++){
            for(int r=0;r<3;r++){
                if (board[r][c] == 1){
                    g.drawImage(o, 50+c*170, 31+r*177);
                } else if (board[r][c] == 2){
                    g.drawImage(x, 50+c*170, 31+r*177);
                }
            }
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        int posX=Mouse.getX();
        int posY=Mouse.getY();
        
        //board is full, players tied
        boolean full=true;
        for(int c=0;c<3;c++){
            for(int r=0;r<3;r++){
                if (board[r][c]==0){
                    full = false;
                    break;
                }
            }
        }        
        if(full){
            winner = 0;
            sbg.enterState(3);
        }
            
        if(((posX>=630 && posX<=765)&&(posY>=35 && posY<=110) )&&
                (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))){
            sbg.enterState(1); //quit        
        }else if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            for (int r=0; r<3; r++){
                for (int c=0; c<3; c++){
                    if ((posX>= c*170+50 && posX<= c*170+215)
                            && (posY>= 365-r*177 && posY<= 562-r*177)){
                        System.out.println(c+" "+r);
                        if (board[r][c]==0){
                            board[r][c]=player;   
                            if (determineWin(board, player)) {
                                winner = player;
                                sbg.enterState(3);
                            }
                            if(player==1)player=2;
                            else player=1;
                        }                        
                    }                    
                }
            }
        }        
    }

    private boolean determineWin(int [][]array, int turn) {
        for(int x=0; x<3; x++){
            for(int y=0; y<3; y++){
                if(array[x][y]==turn){
                    int[] row={-1,1, 0,0,-1,-1, 1,1};
                    int[] col={ 0,0,-1,1,-1, 1,-1,1};
                    int count=0, intR,intC;
                    for(int d=0;d<8;d++){
                        count=1;
                        intR=x;
                        intC=y;
                        for(int c=1;c<=3;c++){ //check boundaries with next increment
                            if((intR+row[d]>=0 && intR+row[d]<3) && 
                                    (intC+col[d]>=0 && intC+col[d]<3)){
                                intR+=row[d];
                                intC+=col[d];
                                if(array[intR][intC]==turn)count++;  //count a correct spot
                                else break; //incorrect digit found
                            } else break; //didn't fall within boudaries
                        }                
                        if(count==3){ //a count of 3 indicates a win 
                            return true;        
                        }             
                    }                          
                }
            }
        }  
        return false;
    }
}
