package ui.panel.allplayers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.UIConfig;

/**
 * 球员详细数据界面的球员数据和平均数据比较的统计图
 * @author cylong
 * @version 2015年4月9日 下午8:09:51
 */
public class Histogram extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 6172308896580893147L;

	/** 整个panel的大小 */
	private Dimension size = new Dimension(150, 150);
	/** 面板下面的名称的大小，就是nameLabel */
	private Dimension nameLabelSize = new Dimension(size.width, 25);
	/** nameLabel的字体 */
	private Font nameFont = new Font("黑体", Font.PLAIN, 15);
	/** 每一条柱最大高度 */
	private int maxHeight = size.height - nameLabelSize.height - 20;
	/** 每条柱的间距还有和边框的间距 */
	private int interval = 20;
	/** 每一条柱的宽度 */
	private int width = (size.width - 3 * interval) / 2;
	/** 球员数据 */
	private double playerData;
	/** 联盟平均数据 */
	private double avgData;
	/** 最大数据 */
	private double maxData;

	/**
	 * @param playerData 球员数据
	 * @param avgData 联盟平均数据
	 * @param max 联盟最大数据
	 * @param name 面板名称
	 * @author cylong
	 * @version 2015年4月9日 下午8:43:02
	 */
	public Histogram(double playerData, double avgData, double max, String name) {
		this.setPreferredSize(size);
		this.setLayout(new BorderLayout());
		this.playerData = playerData;
		this.avgData = avgData;
		this.maxData = max;
		this.addNameLabel(name);
		this.setOpaque(false);
	}

	private void addNameLabel(String name) {
		JLabel nameLabel = new JLabel(name, JLabel.CENTER);
		nameLabel.setOpaque(true);
		nameLabel.setFont(nameFont);
		nameLabel.setPreferredSize(nameLabelSize);
		nameLabel.setBackground(new Color(230, 230, 230, 80));
		this.add(nameLabel, BorderLayout.SOUTH);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int playerH = (int)((playerData / maxData) * maxHeight);
		int playerY = size.height - nameLabelSize.height - playerH;
		g.setColor(Color.BLUE);
		g.fillRect(interval, playerY, width, playerH);

		int avgH = (int)((avgData / maxData) * maxHeight);
		int avgY = size.height - nameLabelSize.height - avgH;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(interval * 2 + width, avgY, width, avgH);

		// 画数字
		g.setColor(Color.BLACK);
		DecimalFormat df = UIConfig.FORMAT;
		g.drawString(df.format(playerData), interval + 5, playerY - 10);
		g.drawString(df.format(avgData), interval * 2 + width + 5, avgY - 10);
	}

}
