package ru.gb.vtitov.materialcourse;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Date;

import io.reactivex.subjects.PublishSubject;
import ru.gb.vtitov.materialcourse.models.PositionModel;


public class PositionInteractor {

	PublishSubject<ArrayList<PositionModel>> positionsSubject = PublishSubject.create();
	private Context context;

	 PositionInteractor(Context context) {
	 	this.context = context;
	 }

	void requestPositionList() {
		/* Will request by repository positions list from api.
		*
		* And just simple generator, now
		* */
		ArrayList<PositionModel> positions = new ArrayList<>();
		positions.add(
						new PositionModel("Chief Technical Officer",
										BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_position_0),
										new Date())
		);
		positions.add(
						new PositionModel("Mobile Team Lead",
										BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_position_1),
										new Date())
		);
		positions.add(
						new PositionModel("Senior Developer",
										BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_position_2),
										new Date())
		);
		positions.add(
						new PositionModel("Middle Developer",
										BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_position_3),
										new Date())
		);
		positions.add(
						new PositionModel("Junior Developer",
										BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_position_4),
										new Date())
		);
		positionsSubject.onNext(positions);
	}
}
