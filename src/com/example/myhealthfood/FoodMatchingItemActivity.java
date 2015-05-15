package com.example.myhealthfood;

import com.example.myhealthfood.database.Food;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodMatchingItemActivity extends Activity {
	
	private Food food;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.food_matching_item);
		
		Bundle bundle = getIntent().getExtras();
		food = bundle.getParcelable("food");
		
		initView();
	}
	
	private void initView(){
		ImageView photo_imagImageView = (ImageView)findViewById(R.id.photo_food_matching_item);
		TextView name_TextView = (TextView)findViewById(R.id.name_food_matching_item);
		TextView description_TextView = (TextView)findViewById(R.id.description_food_matching_item);
		TextView cannotmatching_TextView = (TextView)findViewById(R.id.cannotmatching_food_matching_item);
		TextView cannotmatchingReasn_TextView = (TextView)findViewById(R.id.cannotmatching_reason_food_matching_item);
		
		photo_imagImageView.setImageBitmap(BitmapFactory.decodeFile(food.getPhotoPath()));
		
		if(food.getName().length() == 2){
			StringBuilder builder = new StringBuilder(food.getName());
			builder.insert(1, " ");
			name_TextView.setText(builder.toString());
		}
		
		description_TextView.setText(food.getDescription());
		cannotmatching_TextView.setText(food.getCannotMatchFood());
		cannotmatchingReasn_TextView.setText(food.getCannotMatchFoodReason());
	}
	
}
