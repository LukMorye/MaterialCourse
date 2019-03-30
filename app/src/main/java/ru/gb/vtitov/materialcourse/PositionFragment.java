package ru.gb.vtitov.materialcourse;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.gb.vtitov.materialcourse.adapters.PositionAdapter;
import ru.gb.vtitov.materialcourse.models.PositionModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PositionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PositionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PositionFragment extends Fragment implements PositionPresenterViewInterface {
	/* Constants params */
	private static final String TITLE_PARAM = "person";
	/* UI Elements */
	RecyclerView mRecyclerView;
	/* Presenter */
	PositionPresenter presenter;
	/* Params */
	private String mTitle;

	/* Implementation */
	public PositionFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param title Fragment title.
	 * @return A new instance of fragment PositionFragment.
	 */
	public static PositionFragment newInstance(String title) {
		PositionFragment fragment = new PositionFragment();
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
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_position, container, false);
	}


	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setupDI();
		initView(view);
	}

	private void setupDI() {
		presenter = new PositionPresenter(this);
		PositionInteractor positionInteractor = new PositionInteractor(getContext());
		presenter.setPositionInteractor(positionInteractor);

	}

	private void initView(View view) {
		mRecyclerView = view.findViewById(R.id.position_recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
	}

	@Override
	public void gotPositions(ArrayList<PositionModel> positions) {
		PositionAdapter adapter = new PositionAdapter(positions);
		mRecyclerView.setAdapter(adapter);
		presenter.setAdapter(adapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		presenter.onAppear();
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);

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
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}
}
