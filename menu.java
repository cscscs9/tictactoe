
package tictactoe;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class menu extends BasicGameState {
    Image background;
    
    @Override
    public int getID() {
        return 1;   
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background=new Image("menu.jpg");        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(background,0,0);        
    }

    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        int posX=Mouse.getX();
        int posY=Mouse.getY();
        //clicking play
        if((posX>=255 && posX<=560)&&(posY>=190 && posY<=280)&&
                container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            sbg.enterState(2);//play     
        }
    }
}
