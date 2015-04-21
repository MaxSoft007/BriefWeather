package org.maxsoft.utils;

import org.maxsoft.briefweather.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.provider.Settings;

public class DialogView {
	/**
	 * @function 到系统设置界面的对话框
	 * @param clazz
	 */
	public static void to_setting_dialog(final Activity clazz){
		Builder dialog = new AlertDialog.Builder(clazz);
		dialog.setTitle(R.string.title);
		dialog.setIcon(R.drawable.dialogicon);
		dialog.setMessage(R.string.tip_open_network);
		dialog.setPositiveButton(R.string.confirm, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent intent =  new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);  
					clazz.startActivity(intent);
				}
			});
		dialog.create();        
		dialog.show();
	}
	
	/**
	 * @function 退出程序的对话框
	 * @param clazz
	 */
	public static void quit_app_dialog(Activity clazz){
		AlertDialog.Builder builder = new Builder(clazz);  
        builder.setMessage(R.string.quit_tip);  
        builder.setIcon(R.drawable.dialogicon);
        builder.setTitle(R.string.title);  
        builder.setPositiveButton(R.string.confirm, 
        new android.content.DialogInterface.OnClickListener() {  
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();  
				android.os.Process.killProcess(android.os.Process.myPid());  
			}  
        });  
        builder.setNegativeButton(R.string.cancle,  
        new android.content.DialogInterface.OnClickListener() {  
			@Override
			public void onClick(DialogInterface dialog, int which){
				dialog.dismiss();  
			}  
        });  
        
        builder.create().show();  
	}
	
	/**
	 * @function 带有关于按钮的对话框
	 * @param clazz
	 */
	public static void quit_app_three_button_dialog(final Activity clazz){
		AlertDialog.Builder builder = new Builder(clazz);
		builder.setMessage(R.string.quit_tip);
		builder.setIcon(R.drawable.dialogicon);
		builder.setTitle(R.string.title);
		builder.setPositiveButton(R.string.about, new OnClickListener() {
			AlertDialog.Builder builder = new Builder(clazz);
			@Override
			public void onClick(DialogInterface dialog, int which) {
				builder.setIcon(R.drawable.dialogicon);
				builder.setTitle(R.string.about_name);
				builder.setMessage(R.string.about_info);
				builder.setPositiveButton(R.string.back, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();  
					}
				});
				
				builder.create().show();
			}
		});
		
		builder.setNeutralButton(R.string.cancle, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();  
			}
		});
		
		builder.setNegativeButton(R.string.confirm, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();  
				android.os.Process.killProcess(android.os.Process.myPid()); 
			}
		});
		builder.create().show();
	}
	
	public static void commonDisplay(final StringBuffer html,String msg) {
		html.append("<html>");
		html.append("  <body style=\"background-color: #ececec;\">");
		html.append("   <table width=\"100%\" height=\"100%\">");
		html.append("    	<tr>");
		html.append("    		<td height=\"100%\" width=\"100%\" style=\"vertical-align: middle;text-align: center\">");
		html.append("    			<font color=\"#414141\">"+msg+"</font>");
		html.append("    		</td>");
		html.append("    	</tr>");
		html.append("    </table>");
		html.append("  </body>");
		html.append("</html>");
	}
	
}
