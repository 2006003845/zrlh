package cn.zrong.activity;

import json.JSONException;
import android.support.v4.view.MyPagerTitleStrip;
import android.support.v4.view.ViewPager;
import cn.zrong.ApplicationData;
import cn.zrong.GameAPI;
import cn.zrong.activity.base.BaseActivity;
import cn.zrong.adapter.GroupGridVAdapter;
import cn.zrong.adapter.HallGridVAdapter;
import cn.zrong.adapter.HallListVAdapter;
import cn.zrong.adapter.ImageAdapter;
import cn.zrong.adapter.MoreItemAdapter;
import cn.zrong.adapter.ViewPagerAdapter;
import cn.zrong.apk.db.APKLoadDB;
import cn.zrong.connection.Community;
import cn.zrong.db.HallDB;
import cn.zrong.entity.City;
import cn.zrong.entity.GameItem;
import cn.zrong.entity.Group;
import cn.zrong.entity.Mail;
import cn.zrong.entity.MessageManager;
import cn.zrong.entity.MoreItem;
import cn.zrong.entity.Request;
import cn.zrong.entity.User;
import cn.zrong.handler.MessageHandler;
import cn.zrong.loader.AsyncDataLoader;
import cn.zrong.loader.AsyncDataLoader.Callback;
import cn.zrong.utils.Tools;
import cn.zrong.weibobinding.ShareService;
import cn.zrong.widget.GuideGallery;
import cn.zrong.widget.HallListView;

