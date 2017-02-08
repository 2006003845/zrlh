package com.zrong.data;

import java.util.ArrayList;
import java.util.HashMap;

import org.jbox2d.common.Vec2;

import com.zrong.entity.Combo;
import com.zrong.entity.ElasticityBed;
import com.zrong.entity.Goods;
import com.zrong.entity.Jumper;
import com.zrong.entity.Player;
import com.zrong.entity.Result;
import com.zrong.entity.Stage;

public class GameData {

	/**
	 * 游戏模式
	 */
	public static final int GAME_MODE_STAGE = 1;
	public static final int GAME_MODE_LIMITTIME = 2;
	public static final int GAME_MODE_ENDLESS = 4;

	public static final String NEXTSTSSGE = "stage_next_numb";

	public static ArrayList<Combo> comboList = new ArrayList<Combo>();

	public static int currentGameMode = 0;

	public static int playLastTime = 0;
	public static int leftTimeOfTimeLimit = -1;

	public static boolean getPropOfStopTime = false;

	public static boolean isUsedRainbowSkill = false;

	/**
	 * 道具列表
	 */
	public static HashMap<String, ArrayList<Goods>> goodsOfStore;

	public static final String PERSONANDSENCE = "person_sence";
	public static final String PROP = "prop";
	// public static final String SCENE = "scene";

	/**
	 * 玩家
	 */
	public static Player player;

	/**
	 * 当前游戏结果
	 */
	public static Result currentResult = new Result();

	public static Stage currentStage;

	/**
	 * 跳跃者
	 */
	public static Jumper jumper;

	/**
	 * 弹簧床
	 */
	public static ElasticityBed bed;

	/**
	 * 道具功效
	 */
	public static final int EFFECT_GAIN = 0;// 增益
	public static final int EFFECT_HARM = 1;// 损害

	public static final int propHoldTime = 10;// 道具作用持续时间10秒

	/**
	 * 边板长宽
	 */
	// public static final float border_x = 136;
	// public static final float border_y = 17;
	public static Vec2[] borders;

	/**
	 * floors--0:跳跃失败 1：第一层 2：第二层 3：第三层
	 */
	public static int[][] floors = new int[4][1];

	public static boolean isVolumeOpen = true;
	public static boolean isMusicOpen = true;

	public static Vec2 board1 = new Vec2(106, 102);
	public static Vec2 board2 = new Vec2(136, 193);
	public static Vec2 board3 = new Vec2(161, 341);
	public static float default_w = 800f;
	public static float default_h = 480f;

	public static String img_sence_school_name = "sence_school.png";
	public static String img_sence_snow_name = "sence_snow.png";
	public static String img_sence_city_name = "sence_city.png";
	public static String img_sence_valley_name = "sence_valley.png";

	public static String img_bed_ice_name = "bed_ice_normal.png";
	public static String img_bed_ice_thumbtack_name = "bed_ice_normal_thumbtack.png";
	public static String img_bed_ice_name_l = "bed_ice_long.png";
	public static String img_bed_ice_name_s = "bed_ice_short.png";
	public static String img_bed_rainbow_name = "bed_rainbow_normal.png";
	public static String img_bed_rainbow_name_l = "bed_rainbow_long.png";
	public static String img_bed_rainbow_name_s = "bed_rainbow_short.png";
	public static String img_bed_rope_name = "bed_rope_normal.png";
	public static String img_bed_rope_thumbtack_name = "bed_rope_normal_thumbtack.png";
	public static String img_bed_rope_name_l = "bed_rope_long.png";
	public static String img_bed_rope_name_s = "bed_rope_short.png";
	public static String img_bed_board_name = "bed_board_normal.png";
	public static String img_bed_board_thumbtack_name = "bed_board_normal_thumbtack.png";
	public static String img_bed_board_name_l = "bed_board_long.png";
	public static String img_bed_board_name_s = "bed_board_short.png";
	public static String img_bed_stone_name = "bed_stone_normal.png";
	public static String img_bed_stone_name_l = "bed_stone_long.png";
	public static String img_bed_stone_name_s = "bed_stone_short.png";
	public static String img_bed_iron_name = "bed_iron_normal.png";
	public static String img_bed_iron_name_l = "bed_iron_normal_long.png";
	public static String img_bed_iron_name_s = "bed_iron_normal_short.png";

