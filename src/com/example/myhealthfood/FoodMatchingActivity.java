package com.example.myhealthfood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.myhealthfood.database.DataBaseManager;
import com.example.myhealthfood.database.Food;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

public class FoodMatchingActivity extends Activity {
	
	private EditText food_search_edit;
	
	private ListView foodListView;
	
	private List<Food> foodList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		setContentView(R.layout.food_matching);
		
		initView();
		
		displayAllFood();
		
		foodListView.setOnItemClickListener(foodListView_item_ls);
	}
	
	private void initView(){
		food_search_edit = (EditText)findViewById(R.id.food_search_edittext);
		ImageButton food_search_btn = (ImageButton)findViewById(R.id.food_search_btn);
		ImageButton food_refresh_btn = (ImageButton)findViewById(R.id.food_refresh_btn);
		
		food_search_btn.setOnClickListener(btn_ls);
		food_refresh_btn.setOnClickListener(btn_ls);
		
		foodListView = (ListView)findViewById(R.id.food_listview);
		
	}
	
	private OnClickListener btn_ls = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(food_search_edit.getWindowToken(), 0);
			
			switch (v.getId()) {
			case R.id.food_search_btn:{
				searchFood();
			}
				break;
			case R.id.food_refresh_btn:{
				displayAllFood();
			}
				break;
			}
			
		}
	};
	
	private void searchFood(){
		String foodName = food_search_edit.getText().toString();
		
		DataBaseManager dataBaseManager = new DataBaseManager(this);
		List<Food> foodSearchList = dataBaseManager.query(foodName);
		dataBaseManager.close();
		
		if(foodList.size() == 0){
			return;
		}
		
		foodList.clear();
		foodList = foodSearchList;
		
		displayFoodListView();
	}
	
	private void displayAllFood(){
		DataBaseManager dataBaseManager = new DataBaseManager(this);
		foodList = dataBaseManager.queryAll();
		dataBaseManager.close();
		
		displayFoodListView();
	}
	
	private void displayFoodListView(){
		List<Map<String, Object>> lst = new ArrayList<Map<String,Object>>();
		
		for(Food food:foodList){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", food.getName());
			map.put("cannotMatchFood", food.getCannotMatchFood());
			map.put("photo", BitmapFactory.decodeFile(food.getPhotoPath()));
			lst.add(map);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(this, lst, R.layout.food_matching_listview_item, 
															new String[]{"name", "cannotMatchFood", "photo"},
															new int[]{R.id.name_listview_item, R.id.cannotMatchFood_listview_item, R.id.photo_listview_item});
		
		adapter.setViewBinder(new ViewBinder() {  
			
            public boolean setViewValue(View view, Object data,  
                    String textRepresentation) {  
            	
            	if(view instanceof ImageView  && data instanceof Bitmap){  
                    ImageView iv = (ImageView) view;  
                  
                    iv.setImageBitmap((Bitmap) data);  
                    return true;  
                }else  
                return false;  
            }  
        });
		
		foodListView.setAdapter(adapter);
		
	}
	
	private OnItemClickListener foodListView_item_ls = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent = new Intent();
			intent.setClass(FoodMatchingActivity.this, FoodMatchingItemActivity.class);
			
			Food food = foodList.get(arg2);
			
			Bundle bundle = new Bundle();
			bundle.putParcelable("food", food);
			intent.putExtras(bundle);
			
			startActivity(intent);	
		}
		
	}; 
	
}
