package com.example.myhealthfood.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class FZLanTingHeiSTextView extends TextView {

	public FZLanTingHeiSTextView(Context context) {
		super(context);
		init(context);
	}
	
	public FZLanTingHeiSTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public FZLanTingHeiSTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	private void init(Context context){
		AssetManager assetManager = context.getAssets();
		Typeface font = Typeface.createFromAsset(assetManager, "fonts/FZLanTingHeiS-R-GB.TTF");
		setTypeface(font);
	}
	
//	public void setTypeface(Typeface tf, int style){
//		if(style == Typeface.BOLD){
//			super.setTypeface(tf);
//		}else {
//			super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/FZLanTingHeiS-R-GB.TTF"));
//		}
//	}
}
