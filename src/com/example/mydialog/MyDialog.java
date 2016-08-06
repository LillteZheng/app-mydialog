package com.example.mydialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 自定义退出弹出框
 * @author qinfan
 *
 * 2015-11-6
 */
public class MyDialog {
	private Activity activity;
	private AlertDialog dialog;
	private String title;

	public MyDialog(Activity activity,String title) {
		this.activity = activity;
		this.title = title;
	}

	public void showDialog(){
		dialog=new AlertDialog.Builder(activity).create();
		//点击外部区域不能取消dialog 
		dialog.setCanceledOnTouchOutside(false);
		dialog.setOnKeyListener(keylistener);
		dialog.show();

		Window window = dialog.getWindow();
		window.setContentView(R.layout.my_dialog);
		TextView tv_title = (TextView) window.findViewById(R.id.dialog_title);
		tv_title.setText(title);
		TextView tv_confirm = (TextView) window.findViewById(R.id.tv_confirm);
		TextView tv_cancel = (TextView) window.findViewById(R.id.tv_cancel);
		tv_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
				Toast.makeText(activity, "确定", Toast.LENGTH_SHORT).show();
			}
		});

		tv_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
				Toast.makeText(activity, "取消", Toast.LENGTH_SHORT).show();
			}
		});

		window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		window.setGravity(Gravity.CENTER);
	}

	public static OnKeyListener keylistener = new DialogInterface.OnKeyListener(){
		public boolean onKey(DialogInterface dialog,  int keyCode, KeyEvent event) {
			if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	} ;
}
