package ui.panel.allteams;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.controller.MainController;

/**
 * 球队赛季数据界面
 * @author lsy
 * @version 2015年3月21日  上午12:28:47
 */
public class TeamGamePanel extends TeamSeasonPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 5247981003029326464L;
	ImgButton imgButton;
	String url = UIConfig.IMG_PATH+"teams/";
	
	public TeamGamePanel(MainController controller, String url,TeamButton teamButton) {
		super(controller, url, teamButton);
		addFindButton();
	}
	
	public void addFindButton(){
		 imgButton = new ImgButton(url+"search.png",893,250,url+"searchOn.png",url+"searchClick.png");
		 this.add(imgButton);
	}
	
	public void setEffect() {
		button[2].setOpaque(true);
		button[2].setBackground(UIConfig.BUTTON_COLOR);
		button[2].setForeground(Color.white);
}
	public void addListener(){
		MouListener mou1 = new MouListener();
		for(int i = 0; i < 3; i++) {
			button[i].addMouseListener(mou1);
		}
	}
	
	class MouListener extends MouseAdapter{
		 public void mousePressed(MouseEvent e) {
			 if(e.getSource() == button[0]){
				 controller.toTeamSeasonPanel(TeamGamePanel.this,teamButton);
			 }else if(e.getSource() == button[1]){
				 controller.toTeamPlayerPanel(TeamGamePanel.this,teamButton);
			 }else if(e.getSource() == button[2]){
				 //TODO
			 }
		 }
	}
	

}
