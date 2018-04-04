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
import javax.swing.JPanel;
import javax.imageio.*;

public class MenuPanel extends JPanel {

	private static final long version = 1L;
	private BufferedImage img = null;	//Subclass of Image class to handle and manip image data.

	public boolean start = false;

	public MenuPanel() {

		loadImage();

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				start = true;
			}
		});
	}

	private void loadImage() {
		try {
			img = ImageIO.read(new File("Images/welcome.jpg")) ;	//Reads Image from Images folder
		} catch (Exception ex) {
			ex.printStackTrace();		//If image not present, then tells us what happened exactly.
		}
	}

	

	@Override	//Paint method is used to place components to be painted basically.
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(img, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);		//g Object basically renders the output and display it for us
	}

}
