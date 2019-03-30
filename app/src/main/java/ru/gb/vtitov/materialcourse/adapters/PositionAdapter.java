package ru.gb.vtitov.materialcourse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.gb.vtitov.materialcourse.R;
import ru.gb.vtitov.materialcourse.models.PositionModel;

public class PositionAdapter extends RecyclerView.Adapter <PositionAdapter.PositionViewHolder>{

	/* Stored */
	private final List<PositionModel> positions;

	/* Constructor */
	public PositionAdapter(List<PositionModel> positions) {
		this.positions = positions;
	}


	/** Implementation Adapter interface */
	@NonNull
	@Override
	public PositionAdapter.PositionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.position_view_holder,
						parent,
						false);
		return new PositionViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull PositionViewHolder holder, int index) {
		PositionModel position = positions.get(index);
		holder.imageView.setImageBitmap(position.getImage());
		holder.titleView.setText(position.getTitle());
		holder.dateView.setText(position.getDate());
	}

	@Override
	public int getItemCount() {
		return positions.size();
	}


	public static class PositionViewHolder extends RecyclerView.ViewHolder {

		ImageView imageView;
		TextView titleView;
		TextView dateView;

		public PositionViewHolder(@NonNull View itemView) {
			super(itemView);
			imageView = itemView.findViewById(R.id.position_view_holder_image_view);
			titleView = itemView.findViewById(R.id.position_view_holder_title_view);
			dateView = itemView.findViewById(R.id.position_view_holder_date_view);
		}
	}
}
