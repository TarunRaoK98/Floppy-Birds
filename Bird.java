package floppyBirdPackage;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.imageio.*;

public class Bird {

	private BufferedImage bird = null;
	private static int bird_diameter = 36;

	public static int x = (GamePanel.WIDTH/2) - bird_diameter/2;

	public static int y = (GamePanel.HEIGHT/2);
	private static int speed = 2;
	private int acc = 1;


	public Bird() {
		loadImage();
	}

	public void loadImage() {

		try {
			bird = ImageIO.read(new File("Images/bird1.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void birdMove() {
		if(y>=0 && y<=GamePanel.HEIGHT) {
			speed += acc;
			y+=speed;
		}
		else {
			boolean option = GamePanel.popup();

			if(option) {
				try {
					Thread.sleep(500);
				} catch(Exception e) {
					e.printStackTrace();
				}
				reset();
			}

			else {
				JFrame frame = FloppyBird.getWindow();
				frame.dispose();
				FloppyBird.timer.stop();
			}
		}
	}

	public void upMove() {
		speed = -17;
	}

	public static void reset() {
		speed =2;
		y = GamePanel.HEIGHT/2;
		GamePanel.over = true;
		GamePanel.score = 0;
	}

	public static Rectangle birdRate() {
		Rectangle br = new Rectangle(x,y, bird_diameter, 35);
		return br;
	}

	public void drawBird(Graphics g) {
		g.drawImage(bird, x, y, null);
	}
}
