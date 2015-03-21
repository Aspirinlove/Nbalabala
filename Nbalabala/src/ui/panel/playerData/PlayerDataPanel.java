package ui.panel.playerData;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.common.button.ImgButton;
import ui.common.button.TextButton;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import bl.playerseasonbl.PlayerSeasonAnalysis;
import blservice.PlayerSeasonBLService;
import data.seasondata.PlayerSeasonRecord;
import enums.Position;
import enums.ScreenBasis;
import enums.ScreenDivision;

/**
 * @author lsy
 * @version 2015年3月18日 下午6:28:36
 */
public class PlayerDataPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 代表所有button集合 */
	private Line_1_Button[] buttonLine1;
	private Line_2_Button[] buttonLine2;
	private Line_3_Button[] buttonLine3;
	private Line_4_Button[] buttonLine4;
	private TextButton[] button;

	/** button的宽度 高度 */
	private int width = 60, height = 24, widthThree = 75, widthLong = 135;
	/** 每行的button个数 */
	private int sum1 = 4, sum2 = 9, sum3 = 15, sum4 = 2;
	/** 分别代表第一行到第五行的纵坐标 */
	private int y1 = 27, y2 = 66, y3 = 107, y4 = 138, y5 = 171;
	/** 代表所有列的前三个button的横坐标 */
	private int x1 = 227, x2 = 288, x3 = 349;
	/** “所有”的横坐标 */
	private int allX = 156;
	/** 间隔 */
	private int inter = 61;
	/** 东部 西部 太平洋 西北 西南 */
	private int east = 440, west = east + inter, pacific = west + width + east - x3 - widthThree,
			northWest = pacific + inter + widthThree - width, southWest = northWest + inter;
	/** 得分/篮板/助攻 盖帽 抢断 犯规 失误 分钟 */
	private int three = x3 + inter, block = three + inter + widthLong - width, steal = block + inter, foul = block
			+ 2 * inter, turnover = block + 3 * inter, minute = block + 4 * inter;
	/** 罚球 两双 */
	private int freeThrow = x3 + inter, double_double = freeThrow + inter;
	/** 总计 平均 */
	private int total = 756, average = 823;

	/** 所有button横坐标集合 */
	private int[] buttonXLine1 = new int[] { allX, x1, x2, x3 };
	private int[] buttonXLine2 = new int[] { allX, x1, x2, x3, east, west, pacific, northWest, southWest };
	private int[] buttonXLine3 = new int[] { allX, x1, x2, x3, three, block, steal, foul, turnover, minute, x1,
			x2, x3, freeThrow, double_double };
	private int[] buttonXLine4 = new int[] { total, average };

	/** 所有button上文字部分横坐标集合 */
	private String[] textLine1 = new String[] { "所有", "前锋", "中锋", "后卫" };
	private String[] textLine2 = new String[] { "所有", "东南", "中央", "大西洋", "东部", "西部", "太平洋", "西北", "西南" };
	private String[] textLine3 = new String[] { "所有", "得分", "篮板", "助攻", "得分/篮板/助攻", "盖帽", "抢断", "犯规", "失误", "分钟",
			"效率", "投篮", "三分", "罚球", "两双", "总计" };
	private String[] textLine4 = new String[] { "总计", "平均" };
	
	/** 查询按钮 */
	private ImgButton findButton;
	
	/** 将枚举类型赋值为数组 */
	Position[] pArray = Position.values();
	ScreenDivision[] dArray = ScreenDivision.values();
	ScreenBasis[] bArray = ScreenBasis.values();
	
	/** 通过接口调用方法 */
	PlayerSeasonBLService playerSeason = new PlayerSeasonAnalysis();
	
	public PlayerDataPanel(MainController controller,String url) {
		super(controller, url);
		setButton();
		addButton();
		setEffect(button);
		
		iniSet();
		addListener();
		addFindButton();
		ArrayList<PlayerSeasonRecord> iniArray = playerSeason.getAllPlayersSortedByName();
		//TODO setTable
	}
	
	public void addFindButton(){
		findButton = new ImgButton("images/playerData/search.png",856,124,"images/playerData/searchOn.png","images/playerData/searchClick.png");
		this.add(findButton);
		findButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				ArrayList<PlayerSeasonRecord> seasonArray = playerSeason.getScreenedPlayers(Line_1_Button.current.p,Line_2_Button.current.division,
						Line_3_Button.current.basis);
				//TODO changeTable
			}
		});
	}

	/**
	 * 初始化四个“所有”按钮，初始化为选中状态
	 * @author lsy
	 * @version 2015年3月19日  下午8:44:58
	 */
	public void iniSet() {
		Line_1_Button.current = (Line_1_Button) button[0];
		Line_2_Button.current = (Line_2_Button) button[1];
		Line_3_Button.current = (Line_3_Button) button[2];
		Line_4_Button.current = (Line_4_Button) button[3];
	}

	/**
	 * 设置条件按钮
	 * @author lsy
	 * @version 2015年3月19日  下午9:00:34
	 */
	public void setButton() {
		buttonLine1 = new Line_1_Button[sum1];
		buttonLine2 = new Line_2_Button[sum2];
		buttonLine3 = new Line_3_Button[sum3];
		buttonLine4 = new Line_4_Button[sum4];
		for (int i = 0; i < sum1; i++) {
			buttonLine1[i] = new Line_1_Button(buttonXLine1[i], y1, width, height, textLine1[i]);
			buttonLine1[i].p = pArray[i];
		}
		for (int i = 0; i < sum2; i++) {
			if (i == 3 || i == 6) {
				buttonLine2[i] = new Line_2_Button(buttonXLine2[i], y2, widthThree, height, textLine2[i]);
			} else {
				buttonLine2[i] = new Line_2_Button(buttonXLine2[i], y2, width, height, textLine2[i]);
			}
			buttonLine2[i].division = dArray[i];
		}
		for (int i = 0; i < 10; i++) {
			if (i == 4) {
				buttonLine3[i] = new Line_3_Button(buttonXLine3[i], y3, widthLong, height, textLine3[i]);
			} else {
				buttonLine3[i] = new Line_3_Button(buttonXLine3[i], y3, width, height, textLine3[i]);
			}
			buttonLine3[i].basis = bArray[i];
		}
		for (int i = 10; i < sum3; i++) {
			buttonLine3[i] = new Line_3_Button(buttonXLine3[i], y4, width, height, textLine3[i]);
			buttonLine3[i].basis = bArray[i];
		}
		for (int i = 0; i < sum4; i++) {
			buttonLine4[i] = new Line_4_Button(buttonXLine4[i], y5, width, height, textLine4[i]);
		}
		button = new TextButton[] { buttonLine1[0], buttonLine2[0], buttonLine3[0], buttonLine4[0] };
	}

	/**
	 * 添加条件按钮
	 * @author lsy
	 * @version 2015年3月19日  下午9:00:47
	 */
	public void addButton() {
		for (int i = 0; i < sum1; i++) {
			this.add(buttonLine1[i]);
		}
		for (int i = 0; i < sum2; i++) {
			this.add(buttonLine2[i]);
		}
		for (int i = 0; i < sum3; i++) {
			this.add(buttonLine3[i]);
		}
		for (int i = 0; i < sum4; i++) {
			this.add(buttonLine4[i]);
		}
	}

	/**
	 * 设置按钮初始被选中的效果
	 * 
	 * @author lsy
	 * @version 2015年3月18日 下午11:43:53
	 */
	public void setEffect(TextButton[] button) {
		for (int i = 0; i < button.length; i++) {
			button[i].setOpaque(true);
			button[i].setBackground(new Color(15, 24, 44));
			button[i].setForeground(Color.white);
		}
	}

	/**
	 * 按钮添加监听
	 * @author lsy
	 * @version 2015年3月19日  下午9:00:59
	 */
	public void addListener() {
		MouListener1 mou1 = new MouListener1();
		MouListener2 mou2 = new MouListener2();
		MouListener3 mou3 = new MouListener3();
		MouListener4 mou4 = new MouListener4();
		for (int i = 0; i < sum1; i++) {
			buttonLine1[i].addMouseListener(mou1);
		}
		 for(int i = 0; i< sum2;i++){
		 buttonLine2[i].addMouseListener(mou2);
		 }
		 for(int i = 0; i< sum3;i++){
		 buttonLine3[i].addMouseListener(mou3);
		 }
		 for(int i = 0; i< sum4;i++){
		 buttonLine4[i].addMouseListener(mou4);
		 }
	}

	class MouListener1 extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == Line_1_Button.current) {
				return;
			}
			Line_1_Button.current.back();
			Line_1_Button.current = (Line_1_Button) e.getSource();
		}
	}
	class MouListener2 extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == Line_2_Button.current) {
				return;
			}
			Line_2_Button.current.back();
			Line_2_Button.current = (Line_2_Button) e.getSource();
		}
	}
	class MouListener3 extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == Line_3_Button.current) {
				return;
			}
			Line_3_Button.current.back();
			Line_3_Button.current = (Line_3_Button) e.getSource();
		}
	}
	class MouListener4 extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == Line_4_Button.current) {
				return;
			}
			Line_4_Button.current.back();
			Line_4_Button.current = (Line_4_Button) e.getSource();
		}
	}
}
