package cn.itcast.rss.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

public class MainUtil {
	/**
	 * 1.�������ļ�����Ϣ���米��������Ϣ�����ݶ���G����
	 * 2.��ȡ��Ļ�ķֱ�����Ϣ������G����
	 * һ���ڵ�һ��������Activity��onCreate�����ĵ�һ�д�����ִ�С�
	 * @param context
	 */
	public static void initSoftConfig(Activity context){
		
	}
	
	/**
	 * initConfig��onCreate�����е��ã���Activity�������á�
	 * @param context
	 * @param layoutId
	 */
	public static void initConfig(Activity context,int layoutId){
		if(G.BG_ID!=0){
			View layout=(View) context.findViewById(layoutId);
			layout.setBackgroundResource(G.BG_ID);
		}
	}
	
	/**
	 * ��dipֵת����pxֵ
	 * @param context
	 * @param dipValue
	 */
	public static int dip2px(Context context,float dipValue){
		float density=context.getResources().getDisplayMetrics().density;
		return (int)(dipValue*density+0.5f);
	}
	
}
