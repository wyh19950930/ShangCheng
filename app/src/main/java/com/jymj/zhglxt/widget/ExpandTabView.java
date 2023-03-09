package com.jymj.zhglxt.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;


import com.jymj.zhglxt.R;

import java.util.ArrayList;

public class ExpandTabView extends LinearLayout implements OnDismissListener {

	private ToggleButton selectedButton;
	private ArrayList<String> mTextArray = new ArrayList<String>();
	private ArrayList<RelativeLayout> mViewArray = new ArrayList<RelativeLayout>();
	private ArrayList<ToggleButton> mToggleButton = new ArrayList<ToggleButton>();
	private Context mContext;
	private final int SMALL = 0;
	private int displayWidth;
	private int displayHeight;
	private PopupWindow popupWindow;
	private int selectPosition;
	private int tabNum = 0;

	public ExpandTabView(Context context) {
		super(context);
		init(context);
	}

	public ExpandTabView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void setTitle(String valueText, int position) {
		if (position < mToggleButton.size()) {
			mToggleButton.get(position).setText(valueText);
		}
	}

	public void addView(String text, View view){
		final RelativeLayout r = new RelativeLayout(mContext);
		int maxHeight = (int) (displayHeight * 0.5);
		RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, maxHeight);
		rl.leftMargin = 10;
		rl.rightMargin = 10;

		removeParent(view);
		r.addView(view, rl);
		mViewArray.add(r);
		r.setTag(SMALL);
		ToggleButton tButton = new ExToggleButtton(getContext());
		addView(tButton);
		View line = new TextView(mContext);
		line.setBackgroundResource(R.drawable.expand_choosebar_line);
		LayoutParams lp = new LayoutParams(2, LayoutParams.FILL_PARENT);
		addView(line, lp);
		mToggleButton.add(tButton);
		tButton.setText(text);
		tButton.setTextColor(mContext.getResources().getColor(R.color.white));
		tButton.setTag(tabNum++);

		r.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onPressBack();
			}
		});

		r.setBackgroundColor(mContext.getResources().getColor(R.color.expand_popup_background));
		tButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // initPopupWindow();
                ToggleButton tButton = (ToggleButton) view;

                if (selectedButton != null && selectedButton != tButton) {
                    selectedButton.setChecked(false);
                }
                selectedButton = tButton;
                selectPosition = (Integer) selectedButton.getTag();
                startAnimation();
                if (mOnButtonClickListener != null && tButton.isChecked()) {
                    mOnButtonClickListener.onClick(selectPosition);
                }
            }
        });

	}

//	public void setValue(ArrayList<String> textArray, ArrayList<View> viewArray) {
//		if (mContext == null) {
//			return;
//		}
//		mTextArray = textArray;
//		for (int i = 0; i < viewArray.size(); i++) {
//			final RelativeLayout r = new RelativeLayout(mContext);
//			int maxHeight = (int) (displayHeight * 0.5);
//			RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, maxHeight);
//			rl.leftMargin = 10;
//			rl.rightMargin = 10;
//			removeParent(viewArray.get(i));
//			r.addView(viewArray.get(i), rl);
//			mViewArray.add(r);
//			r.setTag(SMALL);
//			ToggleButton tButton = new ExToggleButtton(getContext());
//			addView(tButton);
//			View line = new TextView(mContext);
//			line.setBackgroundResource(R.drawable.expand_choosebar_line);
//			if (i < viewArray.size() - 1) {
//				LayoutParams lp = new LayoutParams(2, LayoutParams.FILL_PARENT);
//				addView(line, lp);
//			}
//			mToggleButton.add(tButton);
//			tButton.setTag(i);
//			tButton.setText(mTextArray.get(i));
//
//			r.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					onPressBack();
//				}
//			});
//
//			r.setBackgroundColor(mContext.getResources().getColor(R.color.expand_popup_background));
//			tButton.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View view) {
//					// initPopupWindow();
//					ToggleButton tButton = (ToggleButton) view;
//
//					if (selectedButton != null && selectedButton != tButton) {
//						selectedButton.setChecked(false);
//					}
//					selectedButton = tButton;
//					selectPosition = (Integer) selectedButton.getTag();
//					startAnimation();
//					if (mOnButtonClickListener != null && tButton.isChecked()) {
//						mOnButtonClickListener.onClick(selectPosition);
//					}
//				}
//			});
//		}
//	}

	private void removeParent(View view) {
		// TODO Auto-generated method stub
			ViewGroup p = (ViewGroup) view.getParent();
			if (p != null) {
				p.removeAllViewsInLayout();
			}
	}

	private void startAnimation() {

		if (popupWindow == null) {
			popupWindow = new PopupWindow(mViewArray.get(selectPosition), displayWidth, displayHeight);
			popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
			popupWindow.setFocusable(false);
			popupWindow.setOutsideTouchable(true);
		}

		if (selectedButton.isChecked()) {
			if (!popupWindow.isShowing()) {
				showPopup(selectPosition);
			} else {
				popupWindow.setOnDismissListener(this);
				popupWindow.dismiss();
				hideView();
			}
		} else {
			if (popupWindow.isShowing()) {
				popupWindow.dismiss();
				hideView();
			}
		}
	}

	private void showPopup(int position) {
		View tView = mViewArray.get(selectPosition).getChildAt(0);
		if (tView instanceof ViewBaseAction) {
			ViewBaseAction f = (ViewBaseAction) tView;
			f.show();
		}
		if (popupWindow.getContentView() != mViewArray.get(position)) {
			popupWindow.setContentView(mViewArray.get(position));
		}
		popupWindow.showAsDropDown(this, 0, 0);
	}

	public boolean onPressBack() {
		if (popupWindow != null && popupWindow.isShowing()) {
			popupWindow.dismiss();
			hideView();
			if (selectedButton != null) {
				selectedButton.setChecked(false);
			}
			return true;
		} else {
			return false;
		}

	}

	private void hideView() {
		View tView = mViewArray.get(selectPosition).getChildAt(0);
		if (tView instanceof ViewBaseAction) {
			ViewBaseAction f = (ViewBaseAction) tView;
			f.hide();
		}
	}

	private void init(Context context) {
		mContext = context;
		displayWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
		displayHeight = ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
		setOrientation(LinearLayout.HORIZONTAL);
	}

	@Override
	public void onDismiss() {
		showPopup(selectPosition);
		popupWindow.setOnDismissListener(null);
	}

	private OnButtonClickListener mOnButtonClickListener;
	public void setOnButtonClickListener(OnButtonClickListener l) {
		mOnButtonClickListener = l;
	}
	public interface OnButtonClickListener {
		void onClick(int selectPosition);
	}

}
