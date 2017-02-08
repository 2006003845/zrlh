package com.umeng.soexample.socialize.fragments;

import java.io.File;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;
import com.umeng.soexample.R;

/**
 * @功能描述 : 自定义平台Fragment, 包含添加微信和QQ平台
 * 
 * @�?�?�?:
 * @�?�?�?: [版本�? Aug 8, 2013]
 * 
 * @�?�?�?: mrsimple
 * @修改内容 :
 */
public class CustomPlatformFragment extends Fragment {

	// 整个平台的Controller, 负责管理整个SDK的配置�?操作等处�?
	private UMSocialService mController = UMServiceFactory.getUMSocialService(
			"com.umeng.share.coo", RequestType.SOCIAL);

	/**
	 * @功能描述 :
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		mController.getConfig().setSsoHandler(new TencentWBSsoHandler());
		// mController.getConfig().setSsoHandler(new
		// QZoneSsoHandler(getActivity()));
		// patent容器
		final View root = inflater.inflate(
				R.layout.umeng_example_socialize_customplatform_example,
				container, false);
		// 打开分享面板
		root.findViewById(R.id.share_by_weixin).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// 添加微信支持, 并且打开平台选择面板
						addWXPlatform();

						// mController.getConfig().removePlatform(SHARE_MEDIA.RENREN,SHARE_MEDIA.DOUBAN,
						// SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.SMS,SHARE_MEDIA.GOOGLEPLUS,SHARE_MEDIA.EMAIL);
						mController.getConfig().setPlatformOrder(
								SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
								SHARE_MEDIA.WEIXIN, SHARE_MEDIA.TENCENT);
						mController.openShare(getActivity(), false);

					}
				});

		// 添加QQ平台
		root.findViewById(R.id.share_by_qq).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// get a UMSocialService
						addQQPlatform();
						mController.openShare(getActivity(), false);
					}
				});

		return root;
	}

	/**
	 * @功能描述 :
	 */
	@Override
	public void onResume() {
		super.onResume();
	}

	/**
	 * @功能描述 : 添加微信平台分享
	 * @return
	 */
	private void addWXPlatform() {

		// wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里�?��替换成你注册的AppID
		String appId = "wx967daebe835fbeac";
		// 微信图文分享必须设置�?��url
		String contentUrl = "http://www.umeng.com/social";
		// 添加微信平台
		mController.getConfig().supportWXPlatform(getActivity(), appId,
				contentUrl);

		UMImage mUMImgBitmap = new UMImage(getActivity(),
				"http://www.umeng.com/images/pic/banner_module_social.png");
		// UMImage mUMImgBitmap = new UMImage(getActivity(),
		// BitmapFactory.decodeResource(getResources(), R.drawable.icon));
		// UMImage mUMImgBitmap = new UMImage(getActivity(),
		// BitmapFactory.decodeFile("/mnt/sdcard/test.jpg"));
		// UMImage mUMImgBitmap = new UMImage(getActivity(), new
		// File("/mnt/sdcard/test1.png"));
		mUMImgBitmap.setTitle("分享到微博");
		// target url 必须填写
		mUMImgBitmap.setTargetUrl(contentUrl);

		// 设置分享文字内容、图片内�?
		// mController.setShareContent("友盟社会化组件（SDK）让移动应用快�?整合社交分享功能，http://www.umeng.com/social");
		mController.setShareMedia(mUMImgBitmap);
		// 支持微信朋友�?
		mController.getConfig().supportWXCirclePlatform(getActivity(), appId,
				contentUrl);

	}

	/**
	 * @功能描述 : 添加QQ平台支持
	 * 
	 *       QQ分享的内容， 包含四种类型�?即单纯的文字、图片�?音乐、视�? 参数说明 : title, summary, image
	 *       url中必须至少设置一�? targetUrl必须设置,网页地址必须�?http://"�?�� . title : 要分享标�?
	 *       summary : 要分享的文字概述 image url : 图片地址 [以上三个参数至少填写�?��] targetUrl :
	 *       用户点击该分享时跳转到的目标地址 [必填] ( 若不填写则默认设置为友盟主页 )
	 * @return
	 */
	private void addQQPlatform(){
    	// 添加QQ支持, 并且设置QQ分享内容的target url
    	mController.getConfig().supportQQPlatform(getActivity(),false, "http://www.umeng.com");
//    	// 图片分享
		UMImage mImage = new UMImage(getActivity(), 
		        "http://www.umeng.com/images/pic/banner_module_social.png");
		
		// 音乐分享
		UMusic uMusic = new UMusic("http://sns.whalecloud.com/test_music.mp3");
		uMusic.setAuthor("zhangliyong");
		uMusic.setTitle("天籁之音");
//
		// 视频分享
		UMVideo umVedio = new UMVideo(
				"http://v.youku.com/v_show/id_XNTc0ODM4OTM2.html");
		umVedio.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
		umVedio.setTitle("友盟社会化组件视�?");
		
		// 要分享的文字内容
		mController.setShareContent("友盟社会化组件还不错，让移动应用快�?整合社交分享功能。www.umeng.com/social");
		// 设置多媒体内�?		mController.setShareMedia( mImage );
		
    }

	/**
	 * @功能描述 : 自定义平台排�?.分享平台会按照参数传递的顺序来排�? 如果没有指定顺序，则默认排序
	 */
	private void setPlatformOrder() {
		mController.getConfig().setPlatformOrder(SHARE_MEDIA.SINA,
				SHARE_MEDIA.QZONE, SHARE_MEDIA.QQ, SHARE_MEDIA.RENREN);
	}

	/**
	 * @功能描述 :
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

}
