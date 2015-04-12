package ui.panel.hot.hotSeason;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.common.chart.Chart;
import ui.common.chart.Column;
import ui.common.label.HotSeasonPlayerLabel;
import ui.panel.hot.HotThreeFatherPanel;
import ui.panel.hot.ThreeButton;
import vo.HotSeasonPlayerVO;
import bl.hotquerybl.HotQuery;
import blservice.HotBLService;
import enums.HotSeasonPlayerProperty;

/**
 * 赛季热点球员界面
 * @author lsy
 * @version 2015年4月11日 下午4:02:59
 */
public class HotSeasonPlayerPanel extends HotThreeFatherPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 9066290175387899594L;
	private static final HotSeasonPlayerProperty[] HOT_PLAYER_ARRAY = HotSeasonPlayerProperty.values();
	ArrayList<HotSeasonPlayerVO> playerVO;
	HotBLService hot = new HotQuery();
	HotSeasonPlayerLabel label[] = new HotSeasonPlayerLabel[5];
	Chart  chart;
	
	public HotSeasonPlayerPanel(String url) {
		super(url);
		add_bt_Listener();
		addLabel();
		addChart();
	}
	
	private void addChart() {
		if(chart!=null){
			this.remove(chart);
		}
		ArrayList<Column> columns = new ArrayList<Column>();
		columns.add(new Column(playerVO.get(0).getName(), playerVO.get(0).getProperty(), Color.blue));
		columns.add(new Column(playerVO.get(1).getName(),playerVO.get(1).getProperty(), Color.blue));
		columns.add(new Column(playerVO.get(2).getName(), playerVO.get(2).getProperty(), Color.blue));
		columns.add(new Column(playerVO.get(3).getName(), playerVO.get(3).getProperty(), Color.blue));
		columns.add(new Column(playerVO.get(4).getName(), playerVO.get(4).getProperty(), Color.blue));
		chart = new Chart(text, columns, 21.1);
		chart.setBounds(511, 121, 399, 306);
		this.add(chart);
		chart.updateUI();
		chart.repaint();
	}
	
	/**
	 * 添加监听
	 * @author lsy
	 * @version 2015年4月11日  下午6:09:38
	 */
	public void add_bt_Listener() {
		for (int i = 0; i < select.length; i++) {
			hotButton[i].player = HOT_PLAYER_ARRAY[i];
			hotButton[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					for(int i = 0;i<label.length;i++){
						HotSeasonPlayerPanel.this.remove(label[i]);
					}
					addLabel();
					addChart();
				}

			});
		}
	}

	/**
	 * 添加具体每个球员的label
	 * @author lsy
	 * @version 2015年4月11日  下午6:09:44
	 */
	public void addLabel() {
		playerVO = hot.getHotSeasonPlayers(ThreeButton.current.player);
		for (int j = 0; j < 5; j++) {
			label[j] = new HotSeasonPlayerLabel(playerVO.get(j), ThreeButton.current.player);
			this.add(label[j]);
		}
		this.repaint();
	}
}
