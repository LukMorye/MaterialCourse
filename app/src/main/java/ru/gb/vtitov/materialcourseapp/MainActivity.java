package ru.gb.vtitov.materialcourseapp;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

	private  PersonFragment personFragment;
	private  PositionFragment positionFragment;
	private  MoreFragment moreFragment;
	private  TextView badgeView;
	private  Timer badgeTimer;
	private  TimerTask timerTask;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
					= new BottomNavigationView.OnNavigationItemSelectedListener() {

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			switch (item.getItemId()) {
				case R.id.navigation_person:
					switchToFragment(personFragment);
					activateNotificationBadge();
					return true;
				case R.id.navigation_position:
					activateNotificationBadge();
					switchToFragment(positionFragment);
					return true;
				case R.id.navigation_more:
					switchToFragment(moreFragment);
					stopNotifications();
					return true;
			}
			return false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		if (savedInstanceState == null) {
			initFragments();
		}
		badgeView = findViewById(R.id.navigation_badge_more);
	}

	private void initFragments() {
		if (personFragment == null) { personFragment = PersonFragment.newInstance("Person"); }
		if (positionFragment == null) { positionFragment = PositionFragment.newInstance("Position"); }
		if (moreFragment == null) { moreFragment = MoreFragment.newInstance("More"); }
		switchToFragment(personFragment);
		activateNotificationBadge();
	}

	private void switchToFragment(Fragment fragment) {
		addFragment(fragment);
		showFragment(fragment);
	}

	private void addFragment(Fragment fragment) {
		getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.main_content,fragment)
						.commit();
	}

	private void showFragment(Fragment fragment) {
		getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
						.show(fragment).commit();
	}

	private void activateNotificationBadge() {
		if (timerTask != null) return;
		badgeTimer  = new Timer();
		timerTask = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						long badgeValue = Long.parseLong(badgeView.getText().toString());
						badgeValue += 1;
						if (badgeValue > 0) badgeView.setVisibility(View.VISIBLE);
						badgeView.setText(String.valueOf(badgeValue));
					}
				});
			}
		};
		badgeTimer.schedule(timerTask, 0, 1000);
	}

	private void stopNotifications() {
		badgeTimer.cancel();
		timerTask.cancel();
		badgeView.setVisibility(View.GONE);
		badgeView.setText("0");
		timerTask = null;
		badgeTimer = null;
	}
}
