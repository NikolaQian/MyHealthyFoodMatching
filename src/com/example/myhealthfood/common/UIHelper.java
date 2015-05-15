package com.example.myhealthfood.common;

import android.content.Context;
import android.widget.Toast;

public class UIHelper {
	public static void ToastMessage(Context context, String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static void ToastMessage(Context context, int msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
