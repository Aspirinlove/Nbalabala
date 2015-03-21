package bl.playerseasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import blservice.PlayerSeasonBLService;
import data.seasondata.PlayerSeasonRecord;
import data.seasondata.SeasonData;
import dataservice.SeasonDataService;
import enums.PlayerSortBasis;
import enums.Position;
import enums.ScreenBasis;
import enums.ScreenDivision;
import enums.SortOrder;

/**
 * 分析球员数据的类
 * @author Issac Ding
 * @version 2015年3月15日 下午2:46:44
 */
public class PlayerSeasonAnalysis implements PlayerSeasonBLService{

	private SeasonDataService seasonData = new SeasonData();
	
	/** 记录上一次返回给UI层，即UI层正在显示的球员列表 */
	private ArrayList<PlayerSeasonRecord> currentList = new ArrayList<PlayerSeasonRecord>();

	/** 刚进入界面时调用此方法，得到的是全部的以名字排序的球员数据 */
	public ArrayList<PlayerSeasonRecord> getAllPlayersSortedByName() {
		currentList = seasonData.getAllPlayerSeasonData();
		sortPlayersByName(currentList);
		return currentList;
	}
	
	/** 得到对当前表重新排序后的表 */
	public ArrayList<PlayerSeasonRecord> getResortedPlayers(PlayerSortBasis basis, SortOrder order) {
		sortPlayers(currentList, basis, order);
		return currentList;
	}
	
	/** 根据位置、地区、筛选依据，返回含有前50个记录的表 */
	public ArrayList<PlayerSeasonRecord> getScreenedPlayers(Position position, ScreenDivision division, 
			ScreenBasis basis){
		
		ArrayList<PlayerSeasonRecord> players = seasonData.getScreenedPlayerSeasonData(position, division);
		
		// 如果没有排序指标依据，那么返回所有符合位置和分区的球员，按名字为序
		if (basis == ScreenBasis.ALL) {
			sortPlayersByName(players);
			currentList = players;
			return players;
		}
		
		// 否则，就按指标选出前50个人
		PlayerScreener screener = new PlayerScreener();
		currentList = screener.screen(players, basis);
		return currentList;
	}
	
	/** 根据球员名字返回所属球队缩写 */
	public String getTeamAbbrByPlayer(String playerName) {
		return seasonData.getTeamAbbrByPlayer(playerName);
	}
	
	/** 根据球员名字返回其赛季数据*/
	public PlayerSeasonRecord getPlayerSeasonDataByName(String playerName) {
		return seasonData.getPlayerSeasonDataByName(playerName);
	}
	
	/** 根据球队缩写返回其当前阵容包含的球员名单  */
	public ArrayList<String> getPlayerNamesByTeamAbbr(String abbr) {
		return seasonData.getPlayerNamesByTeamAbbr(abbr);
	}
	
	/** 根据名字字典顺序为球员排序 */
	private void sortPlayersByName(ArrayList<PlayerSeasonRecord> players){
		Comparator<PlayerSeasonRecord> comparator = new Comparator<PlayerSeasonRecord>() {
			public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
				return p1.getName().compareTo(p2.getName());
			}
		};
		Collections.sort(players, comparator);
	}

	/** 对输入的球员记录列表按排序依据进行排序 */
	private void sortPlayers(ArrayList<PlayerSeasonRecord> players,
						PlayerSortBasis basis, SortOrder order) {
		
		new PlayerSorter().sort(players, basis, order);
	}

}
