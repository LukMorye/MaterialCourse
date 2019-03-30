package ru.gb.vtitov.materialcourse.models;

import android.graphics.Bitmap;

import java.util.Date;

public class PositionModel {

	private String title;
	private Bitmap image;
	private Date date;

	public PositionModel(String title, Bitmap image, Date date) {
		this.title = title;
		this.image = image;
		this.date = date;
	}


	public String getTitle() {
		return title;
	}

	public Bitmap getImage() {
		return image;
	}

	public String getDate() {
		return date.toString();
	}
}
