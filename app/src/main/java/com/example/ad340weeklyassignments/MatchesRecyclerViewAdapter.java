package com.example.ad340weeklyassignments;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
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

    public MatchesRecyclerViewAdapter (Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MatchesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MatchesItemViewHolder(LayoutInflater.from(parent.getContext()), parent, context);
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

            StringBuilder onFavMsg = new StringBuilder();
            onFavMsg.append((String) context.getText(R.string.faved_msg)).append(holder.name.getText().toString()).append((String) context.getText(R.string.THANKS_EXCLAMATION));
            holder.favMsg = onFavMsg.toString();
        }
    }

    @Override
    public int getItemCount() {
        if (matchItems != null)
            return matchItems.size();
        else
            return 0;
    }

    void setMatchItems(ArrayList<MatchItem> items) {
        this.matchItems = items;
    }

    public static class MatchesItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView photo;
        public TextView name;
        public TextView occupation;
        public TextView age;
        public TextView description;
        public ImageButton favButton;
        public String favMsg;
        public String favColor;

        public MatchesItemViewHolder(LayoutInflater inflater, ViewGroup parent, Context context) {
            super(inflater.inflate(R.layout.matches_fragment, parent, false));
            photo =  itemView.findViewById(R.id.matches_cv_image);
            name =  itemView.findViewById(R.id.matches_cv_name);
            occupation =  itemView.findViewById(R.id.matches_cv_occupation);
            age =  itemView.findViewById(R.id.matches_age);
            description =  itemView.findViewById(R.id.matches_cv_desc);
            favButton = itemView.findViewById(R.id.matches_fav);
            favColor = context.getString(R.string.favWhite);
            favButton.setOnClickListener(v -> {
                if (favColor.equals(context.getString(R.string.favWhite))) {
                    Toast.makeText(v.getContext(), favMsg, Toast.LENGTH_SHORT).show();
                    favButton.setColorFilter(Color.RED);
                    favColor = context.getString(R.string.favRed);
                } else {
                    favButton.setColorFilter(Color.WHITE);
                    favColor = context.getString(R.string.favWhite);
                }

            });
        }
    }
}