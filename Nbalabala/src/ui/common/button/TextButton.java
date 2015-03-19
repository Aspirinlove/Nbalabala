package ui.common.button;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

import ui.UIConfig;

/**
 * 显示文字的button
 * 
 * @author lsy
 * @version 2015年3月18日 下午10:42:39
 */
public class TextButton extends JButton {

	/** serialVersionUID */
	private static final long serialVersionUID = -5390884444460444968L;
	private boolean isSelected = false;
	public boolean isIni = false;

	/**
	 * @param x
	 *            横坐标
	 * @param y
	 *            纵坐标
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param text
	 *            button显示的文字
	 * @author cylong
	 * @version 2015年3月19日 上午2:48:41
	 */
	public TextButton(int x, int y, int width, int height, String text) {
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setBounds(x, y, width, height);
		this.setText(text);
		this.setFont(UIConfig.FONT);
	}

	public void paintComponent(Graphics g) {
		if (getModel().isArmed()||isIni) {
			isSelected = true;
			onClick(g, UIConfig.buttonColor);
		}
		if (isSelected) {
			onClick(g, UIConfig.buttonColor);
		}
		super.paintComponent(g);
	}

	public Graphics onClick(Graphics g, Color color) {
		Graphics2D g2D = (Graphics2D) g.create();
		int h = getHeight();
		int w = getWidth();
		GradientPaint gp = new GradientPaint(0.0F, 0.0F, color, 0.0F, h, color, true);
		g2D.setPaint(gp);
		g2D.fillRect(0, 0, w, h);
		g2D.dispose();
		this.setForeground(Color.white);
		return g;
	}

	public void back() {
		isSelected = false;
		this.setForeground(Color.black);
	}
}
