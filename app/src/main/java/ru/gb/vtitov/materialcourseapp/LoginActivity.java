package ru.gb.vtitov.materialcourseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import ru.gb.vtitov.materialcourseapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

	TopFragment topFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		if (savedInstanceState == null) {
			initFragments();
		}
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);

	}

	@Override
	protected void onResume() {
		super.onResume();
		Button enterButton = findViewById(R.id.login_button);
		enterButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				navigateToMainActivity();
			}
		});
	}

	private void initFragments() {
		if (topFragment == null) { topFragment = TopFragment.newInstance(); }
		switchToFragment(topFragment);
	}

	private void switchToFragment(Fragment fragment) {
		addFragment(fragment);
		showFragment(fragment);
	}

	private void addFragment(Fragment fragment) {
		getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.login_top_layout,fragment)
						.commit();
	}

	private void showFragment(Fragment fragment) {
		getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
						.show(fragment).commit();
	}

	private void navigateToMainActivity() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

	}
}
