package com.umeng.soexample.socialize.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXWebpageObject;
import com.tencent.mm.sdk.platformtools.Util;
import com.umeng.socialize.bean.MultiStatus;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.UMShareMsg;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.MulStatusListener;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.media.UMediaObject.MediaType;
import com.umeng.socialize.view.ActionBarView;
import com.umeng.socom.Log;
import com.umeng.soexample.Constants;
import com.umeng.soexample.R;
import com.umeng.soexample.socialize.dashboard.MockDataHelper.TV;

@SuppressLint("ValidFragment")
public class DetailPageFragment extends Fragment {
	Context mContext;
	TV mTv;
	
	private  UMAuthListener mOatuthListener;
	private  SnsPostListener mSnsListener;
	private  MulStatusListener mComListener;
	UMSocialService service;

	@SuppressLint("ValidFragment")
	public DetailPageFragment(TV tv) {
		mTv = tv;
		Log.d("TestData", mTv.des);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
		initCallbackListeners();
	}
	
	private void initCallbackListeners() {
		mOatuthListener = new UMAuthListener() {
			@Override
			public void onError(SocializeException e, SHARE_MEDIA platform) {
				Toast.makeText(mContext, "ActionBarExample callback on error", 0).show();
				Log.d("TestData", "OauthCallbackListener onError");
			}
			
			@Override
			public void onComplete(Bundle value, SHARE_MEDIA platform) {
				Toast.makeText(mContext, "ActionBarExample callback  onComplete", 0).show();
				Log.d("TestData", "OauthCallbackListener onComplete");
			}

			@Override
			public void onCancel(SHARE_MEDIA arg0) {
				Toast.makeText(mContext, "ActionBarExample callback  onCancel", 0).show();
				Log.d("TestData", "OauthCallbackListener onCancel");				
			}

			@Override
			public void onStart(SHARE_MEDIA arg0) {
				Toast.makeText(mContext, "ActionBarExample callback  onStart", 0).show();
				Log.d("TestData", "OauthCallbackListener onStart");					
			}
		};
		
		mSnsListener = new SnsPostListener() {
			
			@Override
			public void onStart() {
				Toast.makeText(mContext, "ActionBarExample callback on onStart", 0).show();
			}
			
			@Override
			public void onComplete(SHARE_MEDIA platform, int eCode, SocializeEntity entity) {
				Toast.makeText(mContext, "ActionBarExample callback on onComplete", 0).show();
			}
		};
		
		mComListener = new MulStatusListener() {
			
			@Override
			public void onStart() {
				Toast.makeText(mContext, "ActionBarExample callback on onStart", 0).show();
			}
			
			@Override
			public void onComplete(MultiStatus multiStatus, int st, SocializeEntity entity) {
				Toast.makeText(mContext, "ActionBarExample callback on onComplete", 0).show();
			}
		};
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.umeng_example_socialize_actionbar_detail,
				container, false);
		TextView textView = (TextView) root.findViewById(R.id.textview);
		textView.setText(mTv.des);
		service = UMServiceFactory.getUMSocialService(mTv.name, RequestType.SOCIAL);
		//用于集成ActionBar 的ViewGroup
		ViewGroup parent = (ViewGroup) root.findViewById(R.id.root);
		//创建ActionBar des参数是ActionBar的唯�?��识，请确保不为空
		ActionBarView socializeActionBar = new ActionBarView(mContext, mTv.name);

		LayoutParams layoutParams = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.FILL_PARENT);
		socializeActionBar.setLayoutParams(layoutParams);
		//添加ActionBar
		parent.addView(socializeActionBar);
		
		service.registerListener(mComListener);
		service.registerListener(mOatuthListener);
		service.registerListener(mSnsListener);

		return root;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		service.unregisterListener(mComListener);
		service.unregisterListener(mOatuthListener);
		service.unregisterListener(mSnsListener);
	}
	
	
	static class UMWXShare{
	    private static final int THUMB_SIZE = 150;
	    private  IWXAPI mWXApi; 
	    
	    /**
	     * 
	     * @param context
	     * @param appid 微信Appid
	     */
	    public UMWXShare(Context context,String appid) {
	           mWXApi = WXAPIFactory.createWXAPI(context, appid);
	           mWXApi.registerApp(appid);
        }

        /**
	     * 
	     * @param api
	     * @param shareContent 分享的文字内�?	     * @param shareImage 分享的图片（只支持非url形式的Image�?	     * @param toCircle 是否分享到朋友圈
	     * @return
	     */
	    public boolean sendByWX(Context context,String shareContent,UMediaObject shareImage,boolean toCircle) {
	        UMShareMsg shareMsg = new UMShareMsg();//Umeng share message 用户统计
	        
	        WXWebpageObject webpage = new WXWebpageObject();
	        //为什么需要填写url? 当前Demo使用的微信SDK不支持图文分享，使用图文分享必须转成URL分享，所以需要填写一个URL
	        webpage.webpageUrl = Constants.SOCIAL_LINK; 
	        WXMediaMessage msg = new WXMediaMessage(webpage);
	        msg.title = Constants.SOCIAL_TITLE;
	        msg.description = shareContent;
	        shareMsg.mText = shareContent;

	        if (shareImage != null) {
	            
	            if(shareImage.isUrlMedia()){
	                android.util.Log.w(SocializeConstants.COMMON_TAG, "微信分享不支持非图片类型的媒体分享�?");
	            }else if(shareImage.getMediaType() != MediaType.IMAGE){
	                android.util.Log.w(SocializeConstants.COMMON_TAG, "微信分享不支持非图片类型的媒体分享�?");
	            }else{
	                byte[] b = shareImage.toByte();
	                    if (b != null) {
	                        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
	                        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
	                        bmp.recycle();
	                        msg.thumbData = Util.bmpToByteArray(thumbBmp, true); // 设置缩略�?	                        shareMsg.setMediaData(shareImage);
	                    }
	            }
	        }

	        SendMessageToWX.Req req = new SendMessageToWX.Req();
	        req.transaction = buildTransaction("webpage");
	        req.message = msg;
	        req.scene = toCircle ? SendMessageToWX.Req.WXSceneTimeline
	                            : SendMessageToWX.Req.WXSceneSession;
	        boolean sendReq = mWXApi.sendReq(req);
	        
	        //发�?分享统计
	        UMSocialService anaService = UMServiceFactory.getUMSocialService("微信分享统计", RequestType.ANALYTICS);
	        anaService.postShareByCustomPlatform(context, null, toCircle?"wxtimeline":"wxsession", shareMsg, null);
	        
	        return sendReq;
	    }
	    
	    private String buildTransaction(final String type) {
	        return (type == null)   ? String.valueOf(System.currentTimeMillis())
	                                : type + System.currentTimeMillis();
	    }
	}
}
