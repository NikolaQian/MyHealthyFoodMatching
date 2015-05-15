package com.example.myhealthfood.database;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable{
	private int _id;
	private String name;
	private String description;
	private String cannotMatchFood;
	private String cannotMatchFoodReason;
	private String photoPath;
	
	public Food(){
	}
	
	public Food(int _id, String name, String description, String cannotMatchFood,
						String cannotMatchFoodReason, String photoPath){
		this._id = _id;
		this.name = name;
		this.description = description;
		this.cannotMatchFood = cannotMatchFood;
		this.cannotMatchFoodReason = cannotMatchFoodReason;
		this.photoPath = photoPath;
	}
	
	public Food(Parcel p){
		this._id = p.readInt();
		this.name = p.readString();
		this.description = p.readString();
		this.cannotMatchFood = p.readString();
		this.cannotMatchFoodReason = p.readString();
		this.photoPath = p.readString();
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCannotMatchFood() {
		return cannotMatchFood;
	}

	public void setCannotMatchFood(String cannotMatchFood) {
		this.cannotMatchFood = cannotMatchFood;
	}

	public String getCannotMatchFoodReason() {
		return cannotMatchFoodReason;
	}

	public void setCannotMatchFoodReason(String cannotMatchFoodReason) {
		this.cannotMatchFoodReason = cannotMatchFoodReason;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel p, int arg1) {
		p.writeInt(_id);
		p.writeString(name);
		p.writeString(description);
		p.writeString(cannotMatchFood);
		p.writeString(cannotMatchFoodReason);
		p.writeString(photoPath);
	}
	
	public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {

		@Override
		public Food createFromParcel(Parcel p) {
			return new Food(p);
		}

		@Override
		public Food[] newArray(int arg0) {
			return new Food[arg0];
		}
	};
}
