package vo;

import java.awt.Image;

/**
 * 进步最快球员的VO
 * @author Issac Ding
 * @version 2015年4月8日  下午7:29:48
 */
public class HotFastestPlayerVO {
	
	public HotFastestPlayerVO(Image portrait, String name, String teamAbbr,
			double property) {
		super();
		this.portrait = portrait;
		this.name = name;
		this.teamAbbr = teamAbbr;
		this.property = property;
	}
	
	private Image portrait;
	private String name;
	private String teamAbbr;
	private double property;
	
	public Image getPortrait() {
		return portrait;
	}
	public String getName() {
		return name;
	}
	public String getTeamAbbr() {
		return teamAbbr;
	}
	public double getProperty() {
		return property;
	}

}