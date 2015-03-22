package ui.common.label;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 带有图片的label
 * @author lsy
 * @version 2015年3月22日  下午6:06:56
 */
public class ImgLabel extends JLabel{

	
	/** serialVersionUID */
	private static final long serialVersionUID = 4941915864868989205L;

	public ImgLabel(int x, int y, Image img) {
		this.setOpaque(false);
		ImageIcon icon = new ImageIcon(img);
		icon.setImage(icon.getImage().getScaledInstance(80,80,Image.SCALE_DEFAULT));		
		this.setBounds(x, y, icon.getImage().getWidth(null),icon.getImage().getWidth(null));
		this.setIcon(icon);
		this.repaint();
	}
}
