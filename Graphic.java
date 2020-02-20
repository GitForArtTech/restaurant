package 沙漠美食指南;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Graphic extends JFrame {
	Image img;

	public static void main(String[] args) {
		Graphic ai = new Graphic();
	}

	public Graphic() {
		super("Image Frame");
		img = Toolkit.getDefaultToolkit().getImage("abc.jpg");
		this.setSize(800, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 關閉
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		g.drawImage(img, 100, 100, this);
	}
}
