package org.maxsoft.briefweather;

import org.maxsoft.briefweather.R;
import org.maxsoft.custom.element.ScrollLayout;
import org.maxsoft.pm.activity.base.ActivityBase;
import org.maxsoft.utils.Constant;
import org.maxsoft.utils.DialogView;
import org.maxsoft.utils.PhoneStatusUtils;
import org.maxsoft.welcome.ilistener.OnViewChangeListener;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class BriefWeather extends ActivityBase implements OnViewChangeListener{

	@SuppressWarnings("unused")
	private static final String TAG = "BriefWeather";
	private ScrollLayout mScrollLayout;
	private ImageView[] imgs;
	private int count;
	private int currentItem;
	private Button startBtn;
	private RelativeLayout mainRLayout;
	private LinearLayout pointLLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sp = getSharedPreferences(Constant.BRIEFWEATHER, Context.MODE_PRIVATE);
		if(!sp.getBoolean(Constant.ISFIRST, false)){
			setContentView(R.layout.welcome);
			init();
		}else{
			setContentView(R.layout.brief_weather);
		}
	}
	
	/**
	 * ≥ı ºªØ
	 */
	private void init()
	{
		mScrollLayout = (ScrollLayout) findViewById(R.id.ScrollLayout);
		pointLLayout = (LinearLayout) findViewById(R.id.llayout);
		mainRLayout = (RelativeLayout) findViewById(R.id.mainRLayout);
		startBtn = (Button) findViewById(R.id.startBtn);
		startBtn.setOnClickListener(onClick);
		count = mScrollLayout.getChildCount();
		imgs = new ImageView[count];
		for (int i = 0; i < count; i++) {
			imgs[i] = (ImageView) pointLLayout.getChildAt(i);
			imgs[i].setEnabled(true);
			imgs[i].setTag(i);
		}
		currentItem = 0;
		imgs[currentItem].setEnabled(false);
		mScrollLayout.SetOnViewChangeListener(this);
	}
	
	private View.OnClickListener onClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.startBtn:
				if(!PhoneStatusUtils.isOpenNetwork(v.getContext())){
					DialogView.to_setting_dialog(BriefWeather.this);
				}else{
					mScrollLayout.setVisibility(View.GONE);
					pointLLayout.setVisibility(View.GONE);
					mainRLayout.setBackgroundResource(R.drawable.whatsnew_bg);
					v.destroyDrawingCache();
					sp = getSharedPreferences(Constant.BRIEFWEATHER, Context.MODE_PRIVATE);
					Editor edit = sp.edit();
					edit.putBoolean(Constant.ISFIRST, true);
					edit.commit();
					setContentView(R.layout.brief_weather);
				}
			}
		}

	};
	
	@Override
	public void OnViewChange(int position) {
		setcurrentPoint(position);
	}

	private void setcurrentPoint(int position) {
		if (position < 0 || position > count - 1 || currentItem == position) {
			return;
		}
		imgs[currentItem].setEnabled(true);
		imgs[position].setEnabled(false);
		currentItem = position;
	}
}
