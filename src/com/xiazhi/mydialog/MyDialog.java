package com.xiazhi.mydialog;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MyDialog {
	private Context mContext;
	private WindowManager manager;
	private WindowManager.LayoutParams params;
	private static RelativeLayout contentView;
	private static PopupWindow mPopupWindow;

	public MyDialog(Context context) {
		this.mContext = context;
	}

	public void showDialog() {
		// 获取系统manager的window_service 服务
		manager = (WindowManager) mContext.getApplicationContext()
				.getSystemService(Context.WINDOW_SERVICE);
		params = new WindowManager.LayoutParams();
		// 设置 type的属性，以ALERT为属性
		params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
		params.format = PixelFormat.RGBA_8888; // 设置透明背景
		// 让dialog外部的区域不能被点击,back键也不起作用
		params.flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
				//WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
				//| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
		
		params.width = LayoutParams.MATCH_PARENT;
		params.height = LayoutParams.MATCH_PARENT;
		View layoutView = getPopupWindowView();
		manager.addView(layoutView, params);

	}

	private View getPopupWindowView() {
		// 加载dialog布局
		contentView = (RelativeLayout) LayoutInflater.from(mContext).inflate(
				R.layout.mydialog_ly, null);
		// 装载到popupwindow中
		mPopupWindow = new PopupWindow(contentView, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		mPopupWindow.setContentView(contentView);
		mPopupWindow.setFocusable(true);
		
		contentView.findViewById(R.id.dialog_sure).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						showToast("确定退出");
						if (contentView != null) {
							mPopupWindow.dismiss();
							manager.removeView(contentView);
							
						}

					}
				});

		contentView.findViewById(R.id.dialog_cancel).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						showToast("取消退出");
						if (contentView != null) {
							mPopupWindow.dismiss();
							manager.removeView(contentView);
						}

					}
				});
		return contentView;
	}

	public void dimissDialog() {
		Log.d("zsr", "null?: " + mPopupWindow);
		if (contentView != null) {
			mPopupWindow.dismiss();
			manager.removeView(contentView);
		}
	}

	private void showToast(String text) {
		Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
	}

}