public class HomeActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

  public boolean isLaucherToOtherAPK = false;

  public static void launch(Context c, Intent intent) {
    intent.setClass(c, HomeActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    c.startActivity(intent);
  }

  public static HomeActivity mInstance;
  private Context context;

  public static final String LAG_IS_NEWCOMMER = "is_newcommer";
  private boolean isNewCommer = false;
  public MessageHandler handler;

  private static final int VIEW_HALL_INDEX = 0;
  // private static final int VIEW_CENTER_INDEX = 1;
  private static final int VIEW_GROUP_INDEX = 1;
  private static final int VIEW_MORE_INDEX = 2;
  private List<View> views = new ArrayList<View>();
  // private final int VIEWIDs[] = new int[] { R.layout.home_hall,
  // R.layout.home_center, R.layout.home_group, R.layout.home_more };
  // private final int TITLEIDs[] = new int[] { R.string.hall,
  // R.string.center,
  // R.string.group, R.string.more };

  private final int VIEWIDs[] = new int[] {R.layout.home_hall, R.layout.home_group,
      R.layout.home_more};
  private final int TITLEIDs[] = new int[] {R.string.hall, R.string.group, R.string.more};

  private List<String> titles = new ArrayList<String>();
  // private final int subSelect[] = new int[] { R.drawable.hall_selected,
  // R.drawable.center_selected, R.drawable.group_selected,
  // R.drawable.more_selected };
  // private final int sub[] = new int[] { R.drawable.hall_unselected,
  // R.drawable.center_unselected, R.drawable.group_unselected,
  // R.drawable.more_unselected };

  private final int subSelect[] = new int[] {R.drawable.hall_selected, R.drawable.group_selected,
      R.drawable.more_selected};
  private final int sub[] = new int[] {R.drawable.hall_unselected, R.drawable.group_unselected,
      R.drawable.more_unselected};

  private ImageView[] pageSelect = null;

  private View view;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mInstance = this;
    context = this;
    inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
      isNewCommer = bundle.getBoolean(LAG_IS_NEWCOMMER);
    }
    handler = MessageHandler.sharedHandler();
    Display display = getWindowManager().getDefaultDisplay();

    GameAPI.screen_width = display.getWidth();
    GameAPI.screen_height = display.getHeight();

    // 启动服务器
    startService(new Intent(context, ShareService.class));

    Looper looper = Looper.myLooper();
    new MessageHandler(looper, this);

    new AsyncDataLoader(getSystemDataCallback).execute();
    // Config.checkUpdate(this, this.getApplication().getPackageName(),
    // "http://112.25.14.51/PassSystem/upDate/", "gagaSever.json");
    view = getView(R.layout.home);
    setContentView(view);
    initView();

    // if (isNewCommer) {
    showWelcomeDialog();
    // }
  }

  private ViewPager mViewPager;
  private MyPagerTitleStrip mPagerTitleStrip;

  private void initView() {
    mViewPager = (ViewPager) this.findViewById(R.id.home_viewpager);
    mPagerTitleStrip = (MyPagerTitleStrip) this.findViewById(R.id.home_pagertitle);

    for (int i = 0, len = 2; i < len; i++) {
      views.add(getView(VIEWIDs[i]));
    }
    for (int i = 0, len = 2; i < len; i++) {
      titles.add(this.getResources().getString(TITLEIDs[i]));
    }

    ViewPagerAdapter adapter = new ViewPagerAdapter(this, views, titles);
    mViewPager.setAdapter(adapter);
    mViewPager.setOnPageChangeListener(this);
    mViewPager.setCurrentItem(VIEW_HALL_INDEX);
    initChildView(VIEW_HALL_INDEX);

    LinearLayout buttomLayout = (LinearLayout) findViewById(R.id.home_buttomlinearlayout);
    pageSelect = new ImageView[VIEWIDs.length];
    for (int i = 0; i < 2; i++) {
      pageSelect[i] = new ImageView(this);
      pageSelect[i].setBackgroundResource(sub[i]);
      buttomLayout.addView(pageSelect[i]);
      android.widget.LinearLayout.LayoutParams param =
          (android.widget.LinearLayout.LayoutParams) pageSelect[i].getLayoutParams();
      param.leftMargin = 10;
      param.rightMargin = 10;
    }
    pageSelect[0].setBackgroundResource(subSelect[0]);
    groupCreateLayout = (LinearLayout) this.findViewById(R.id.home_group_create_layout);
    groupSearchLayout = (LinearLayout) this.findViewById(R.id.home_group_search_layout);
    groupCreateLayout.setVisibility(View.INVISIBLE);
    groupSearchLayout.setVisibility(View.INVISIBLE);
    this.findViewById(R.id.btn_home_create).setOnClickListener(groupToolsClickListener);
    this.findViewById(R.id.btn_home_search).setOnClickListener(groupToolsClickListener);
    this.findViewById(R.id.btn_home_msg).setOnClickListener(groupToolsClickListener);
    this.findViewById(R.id.btn_home_friends).setOnClickListener(groupToolsClickListener);
    this.findViewById(R.id.btn_home_card).setOnClickListener(groupToolsClickListener);
    this.findViewById(R.id.btn_home_wbbinding).setOnClickListener(groupToolsClickListener);
    this.findViewById(R.id.btn_home_accountmanage).setOnClickListener(groupToolsClickListener);
    this.findViewById(R.id.btn_home_feedback).setOnClickListener(groupToolsClickListener);
  }

  private LinearLayout groupCreateLayout, groupSearchLayout;

  private void initChildView(int index) {
    switch (index) {
      case VIEW_HALL_INDEX:
        initHallView(VIEW_HALL_INDEX);
        break;
      // case VIEW_CENTER_INDEX:
      // initGameCenterView(VIEW_CENTER_INDEX);
      // break;
      case VIEW_GROUP_INDEX:
        initGroupView(VIEW_GROUP_INDEX);
        break;
      case VIEW_MORE_INDEX:
        initMoreView(VIEW_MORE_INDEX);
        break;

    }
  }

  private HallListView hallListV;
  private GridView hallGridV;
  private HallGridVAdapter hallGridVAdapter;
  private HallListVAdapter hallListVAdapter;
  private List<GameItem> hall_gameItemList = new ArrayList<GameItem>();

  private void initHallView(int viewId) {
    if (groupCreateLayout != null) {
      groupCreateLayout.setVisibility(View.INVISIBLE);
      groupSearchLayout.setVisibility(View.INVISIBLE);
    }
    if (hallGridV != null) {
      return;
    }
    // TODO
    // hallListV = (HallListView) views.get(viewId).findViewById(
    // R.id.hall_listv);
    hallGridV = (GridView) views.get(viewId).findViewById(R.id.hall_gridv);
    // View view = getView(R.layout.listv_foot_view);
    // hallListV.addFooterView(view);
    // hallListV.postInvalidate();
    // view.setOnClickListener(new OnClickListener() {
    //
    // @Override
    // public void onClick(View v) {
    // Toast.makeText(context, "LISTVIEW_FOOT_VIEW",
    // Toast.LENGTH_SHORT).show();
    // }
    // });

    // hallListVAdapter = new HallListVAdapter(hall_gameItemList, context);
    hallGridVAdapter = new HallGridVAdapter(hall_gameItemList, context);
    hallGridV.setAdapter(hallGridVAdapter);
    // hallListV.setAdapter(hallListVAdapter);
    new AsyncDataLoader(loadHallGamesCallback).execute();
  }

  private Callback loadHallGamesCallback = new Callback() {
    ProgressDialog dialog;

    @Override
    public void onStart() {
      Community comm = Community.getInstance(context);
      if (comm != null) {
        try {
          List<GameItem> list = comm.getHallGames();
          if (list != null) {
            hall_gameItemList.clear();
            hall_gameItemList.addAll(list);
            hall_gameItemList.add(new GameItem());
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    }

    @Override
    public void onPrepare() {
      dialog = new ProgressDialog(context, android.R.style.Widget_ProgressBar_Small);
      dialog.setCancelable(true);
      dialog.setMessage("加载...");
      dialog.show();
    }

    @Override
    public void onFinish() {
      setProgressBarIndeterminateVisibility(false);
      dialog.dismiss();
      hallGridVAdapter.notifyDataSetChanged();
    }
  };

  private RelativeLayout game_search, game_hotrecommed, game_gameranked, game_class;
  private ImageView hotRecommed00, hotRecommed01, hotRecommed02, hotRecommed03;

  private void initGameCenterView(int viewId) {
    if (game_search != null) {
      return;
    }
    game_search = (RelativeLayout) views.get(viewId).findViewById(R.id.gamecenter_search);
    game_search.setOnTouchListener(new OnTouchListener() {

      @Override
      public boolean onTouch(View v, MotionEvent event) {
        int e = event.getAction();
        switch (e) {
          case MotionEvent.ACTION_DOWN:
            game_search.setBackgroundColor(0xff6ad23c);
            break;
          case MotionEvent.ACTION_OUTSIDE:
            game_search.setBackgroundColor(0xff45b513);
            break;
          case MotionEvent.ACTION_UP:
            game_search.setBackgroundColor(0xff45b513);
            break;
        }
        return false;
      }
    });
    game_search.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO

      }
    });
    game_hotrecommed = (RelativeLayout) views.get(viewId).findViewById(R.id.gamecenter_hot);
    game_hotrecommed.setOnTouchListener(new OnTouchListener() {

      @Override
      public boolean onTouch(View v, MotionEvent event) {
        int e = event.getAction();
        switch (e) {
          case MotionEvent.ACTION_DOWN:
            game_hotrecommed.setBackgroundColor(0xff6ad23c);
            break;
          case MotionEvent.ACTION_OUTSIDE:
            game_hotrecommed.setBackgroundColor(0xff45b513);
            break;
          case MotionEvent.ACTION_UP:
            game_hotrecommed.setBackgroundColor(0xff45b513);
            break;
        }
        return false;
      }
    });
    game_hotrecommed.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO
      }
    });
    game_gameranked = (RelativeLayout) views.get(viewId).findViewById(R.id.gamecenter_ranked);
    game_gameranked.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO
      }
    });
    game_gameranked.setOnTouchListener(new OnTouchListener() {

      @Override
      public boolean onTouch(View v, MotionEvent event) {
        int e = event.getAction();
        switch (e) {
          case MotionEvent.ACTION_DOWN:
            game_gameranked.setBackgroundColor(0xff6ad23c);
            break;
          case MotionEvent.ACTION_OUTSIDE:
            game_gameranked.setBackgroundColor(0xff45b513);
            break;
          case MotionEvent.ACTION_UP:
            game_gameranked.setBackgroundColor(0xff45b513);
            break;
        }
        return false;
      }
    });
    game_class = (RelativeLayout) views.get(viewId).findViewById(R.id.gamecenter_class);
    game_class.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO
      }
    });
    game_class.setOnTouchListener(new OnTouchListener() {

      @Override
      public boolean onTouch(View v, MotionEvent event) {
        int e = event.getAction();
        switch (e) {
          case MotionEvent.ACTION_DOWN:
            game_class.setBackgroundColor(0xff6ad23c);
            break;
          case MotionEvent.ACTION_OUTSIDE:
            game_class.setBackgroundColor(0xff45b513);
            break;
          case MotionEvent.ACTION_UP:
            game_class.setBackgroundColor(0xff45b513);
            break;
        }
        return false;
      }
    });

    views.get(viewId).findViewById(R.id.gamecenter_recommendgame00)
        .setOnClickListener(new OnClickListener() {

          @Override
          public void onClick(View v) {
            // TODO
          }
        });
    views.get(viewId).findViewById(R.id.gamecenter_recommendgame01)
        .setOnClickListener(new OnClickListener() {

          @Override
          public void onClick(View v) {
            // TODO
          }
        });
    views.get(viewId).findViewById(R.id.gamecenter_recommendgame02)
        .setOnClickListener(new OnClickListener() {

          @Override
          public void onClick(View v) {
            // TODO
          }
        });
    views.get(viewId).findViewById(R.id.gamecenter_recommendgame03)
        .setOnClickListener(new OnClickListener() {

          @Override
          public void onClick(View v) {
            // TODO
          }
        });

  }

  private GridView groupGridV;
  private GroupGridVAdapter groupGridVAdapter;
  public List<Group> groupList = new ArrayList<Group>();

  private void initGroupView(int viewId) {
    groupCreateLayout.setVisibility(View.VISIBLE);
    groupSearchLayout.setVisibility(View.VISIBLE);
    if (groupGridV != null) {
      return;
    }
    // TODO
    View view = views.get(viewId);
    groupGridV = (GridView) view.findViewById(R.id.group_gridv);
    groupGridV.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Group g = groupList.get(position);
        g.haveJoin = 2;
        if (!groupGridVAdapter.isEditState()) {
          Intent intent = new Intent();
          Bundle b = new Bundle();
          b.putSerializable("group", g);
          intent.putExtras(b);
          GroupActivity.launch(context, intent);
        }
      }
    });

    groupGridV.setOnItemLongClickListener(new OnItemLongClickListener() {

      @Override
      public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        groupGridVAdapter.setEditState(true);
        groupGridVAdapter.notifyDataSetChanged();
        return false;
      }
    });

    groupGridVAdapter = new GroupGridVAdapter(context, groupList);
    groupGridV.setAdapter(groupGridVAdapter);
    updateMyGroups();
    view.findViewById(R.id.home_group_layout).setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        if (groupGridVAdapter.isEditState()) {
          groupGridVAdapter.setEditState(false);
          groupGridVAdapter.notifyDataSetChanged();
        }
      }
    });

  }

  private OnClickListener groupToolsClickListener = new OnClickListener() {

    @Override
    public void onClick(View v) {
      int id = v.getId();
      switch (id) {
        case R.id.btn_home_create:
          GroupCreateActivity.launch(context, null);
          break;
        case R.id.btn_home_search:
          GroupSearchActivity.launch(context, null);
          break;
        case R.id.btn_home_msg:
          MessageListActivity.launch(context);
          break;
        case R.id.btn_home_friends:
          FriendsActivity.launch(context, null);
          break;
        case R.id.btn_home_card:
          Intent intent = new Intent();
          Bundle b = new Bundle();
          b.putInt(CardActivity.WHERE_COME_FROM, CardActivity.COMEFROM_SELF);
          intent.putExtras(b);
          CardActivity.launch(context, intent);
          break;
        case R.id.btn_home_wbbinding:
          // WeiboBandingActivity.launch(context);
          break;
        case R.id.btn_home_accountmanage:
          AccountManagerActivity.launch(context);
          break;
        case R.id.btn_home_feedback:
          FeedbackActivity.launch(context);
          break;
      }
    }
  };

  public void updateMyGroups() {
    new AsyncDataLoader(loadSelfGroupCallback).execute();
  }

  private Callback loadSelfGroupCallback = new Callback() {
    ProgressDialog dialog;

    @Override
    public void onStart() {
      try {
        User u = ApplicationData.currentUser;
        Log.i("Log", u.getUserInfo().nickName);
        Community comm = Community.getInstance(context);
        if (comm != null) {
          List<Group> list = comm.getGroupList("", Community.TYPE_GROUP_KEY_SELF);
          if (list != null) {
            groupList.clear();
            groupList.addAll(list);
          }
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void onPrepare() {
      dialog = new ProgressDialog(context, android.R.style.Widget_ProgressBar_Small);
      dialog.setCancelable(true);
      dialog.setMessage("加载...");
      dialog.show();
    }

    @Override
    public void onFinish() {
      setProgressBarIndeterminateVisibility(false);
      dialog.dismiss();
      groupGridVAdapter.notifyDataSetChanged();
    }
  };

  private int selectedColor = 0xff14dbb8;
  private int normalColor = 0xff49e8cc;

  private GridView moreGridV;

  private void initMoreView(int viewId) {
    // TODO
    moreGridV = (GridView) views.get(viewId).findViewById(R.id.more_gridv);
    moreGridV.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
          case 0:
            // WeiboBandingActivity.launch(context);
            break;
          case 1:
            AccountManagerActivity.launch(context);
            break;
          case 2:
            FeedbackActivity.launch(context);
            break;
          case 3:
            AboutUsActivity.launch(context);
            break;

        }
      }
    });
    List<MoreItem> itemList = new ArrayList<MoreItem>();
    itemList.add(new MoreItem(R.drawable.img_weibobanding, R.string.binding_weibo));
    itemList.add(new MoreItem(R.drawable.img_accountmanage, R.string.account_manage));
    itemList.add(new MoreItem(R.drawable.img_feedback, R.string.opinion_feedback));
    // itemList.add(new MoreItem(R.drawable.img_weibobanding,
    // R.string.binding_weibo));
    MoreItemAdapter adapter = new MoreItemAdapter(context, itemList);
    moreGridV.setAdapter(adapter);
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if ((keyCode == KeyEvent.KEYCODE_BACK) && isLaucherToOtherAPK) {
      this.setContentView(view);
      isLaucherToOtherAPK = false;
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }

  @Override
  public BaseActivity getGameContext() {
    return this;
  }

  private LayoutInflater inflater;

  private View getView(int layout) {
    View view = inflater.inflate(layout, null);
    return view;
  }

  @Override
  public void onPageScrollStateChanged(int arg0) {}

  @Override
  public void onPageScrolled(int arg0, float arg1, int arg2) {}

  @Override
  public void onPageSelected(int arg0) {
    initChildView(arg0);
    for (int i = 0; i < (pageSelect.length - 1); i++) {
      pageSelect[i].setBackgroundResource(sub[i]);
    }
    pageSelect[arg0].setBackgroundResource(subSelect[arg0]);
  }

  private Callback getSystemDataCallback = new Callback() {

    @Override
    public void onStart() {
      Community comm = Community.getInstance(context);
      if (comm != null) {
        try {
          List<City> list = comm.getCityList();
          if (list != null) {
            ApplicationData.cityList.clear();
            ApplicationData.cityList.addAll(list);
          }
          List<Mail> list2 = comm.getMailList();
          if (list2 != null) {
            ApplicationData.mailList.clear();
            for (Mail mail : list2) {
              if (mail.type == MessageManager.TYPE_MESSAGE_MAIL) {
                ApplicationData.mailList.add(mail);
              }
            }
          }

          List<Request> list3 = comm.getRequestListOfFriend();
          if (list3 != null) {
            ApplicationData.requestList.clear();
            for (Request req : list3) {
              if (req.type == MessageManager.TYPE_MESSAGE_REQUEST) {
                ApplicationData.requestList.add(req);
              }
            }
          }

          List<Request> list4 = comm.getRequestListOfGroup();
          if (list4 != null) {
            ApplicationData.requestList.clear();
            for (Request req : list4) {
              if (req.type == MessageManager.TYPE_MESSAGE_REQUEST) {
                ApplicationData.requestList.add(req);
              }
            }
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    }

    @Override
    public void onPrepare() {}

    @Override
    public void onFinish() {}
  };

  int count = 0;

  long startTime;
  long nextTime;

  @Override
  public void onBackPressed() {
    if (count == 0) {
      startTime = System.currentTimeMillis();
      Tools.showToast(context, "再按一次退出" + getResources().getString(R.string.app_name));
      count++;
      return;
    } else {
      nextTime = System.currentTimeMillis();
      if ((nextTime - startTime) <= 1500) {
        super.onBackPressed();
      } else {
        Tools.showToast(context, "再按一次退出" + getResources().getString(R.string.app_name));
        startTime = nextTime;
      }
    }

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mInstance = null;
    HallDB.getHallDBInstance(context).closeDB();
    APKLoadDB.getHallDBInstance(mInstance).closeDB();
    ApplicationData.clear();
  }

  private void showWelcomeDialog() {
    LayoutInflater inflater = getLayoutInflater();
    final View layout = inflater.inflate(R.layout.guide_view, null);
    final AlertDialog dialog = new AlertDialog.Builder(context).create();
    dialog.setCanceledOnTouchOutside(true);
    dialog.show();
    dialog.getWindow().setLayout(getWindowManager().getDefaultDisplay().getWidth(),
        getWindowManager().getDefaultDisplay().getHeight());
    dialog.getWindow().setContentView(layout);
    dialog.setCanceledOnTouchOutside(true);

    GuideGallery gallery = (GuideGallery) dialog.findViewById(R.id.guide_view_gallery);

    final ImageAdapter imageAdapter = new ImageAdapter(this);
    final ImageView start = (ImageView) dialog.findViewById(R.id.guide_view_start);

    start.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        dialog.dismiss();
      }
    });
    gallery.setAdapter(imageAdapter);
    gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == (imageAdapter.getCount() - 1)) {
          start.setVisibility(View.VISIBLE);
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

      }
    });
  }
}
