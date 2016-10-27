package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月13日 上午11:20:31 类说明
 */
public class UserInfo {
	private Integer userId;
	private String userName;
	private Integer userSex;

	private Location userLoc;

	private List<Kid> kids;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public Location getUserLoc() {
		return userLoc;
	}

	public void setUserLoc(Location userLoc) {
		this.userLoc = userLoc;
	}

	public List<Kid> getKids() {
		return kids;
	}

	public void setKids(List<Kid> kids) {
		this.kids = kids;
	}

	public static UserInfo createUser() {
		UserInfo ui = new UserInfo();
		ui.setUserId(1);
		ui.setUserName("金星");
		ui.setUserSex(1);
		Location userLoc = new Location();
		userLoc.setCountry("china");
		userLoc.setCity("广州");
		userLoc.setDetail("天河中心大道111号");
		ui.setUserLoc(userLoc);
		List<Kid> kids = new ArrayList<Kid>();
		Kid kid1 = new Kid();
		kid1.setKidName("Son Num 1");
		kid1.setKidAge(12);
		kid1.setKidSex(1);
		kids.add(kid1);
		Kid kid2 = new Kid();
		kid2.setKidName("Son Num 2");
		kid2.setKidAge(10);
		kid2.setKidSex(1);
		kids.add(kid2);
		ui.setKids(kids);
		return ui;
	}
}
