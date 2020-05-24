package com.example.ad340weeklyassignments;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MatchesRecyclerViewAdapter extends RecyclerView.Adapter<MatchesRecyclerViewAdapter.MatchesItemViewHolder> {

    private Context context;
    private ArrayList<MatchItem> matchItems;
    public static final String TAG = MatchesRecyclerViewAdapter.class.getSimpleName();
    public LikedClickListener listener;

    public MatchesRecyclerViewAdapter(Context context, ArrayList<MatchItem> matchItems, LikedClickListener listener) {
        this.context = context;
        this.matchItems = matchItems;
        this.listener = listener;
    }


    @NonNull
    @Override
    public MatchesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MatchesItemViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesItemViewHolder holder, int position) {
        if (matchItems != null && position < matchItems.size()) {
            MatchItem match = matchItems.get(position);
            holder.age.setText(match.getAge());
            holder.name.setText(match.getName());
            holder.description.setText(match.getDescription());
            holder.occupation.setText(match.getOccupation());
            Picasso.get().load(match.getImageUrl()).into(holder.photo);
            if (match.isLiked())
                holder.favButton.setColorFilter(Color.RED);

            StringBuilder onFavMsg = new StringBuilder();
            onFavMsg.append((String) context.getText(R.string.faved_msg)).append(holder.name.getText().toString()).append((String) context.getText(R.string.THANKS_EXCLAMATION));
            holder.favMsg = onFavMsg.toString();

            StringBuilder favBtnDesc = new StringBuilder();
            favBtnDesc.append(match.getName()).append(context.getText(R.string.fav_icon_set_content));
            holder.favButton.setContentDescription(favBtnDesc.toString());

            holder.favButton.setOnClickListener( v -> {
                if (!match.isLiked()) {
                    Toast.makeText(v.getContext(), holder.favMsg, Toast.LENGTH_SHORT).show();
                    holder.favButton.setColorFilter(Color.RED);
                    match.setLiked(true);
                } else {
                    holder.favButton.setColorFilter(Color.WHITE);
                    match.setLiked(false);
                }
                listener.onLikedClicked(match);
            });
        }
    }

    @Override
    public int getItemCount() {
        if (matchItems != null)
            return matchItems.size();
        else
            return 0;
    }

    public static class MatchesItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView photo;
        public TextView name;
        public TextView occupation;
        public TextView age;
        public TextView description;
        public ImageButton favButton;
        public String favMsg;

        public MatchesItemViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.matches_fragment, parent, false));

            photo =  itemView.findViewById(R.id.matches_cv_image);
            name =  itemView.findViewById(R.id.matches_cv_name);
            occupation =  itemView.findViewById(R.id.matches_cv_occupation);
            age =  itemView.findViewById(R.id.matches_age);
            description =  itemView.findViewById(R.id.matches_cv_desc);
            favButton = itemView.findViewById(R.id.matches_fav);
        }
    }
}