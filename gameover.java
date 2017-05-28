package tictactoe;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class gameover extends BasicGameState {
//menu
    Image background;
    Image tie;
    Image x;
    Image o;    
    
    @Override
    public int getID() {
        return 3;   
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background=new Image("gameover.jpg");
        o = new Image("o.png");
        x = new Image("x.png");
        tie = new Image("tied.jpg");        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(background,0,0);
        if (play.winner == 1) g.drawImage(o, 190, 85);
        else if (play.winner == 2) g.drawImage(x, 190, 85);
        else g.drawImage(tie, 140, 100);        
    }

    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        int posX=Mouse.getX();
        int posY=Mouse.getY();
        
        //clicking menu
        if(((posX>=255 && posX<=555)&&(posY>=180 && posY<=280) )&&
                (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))){            
            sbg.enterState(1);//menu     
        }
    }
}