package cn.zrong;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import cn.zrong.entity.City;
import cn.zrong.entity.Friend;
import cn.zrong.entity.GameItem;
import cn.zrong.entity.Group;
import cn.zrong.entity.Mail;
import cn.zrong.entity.Request;
import cn.zrong.entity.User;
import cn.zrong.weibobinding.BindingAccount;

public class ApplicationData {

	public static User currentUser  = new User("", "", "");

	public static List<BindingAccount> bindingUserList = new ArrayList<BindingAccount>();

	public static String temporaryAccountName;
	public static String temporaryPwd;

	public static List<City> cityList = new ArrayList<City>();

	public static List<GameItem> gameItemListOfClient = new ArrayList<GameItem>();

	public static List<Friend> friendsList = new ArrayList<Friend>();

	public static List<Friend> verifyFriendList = new ArrayList<Friend>();
	public static List<Group> verifyGroupList = new ArrayList<Group>();

	public static List<Mail> mailList = new ArrayList<Mail>();

	public static List<Request> requestList = new ArrayList<Request>();

	public static Random rand = new Random();

	public static Bitmap[] numBms;

	public static int getRandomTextColor() {
		return textColors[rand.nextInt(textColors.length)];
	}

	// 描述文字的随机色值
	public static int[] textColors = new int[] { 0xffeabf98, 0xff9bb7eb,
			0xffeca3ca, 0xffedb5ae, 0xffede8af, 0xffd7edb1, 0xffb9eab3,
			0xffa9e9d3, 0xffa8e6ec, 0xffabb6ec, 0xffc6acee };

	public static long sysTime = System.currentTimeMillis();
	
	public static void clear(){
		mailList.clear();
		requestList.clear();
		friendsList.clear();
		gameItemListOfClient.clear();
		cityList.clear();
	}

}
