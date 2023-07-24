package javagame;

import org.newdawn.slick.*;
import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.SlickException;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;



public class Play extends BasicGameState //BasicGame implements the game loop/ input handling
{
    private Music music; //creating variables for the sounds
    private Sound pickCoin;
    private Sound win;
	ArrayList<Animation> coins = new ArrayList<Animation>(); //arrayList fot the 5 coins
	Image bgImage;
	int ID;
    private Animation sprite; //the chatacter variable
    private Animation coin1,coin2,coin3,coin4,coin5;// coins variables
    private Animation left; // creating animations for when the user presses on different 
    private Animation right;//arrows
    private Animation up;
    private Animation down;
    boolean quit = false; //a boolean variable that becomes true when the user press on "escape"
    boolean collectCoin1,collectCoin2,collectCoin3,collectCoin4,collectCoin5,winner = false; //boolean variables for the coins. become true when the character picking up a coin
    float x = 500; //x and y variables that are representing the characters initial position
    float y = 450;
    
    
    
    
    

    
	
	
	



public Play(int play) //constructor for the states id
{
	super();
	ID = play;


}



public static void main(String[] args) throws SlickException
{
}

public void reset() { //a method that brings all the variables to its initial position
	winner = false;// and will be activated once
	collectCoin1 = false;
	collectCoin2 = false;
	collectCoin3 = false;
	collectCoin4 = false;
	collectCoin5 = false;
	x = 500;
	y= 450;
	music.loop();
	win.stop();
}
public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException //update first, then render //determines FPS
{
	//renders the game into a frame
	//method to render the map

	Image bgImage = new Image("res/map1.png"); // creates and draws the background image, the characters and the coin
	g.drawImage(bgImage,  0, 0);
	sprite.draw(x,y);
	g.drawString("Char X: " + x + "\nChar Y: " + y, 800,50);
	Image coin1 = new Image("res/coin1.png");
	Image coin2 = new Image("res/coin2.png");
	Image coin3 = new Image("res/coin3.png");
	Image coin4 = new Image("res/coin4.png");
	Image coin5 = new Image("res/coin5.png");
	if (collectCoin1 == false) { //and alghoritm that draws the coins
	coin1.draw(750,430);
	}
	if (collectCoin2 == false) {
	coin2.draw(700,200);
	}
	if (collectCoin3 == false) {
	coin3.draw(400,130);
	}
	if (collectCoin4 == false) {
	coin4.draw(220,233);
	}
	if (collectCoin5 == false) {
	coin5.draw(678,29);
	}

	
	if (quit==true) { //when the user presses on escape it makes quit == true and
		g.drawString("Resume (R)", 400, 250);//opens this small menu
		g.drawString("Main Menu (M)", 400, 300);
		g.drawString("Quit Game (Q)", 400, 350);
	}
	
	if (winner == true) { //once the user collected all of the coins, the variable 'winner' becomes true and the messgae pops
		g.drawString("Cogratulations!! You won the game!", 300,200);
		
	}
  
	


	

}
@Override
public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
{
//loads in maps, images, sprites,and music


    	
    	

   music = new Music("sound/arcade.ogg");
   pickCoin = new Sound("sound/pickCoin.ogg");
   win = new Sound("sound/win.ogg");
   music.loop();
   coins.add(coin1);
   coins.add(coin2);
   coins.add(coin3);
   coins.add(coin4);
   coins.add(coin5);
   Image [] walkLeft = {new Image("res/charLeft.png"), new Image("res/charLeft.png")}; //Image arrays for animation, activates a different animation when the user presses right/left arrows
   Image [] walkRight = {new Image("res/character.png"), new Image("res/character.png")};
   Image [] walkUp = {new Image("res/character.png"), new Image("res/character.png")};
   Image [] walkDown = {new Image("res/character.png"), new Image("res/character.png")};
   
	int [] duration = {300,300};
	left = new Animation (walkLeft, duration, false);
	right = new Animation(walkRight, duration, false);
	up = new Animation(walkUp, duration, false);
	down = new Animation(walkDown, duration, false);
    sprite = right; // initializes the character to look right at initial position


}   






@Override
public int getID() {
//Id getter for the play class
return ID;
}

public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	// //Update method- a method that is basically responsible for everything that is changing
	//on the screen like character movements, going between states etc
	Input input;
	input= gc.getInput(); //gets and input for the user- and moves the character by how the users presses the arrows. includes collision detection at the edges of the screen
	if (input.isKeyDown(Input.KEY_UP))
	{
	    sprite = up;
	    sprite.update(delta);
	    // The lower the delta the slowest the sprite will animate.
	    y -= delta * 0.1f;
	    if (y<1.1) {
	    	y += delta * 0.1f;
	    	
	    }
	}
	else if (input.isKeyDown(Input.KEY_DOWN))
	{
	    sprite = down;
	    sprite.update(delta);
	    y += delta * 0.1f;
	    if (y>450)  {
	    	y -= delta * 0.1f;
	    }
	}
	else if (input.isKeyDown(Input.KEY_LEFT))
	{
	    sprite = left;
	    sprite.update(delta);
	    x -= delta * 0.1f;
	    if (x<93) {
	    	x += delta * 0.1f;
	    }
	}
	else if (input.isKeyDown(Input.KEY_RIGHT))
	{
	    sprite = right;
	    sprite.update(delta);
	    x += delta * 0.1f;
	    if ((x>832)) {
	    	x -= delta * 0.1f;
	    }
	}
	
	if ((x>722) && (x<790) && (y>403)) { //algorithm that is activated when the character collides with the coins that basically makes the boolean variables 'true'
		collectCoin1 = true;//and by that eliminates the inial draw of the coin and creates the effect of "dissappearance"
		pickCoin.play();//activates the sound effect of picking up the coin
	
		}
	
	if ((x>668) && (x<748) && (y>169) && (y<246)) {
		collectCoin2 = true;
		pickCoin.play();
	}
	if ((x>369) && (x<445) && (y>98) && (y<175)) {
		collectCoin3 = true;
		pickCoin.play();
	}
	if ((x>187) && (x<265) && (y>229) && (y<279)) {
		collectCoin4 = true;
		pickCoin.play();
	}
    if ((x>649) && (x<725) && (y<72)) {
		collectCoin5 = true;
		pickCoin.play();
	}
    if ((collectCoin1 == true) && (collectCoin2 == true) && (collectCoin3 == true) && 
    		(collectCoin4 == true) && (collectCoin5 == true)) {
    	winner = true;
    	win.play();
    	music.stop();
    }
	
	
	
	//escape
	if (input.isKeyDown(Input.KEY_ESCAPE)) { //pauses the game once the user presses 'escape'
		quit = true;
		gc.pause();

	}
	if (quit==true) {
		if (input.isKeyDown(Input.KEY_R))  { //if the user presses 'R', resumes to the game
			quit = false;
			gc.resume();
		}
		if (input.isKeyDown(Input.KEY_M)) {	//if he presses 'M', goes back to the menu and activates
			sbg.enterState(0);// the reset() method which brings the play state to default
			quit = false;
			reset();
			gc.resume();
			

		}
		if (input.isKeyDown(Input.KEY_Q)) {	//exits the game completely if user presses 'q'
			System.exit(0);
		}
	}
}

	
}

