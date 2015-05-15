package com.example.myhealthfood.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.Button;

public class FZSongFontButton extends Button {
	
	public FZSongFontButton(Context context) {
		super(context);
		init(context);
	}
	
	public FZSongFontButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public FZSongFontButton(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	private void init(Context context){
		AssetManager assetManager = context.getAssets();
		Typeface font = Typeface.createFromAsset(assetManager, "fonts/FZSong III-Z05S.ttf");
		setTypeface(font);
		setTextSize(30);
		TextPaint textPaint = this.getPaint();
		textPaint.setFakeBoldText(true);
	}

}
