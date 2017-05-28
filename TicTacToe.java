
package tictactoe;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.*;

public class TicTacToe extends StateBasedGame{
    public TicTacToe(String title){
        super(title);
    }
    
    public static void main(String args[]) throws SlickException{
        AppGameContainer app=new AppGameContainer(new TicTacToe("Tic Tac Toe"));
        app.setDisplayMode(800,600,false);
        app.start();
    }
    @Override
    public void initStatesList(GameContainer container) throws SlickException{
        this.addState(new menu());
        this.addState(new play());
        this.addState(new gameover());
    }
}