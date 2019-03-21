package ru.gb.vtitov.materialcourseapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class PersonFragment extends Fragment {

	/* Constants params */
	private static final String TITLE_PARAM = "title";
	/* UI Elements */
	private TextView mTitleText;
	private ImageView mImageView;

	/* Params */
	private String mTitle;
	/* Listeners */
	PersonFragment.OnFragmentInteractionListener mListener;

	/* Implementation */
	public PersonFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param title Parameter 1.
	 * @return A new instance of fragment PersonFragment.
	 */
	public static PersonFragment newInstance(String title) {
		PersonFragment fragment = new PersonFragment();
		Bundle args = new Bundle();
		args.putString(TITLE_PARAM, title);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mTitle = getArguments().getString(TITLE_PARAM);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
													 Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_person, container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {
		mTitleText = view.findViewById(R.id.person_title);
		mTitleText.setText(mTitle);
		mImageView = view.findViewById(R.id.person_share_button);
		mImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Fragment fragment = mListener.defineSecondFragment();
				ChangeBounds changeBounds = new ChangeBounds();
				changeBounds.setPathMotion(new ArcMotion());
				changeBounds.setDuration(2000);
				changeBounds.setInterpolator(new OvershootInterpolator(1.5f));
				fragment.setSharedElementEnterTransition(changeBounds);
				getFragmentManager()
								.beginTransaction()
								.replace(R.id.main_content, fragment)
								.addToBackStack(this.getClass().getName())
								.setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
								.addSharedElement(mImageView, getString(R.string.share_element_name))
						.commit();
			}
		});
	}



	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof PersonFragment.OnFragmentInteractionListener) {
			mListener = (PersonFragment.OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
							+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		Fragment defineSecondFragment();
	}
}
