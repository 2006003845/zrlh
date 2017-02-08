package com.zrong.entity;

import java.io.Serializable;

import android.database.Cursor;

public class Stage implements Serializable {

	private static final long serialVersionUID = -4661617808678016575L;

	public int numb, imgId, jumppoint, npc, spacingTime, gameTime, skill,
			isPassed, starNum;
	public String sceneName;

	public Stage() {

	}

	/**
	 * Construct 创建关卡
	 * 
	 * @param numb
	 *            编号
	 * @param jumppoint
	 *            跳跃点
	 * @param npc
	 *            跳跃者
	 * @param sceneName
	 *            场景
	 * @param spacingTime
	 * @param gameTime
	 * @param skill
	 * @param isPassed
	 *            是否过关(0/1)
	 * @param starNum
	 *            过关星数
	 * @param imgId
	 */
	public Stage(int numb, int jumppoint, int npc, String sceneName,
			int spacingTime, int gameTime, int skill, int isPassed,
			int starNum, int imgId) {
		super();
		this.numb = numb;
		this.jumppoint = jumppoint;
		this.npc = npc;
		this.sceneName = sceneName;
		this.spacingTime = spacingTime;
		this.gameTime = gameTime;
		this.skill = skill;
		this.isPassed = isPassed;
		this.starNum = starNum;
		this.imgId = imgId;
	}

	// 列编号
	public final static int NUM_INDEX = 1;
	public final static int JUMPP_INDEX = 2;
	public final static int NPC_INDEX = 3;
	public final static int SCENE_INDEX = 4;
	public final static int SPAC_TIM_INDEX = 5;
	public final static int GAME_TIM_INDEX = 6;
	public final static int SKILL_INDEX = 7;
	public final static int ISPASSED_INDEX = 8;
	public final static int STAR_INDEX = 9;
	public final static int IMGID_INDEX = 10;
	// column_name
	public static final String ID = "_id";
	public static final String NUMB = "stage_numb";
	public static final String JUMPPOINT = "stage_jumppoint";
	public static final String NPC = "stage_npc";
	public static final String SCENE = "stage_scene";
	public static final String SPACING_TIME = "stage_spacingtime";
	public static final String GAME_TIME = "stage_gametime";
	public static final String SKILL = "stage_skill";
	public static final String ISPASSED = "stage_ispassed";
	public static final String STAR_NUM = "stage_starnum";
	public static final String IMGID = "stage_imgid";

	// table_name
	public static final String TAB_NAME = "stage";

	/**
	 * 未过关
	 */
	public static final int UNPASSED = 0;
	/**
	 * 已过关
	 */
	public static final int PASSED = 1;

	/**
	 * 仅使用生命恢复技能
	 */
	public static final int USELIVESKILL = 1;
	/**
	 * 仅使用彩虹桥技能
	 */
	public static final int USEBRIFROSTSKILL = 2;
	/**
	 * 两技能都使用
	 */
	public static final int USEBOTHSKILL = 3;

	/**
	 * 跳跃点为6
	 */
	public static final int JUMP_POINT6 = 2;
	/**
	 * 跳跃点为4,6
	 */
	public static final int JUMP_POINT46 = 6;
	/**
	 * 跳跃点为6,8
	 */
	public static final int JUMP_POINT68 = 3;
	/**
	 * 跳跃点为4,6,8
	 */
	public static final int JUMP_POINT468 = 7;

	/**
	 * 间隔时间
	 */
	public static final int SPACING_TIME_2_3 = 0;
	public static final int SPACING_TIME_2_25 = 1;
	public static final int SPACING_TIME_15_25 = 2;
	public static final int SPACING_TIME_15_2 = 3;
	public static final int SPACING_TIME_1_2 = 4;

	/**
	 * 出现的NPC 学生; 奥托曼; 学生+白领; 安卓+忍者; 豪华小睿+学生; 豪华小睿+奥托曼; 豪华小睿+学生+白领; 豪华小睿+安卓+忍者;
	 * 学生+黑衣人; 忍者+黑衣人+奥托曼; 所有
	 */
	public static final int APPEAR_NPC_STU = 0;
	public static final int APPEAR_NPC_STU_OUTM = 1;
	public static final int APPEAR_NPC_STU_WHITE = 2;
	public static final int APPEAR_NPC_ANDR_NINJA = 3;
	public static final int APPEAR_NPC_DOCTOR_STU = 4;
	public static final int APPEAR_NPC_DOCTOR_OUTM = 5;
	public static final int APPEAR_NPC_DOCTOR_STU_WHITE = 6;
	public static final int APPEAR_NPC_DOCTOR_ANDR_NINJA = 7;
	public static final int APPEAR_NPC_STU_BLACK = 8;
	public static final int APPEAR_NPC_NINJA_BLACK = 9;
	public static final int APPEAR_NPC_ALL = 11;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "编号" + numb + "场景" + sceneName + "isPassed" + isPassed;
	}

	public static Stage getStage(Cursor cursor) {
		if (cursor.getCount() == 0)
			return null;
		Stage stage = new Stage();
		stage.numb = cursor.getInt(Stage.NUM_INDEX);
		stage.jumppoint = cursor.getInt(Stage.JUMPP_INDEX);
		stage.npc = cursor.getInt(Stage.NPC_INDEX);
		stage.sceneName = cursor.getString(Stage.SCENE_INDEX);
		stage.spacingTime = cursor.getInt(Stage.SPAC_TIM_INDEX);
		stage.gameTime = cursor.getInt(Stage.GAME_TIM_INDEX);
		stage.skill = cursor.getInt(Stage.SKILL_INDEX);
		stage.isPassed = cursor.getInt(Stage.ISPASSED_INDEX);
		stage.starNum = cursor.getInt(Stage.STAR_INDEX);
		stage.imgId = cursor.getInt(Stage.IMGID_INDEX);
		return stage;
	}

}
