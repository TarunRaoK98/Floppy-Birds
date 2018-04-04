package floppyBirdPackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.imageio.*;

class Wall extends JPanel {

	private Random r = new Random();

	public int X;
	public int Y = r.nextInt(GamePanel.HEIGHT - 400) + 200;		//max=600, min=200
	private int wall_width = 45;

	private int wall_height = GamePanel.HEIGHT - Y;

	private int gap = 200;
	private BufferedImage wall = null;

	public static int speed = -6;


	public Wall(int X) {
		this.X = X;
		loadImage();
	}

	private void loadImage() {
		try {
			wall = ImageIO.read(new File("Images/pipe.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void wallMove() {
		X += speed;
		if(X<=-wall_width) {
			X = GamePanel.WIDTH;
			Y = r.nextInt(GamePanel.HEIGHT - 400) + 200;
			wall_height = GamePanel.HEIGHT - Y;
		}

		Rectangle lower = new Rectangle(X,Y, wall_width, wall_height);
		Rectangle upper = new Rectangle(X,0, wall_width, GamePanel.HEIGHT - (wall_height + gap));

		if(lower.intersects(Bird.birdRate()) || upper.intersects(Bird.birdRate())) {
			boolean option = GamePanel.popup();

			if(option) {
				try {
					Thread.sleep(500);
				} catch(Exception e) {
					e.printStackTrace();
				}
			Bird.reset();
			wallReset();
			}
			else {
				JFrame frame = FloppyBird.getWindow();
				frame.dispose();
				FloppyBird.timer.stop();
			}
		}
	}

	public void drawWall(Graphics g) {
		

		g.drawImage(wall, X, Y, null);		//Bottom wall
		g.drawImage(wall, X, (-GamePanel.HEIGHT) + (Y - gap), null);	//Upper wall
	}

	private void wallReset() {
		Y = r.nextInt(GamePanel.HEIGHT - 400) + 200;
		wall_height = GamePanel.HEIGHT - Y;

		GamePanel.over = true;
	}

}
