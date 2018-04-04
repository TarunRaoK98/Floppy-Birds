package floppyBirdPackage;

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.imageio.*;


public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static boolean over = false;
	public static final int HEIGHT = 800;
	public static final int WIDTH = 600;

	public static int score = 0;
	public static boolean ready = false;

	public static int proceed = -1;
	private int x=0;

	private BufferedImage bg;

	Bird bi = new Bird();

	Wall wa = new Wall(GamePanel.WIDTH);
	Wall wa2 = new Wall(GamePanel.WIDTH +(GamePanel.WIDTH/2));
	public GamePanel() {
		loadImage();
		//handles bird movement

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				bi.upMove();
			}
		});
	}

	public void loadImage() {

		try {
			bg = ImageIO.read(new File("Images/bg.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void move() {
		bi.birdMove();
		wa.wallMove();
		wa2.wallMove();

		if(over == true) {
			wa.X =GamePanel.WIDTH;
			wa2.X = GamePanel.WIDTH +(GamePanel.WIDTH/2);
			over = false;
		}

		x += Wall.speed;

		if(x==-600)
		x = 0;

		if(wa.X == Bird.x || wa2.X == Bird.x) {
			score+=1;
		}

	}

	public static boolean popup() {
		int result = JOptionPane.showConfirmDialog(null, "Bad luck baby! Your final score is "+score+".\nWanna restart kya?\n", "Game Over!",JOptionPane.YES_NO_OPTION);

		if(result == JOptionPane.YES_OPTION) {
			return true;
		}
		else
		return false;
	}



	@Override
	public void paint(Graphics g) {
		super.paint(g);

		
		g.drawImage(bg, 0,0, GamePanel.WIDTH, GamePanel.HEIGHT, null);		//g Object basically renders the output and display it for us
		g.drawImage(bg, 0,0, GamePanel.WIDTH, GamePanel.HEIGHT, null);

		bi.drawBird(g);
		wa.drawWall(g);
		wa2.drawWall(g);

		g.setFont(new Font("Tahoma", Font.BOLD, 40));
		g.drawString(Integer.toString(score), WIDTH/2,100);

		if(ready) {
			g.setFont(new Font("Tahoma", Font.BOLD, 150));
			g.drawString(Integer.toString(proceed),WIDTH/2-40, 250);
		}

		
	}
}
