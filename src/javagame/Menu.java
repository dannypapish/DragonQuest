
package javagame;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

public class Menu extends BasicGameState
{
	int ID;
	Image bgImage;
	Image bgImage2;
	String mouse = "";

public Menu(int state) //constructor- creates the state's ID
{
	ID = state;	
}

public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
{

}

public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
{
	
	bgImage = new Image("res/menu1.png"); // creatinng the image objects for the menu
	bgImage2 = new Image("res/menu2.png");
	g.drawString(mouse, 50, 50);                 //creating everything that is written
	g.drawString("Are you ready?!?!?!", 400, 40);
	g.drawImage(bgImage, 250, 100); // draws the menu pictures
	g.drawImage(bgImage2,250,200 );

}
public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
{ //Update method- a method that is basically responsible for everything that is changing
	//on the screen like character movements, going between states etc
	
	int xpos =Mouse.getX(); //Getting the mouse coordinates and displaying it on the
	int ypos = Mouse.getY();//screen, so that we'll now the coordinates at any given
	mouse = "x: " + xpos + " y: " + ypos; //spot in the screen
	if((xpos >277 && xpos <677) && (ypos>324 && ypos<357)) //when the user presses on  
	{  	// the mouse at a certain spot- it starts the game
		if(Mouse.isButtonDown(0))
		{
			sbg.enterState(1);
		}
	}
	
	else if((xpos >345 && xpos <607) && (ypos>232 && ypos<267))  //goes to credit state
	{  	
		if(Mouse.isButtonDown(0))
		{
			sbg.enterState(2);
		}
	}
	
	
	else if((xpos >410 && xpos <555) && (ypos>142 && ypos<177)) //exits the game
	{
		if(Mouse.isButtonDown(0))
		{
			System.exit(0);
		}
	}




}
public int getID() //getter for the menu's ID
{
	return ID;
}


}
