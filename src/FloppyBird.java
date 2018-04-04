package floppyBirdPackage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.Timer;

public class FloppyBird {

	//We'll have 2 panels. The first panel is like a welcome message and 2nd one is the game
	private static JFrame window;
	public static Timer timer;

	public static Timer timer2;

	private int starter = 3;
	// Constructor to build empty window 
	public FloppyBird() {
		window = new JFrame();			//new frame created
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allows us to terminate method on clicking close button
		window.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);		// Sets size of the window which is displayed (width,height)
		window.setLocationRelativeTo(null);	//Pops up window in the middle of screen
		window.setTitle("Floppy Bird");		//Sets title of the game
		window.setResizable(true);		//Cannot resize window anymore
		window.setVisible(true); 		// Displays the window
	}

	private void render() {
		MenuPanel menu = new MenuPanel();	//Initialise game panel
		GamePanel game = new GamePanel();

		timer = new Timer(20,new ActionListener() {		//Fires an action event after every 20 milli seconds in swing timer to perform as task continuously.

			@Override
			public void actionPerformed(ActionEvent e) {
				game.repaint();
				game.move();
				
			}
		});
		window.add(menu);
		window.setVisible(true);

		while(menu.start == false) {
			try {
				Thread.sleep(10);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		window.remove(menu);
		window.add(game);

		game.setVisible(true);
		window.revalidate();
		//timer.start();
		timer2 = new Timer(1000,new ActionListener() {

		@Override
			public void actionPerformed(ActionEvent e) {
				starter--;
				GamePanel.proceed = starter;
				GamePanel.ready = true;
				game.repaint();

				if(starter==0) {
					timer2.stop();
					timer.start();
					GamePanel.ready = false;
				}
			}
		});
		timer2.start();
		
	}

	public static JFrame getWindow() {
		return window;
	}
	public static void main(String[] args) {
		FloppyBird fb = new FloppyBird();
		fb.render();
	}
}
