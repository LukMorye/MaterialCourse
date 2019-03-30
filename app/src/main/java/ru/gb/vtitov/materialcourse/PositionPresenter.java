package ru.gb.vtitov.materialcourse;

import android.util.Log;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import ru.gb.vtitov.materialcourse.adapters.PositionAdapter;
import ru.gb.vtitov.materialcourse.models.PositionModel;

interface PositionPresenterListener {
	void onAppear();
	void setAdapter(PositionAdapter adapter);
}

interface PositionPresenterViewInterface {
	void gotPositions(ArrayList <PositionModel> positions);
}


public class PositionPresenter implements PositionPresenterListener{

	private final PositionPresenterViewInterface view;
	/* Interactors */
	private PositionInteractor interactor;
	/* Disposable */
	private CompositeDisposable disposable = new CompositeDisposable();
	/* Positions Adapter */
	private PositionAdapter positionAdapter;


	/* Constructor */
	PositionPresenter(PositionPresenterViewInterface view) {
		this.view = view;
	}


	/** Implementation */
	@Override
	public void setAdapter(PositionAdapter adapter) {
		positionAdapter = adapter;
	}

	/* Position Interactor */
	void setPositionInteractor(PositionInteractor interactor) {
		this.interactor = interactor;
		observePositionInteractor();
	}

	private void observePositionInteractor() {
		disposable.add(
						interactor.positionsSubject.subscribe(positions ->
										Log.d("PositionPresenter",
										"Positions count: " + positions.size()))
		);

	}

	@Override
	public void onAppear() {
		requestPositionList();
	}

	private void requestPositionList() {
		/* Will request to api here in real project */
		interactor.requestPositionList();
	}


}
