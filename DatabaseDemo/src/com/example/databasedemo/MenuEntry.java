package com.example.databasedemo;

public class MenuEntry {
	private Integer mDrawable;
	private String mText;
	private Integer mTextId;
	private MenuListener mListener;

	public Integer getDrawable() {
		return mDrawable;
	}

	public void setDrawable(Integer drawable) {
		mDrawable = drawable;
	}

	public Integer getTextId() {
		return mTextId;
	}

	public void setTextId(Integer textId) {
		mTextId = textId;
	}

	public void setListener(MenuListener listener) {
		this.mListener = listener;
	}

	public MenuListener getListener() {
		return mListener;
	}

	public void setText(String mText) {
		this.mText = mText;
	}

	public String getText() {
		return mText;
	}

	public MenuEntry(Integer drawable, Integer textId) {
		mDrawable = drawable;
		mTextId = textId;
	}

	public MenuEntry(Integer drawable, String text) {
		mDrawable = drawable;
		mText = text;
	}

	public MenuEntry(Integer drawable, Integer text, MenuListener listener) {
		mDrawable = drawable;
		mTextId = text;
		mListener = listener;
	}

	public MenuEntry(Integer text, MenuListener listener) {
		mTextId = text;
		mListener = listener;
	}
}
