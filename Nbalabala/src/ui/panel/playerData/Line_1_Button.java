package ui.panel.playerData;

import ui.common.button.TextButton;

/**
 * 第一行按钮
 * 
 * @author lsy
 * @version 2015年3月19日 下午2:47:26
 */
public class Line_1_Button extends TextButton {

	/** serialVersionUID */
	private static final long serialVersionUID = -4055004512100806741L;
	static Line_1_Button current;

	public Line_1_Button(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}
}
