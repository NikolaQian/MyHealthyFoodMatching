package com.example.myhealthfood;

import com.example.myhealthfood.common.UIHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainMenuActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.main_menu);

		initView();
	}
	
	private void initView(){
		Button foodMatchBtn = (Button)findViewById(R.id.food_macth_btn);
		Button foodNutritionBtn = (Button)findViewById(R.id.food_nutrition_btn);
		Button aboutBtn = (Button)findViewById(R.id.about_btn);
		
		foodMatchBtn.setOnClickListener(btn_ls);
		foodNutritionBtn.setOnClickListener(btn_ls);
		aboutBtn.setOnClickListener(btn_ls);
	}

	private OnClickListener btn_ls = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.food_macth_btn:{
				Intent intent = new Intent();
				intent.setClass(MainMenuActivity.this, FoodMatchingActivity.class);
				startActivity(intent);
			}
				break;
			case R.id.food_nutrition_btn:{
				Intent intent = new Intent();
				intent.setClass(MainMenuActivity.this, FoodNutritionActivity.class);
				startActivity(intent);
			}
				break;
			case R.id.about_btn:{
				Intent intent = new Intent();
				intent.setClass(MainMenuActivity.this, AboutActivity.class);
				startActivity(intent);
			}
				break;
			}
		}
	};
}