	public static String img_bed_board_break_l = "bed_board_break_long.png";
	public static String img_bed_board_break_n = "bed_board_break_normal.png";
	public static String img_bed_board_break_s = "bed_board_break_short.png";
	public static String img_bed_ice_break_l = "bed_ice_break_long.png";
	public static String img_bed_ice_break_n = "bed_ice_break_normal.png";
	public static String img_bed_ice_break_s = "bed_ice_break_short.png";
	public static String img_bed_rope_break_l = "bed_rope_break_long.png";
	public static String img_bed_rope_break_n = "bed_rope_break_normal.png";
	public static String img_bed_rope_break_s = "bed_rope_break_short.png";
	public static String img_bed_iron_break_l = "bed_iron_break_long.png";
	public static String img_bed_iron_break_n = "bed_iron_break_normal.png";
	public static String img_bed_iron_break_s = "bed_iron_break_short.png";
	public static String img_bed_stone_break_l = "bed_stone_break_long.png";
	public static String img_bed_stone_break_n = "bed_stone_break_normal.png";
	public static String img_bed_stone_break_s = "bed_stone_break_short.png";
	public static String img_bed_rainbow_break_l = "bed_rainrost_break_long.png";
	public static String img_bed_rainbow_break_n = "bed_rainrost_break_normal.png";
	public static String img_bed_rainbow_break_s = "bed_rainrost_break_short.png";

	public static String img_npc_black_name = "npc_black.png";
	public static String img_npc_stu_name = "npc_stu.png";
	public static String img_npc_andr_name = "npc_andr.png";
	public static String img_npc_default_doctor_name = "npc_doctor.png";
	public static String img_npc_luxury_doctor_name = "npc_luxury_doctor.png";
	public static String img_npc_ninga_name = "npc_ninja.png";
	public static String img_npc_white_name = "npc_white.png";
	public static String img_npc_outm_name = "npc_outm.png";
	public static String img_npc_knif_name = "npc_knife.png";
	
	public static String img_g_black_name = "g_black.png";
	public static String img_g_stu_name = "g_stu.png";
	public static String img_g_andr_name = "g_andr.png";
	public static String img_g_luxury_doctor_name = "g_lunx_doc.png";
	public static String img_g_ninga_name = "g_ninja.png";
	public static String img_g_white_name = "g_white.png";
	public static String img_g_outm_name = "g_outm.png";
	

	public static String img_npc_knif_kill_name = "npc_knife_kill.png";

	// 特效
	public static String img_effect_fire_name = "effect_fire.png";
	public static String img_effect_double_name = "effect_double.png";
	public static String img_effect_gold_name = "effect_gold.png";
	public static String img_effect_life_name = "effect_life.png";
	// public static String img_effect_slowdown_name = "effect_slow.png";
	public static String img_effect_extend_name = "effect_extend.png";
	public static String img_effect_shorten_name = "effect_short.png";
	public static String img_effect_stone_name = "effect_stone.png";
	public static String img_effect_thumbtack_name = "effect_thumbtack.png";
	
	// 商城道具
		public static String img_g_fire_name = "prop_fire.png";
		public static String img_g_double_name = "prop_double.png";
		public static String img_g_gold_name = "prop_gold.png";
		public static String img_g_life_name = "prop_life.png";
		// public static String img_effect_slowdown_name = "effect_slow.png";
		public static String img_g_extend_name = "prop_extend.png";
		public static String img_g_shorten_name = "prop_short.png";
		public static String img_g_stone_name = "prop_stone.png";
		public static String img_g_thumbtack_name = "prop_thumbtack.png";
		
		public static String img_g_skill_life_name = "skill_life.png";
		public static String img_g_skill_stop_name = "skill_clock.png";
		public static String img_g_skill_rainbow_name = "skill_bifrost.png";
		

	// 边界
	public static String img_school_board_up_name = "school_u.png";
	public static String img_school_board_mid_name = "school_m.png";
	public static String img_school_board_dow_name = "school_d.png";

	public static String img_snow_board_up_name = "snow_u.png";
	public static String img_snow_board_mid_name = "snow_m.png";
	public static String img_snow_board_dow_name = "snow_d.png";

	public static String img_city_board_up_name = "city_u.png";
	public static String img_city_board_mid_name = "city_m.png";
	public static String img_city_board_dow_name = "city_d.png";

	public static String img_valley_board_up_name = "tree_u.png";
	public static String img_valley_board_mid_name = "tree_m.png";
	public static String img_valley_board_dow_name = "tree_d.png";

	public static String[] npc_imgs;

	public static final int rainbowBed_ID = 100;// 彩虹桥蹦床的编号
	public static final int blackJumper_ID = 101;// 黑衣人的编号
	public static final int sceneSnow_ID = 11;
	public static final int sceneCity_ID = 12;
	public static final int sceneValley_ID = 102;// 山谷编号
	public static final int skillbifrost_ID = 103;// 彩虹桥技能编号

}
