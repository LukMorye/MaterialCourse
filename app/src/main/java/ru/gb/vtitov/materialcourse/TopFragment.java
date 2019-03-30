package ru.gb.vtitov.materialcourse;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.gb.vtitov.materialcourse.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {

	public static TopFragment newInstance() {
		return new TopFragment();
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
													 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_top, container, false);
	}

}
