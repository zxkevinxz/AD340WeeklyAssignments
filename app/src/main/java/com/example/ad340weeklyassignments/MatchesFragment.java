package com.example.ad340weeklyassignments;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchesFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView photo;
        public TextView name;
        public TextView occupation;
        public TextView age;
        public TextView description;
        public ImageButton favButton;
        public String favMsg;
        public String favColor = getString(R.string.favWhite);
        
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.matches_fragment, parent, false));
            photo =  itemView.findViewById(R.id.matches_cv_image);
            name =  itemView.findViewById(R.id.matches_cv_name);
            occupation =  itemView.findViewById(R.id.matches_cv_occupation);
            age =  itemView.findViewById(R.id.matches_age);
            description =  itemView.findViewById(R.id.matches_cv_desc);
            favButton = itemView.findViewById(R.id.matches_fav);
            favButton.setOnClickListener(v -> {
                if (favColor.equals(getString(R.string.favWhite))) {
                    Toast.makeText(v.getContext(), favMsg, Toast.LENGTH_SHORT).show();
                    favButton.setColorFilter(Color.RED);
                    favColor = getString(R.string.favRed);
                } else {
                    favButton.setColorFilter(Color.WHITE);
                    favColor = getString(R.string.favWhite);
                }

            });
        }
    }

    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final String[] mNames;
        private final String[] mOccupation;
        private final String[] mDescription;
        private final String age;
        private final Drawable[] mPhotos;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mNames = resources.getStringArray(R.array.match_names);
            mOccupation = resources.getStringArray(R.array.match_occ);
            mDescription = resources.getStringArray(R.array.match_desc);
            TypedArray a = resources.obtainTypedArray(R.array.photos);
            age = getString(R.string.profile_age);
            mPhotos = new Drawable[a.length()];
            for (int i = 0; i < mPhotos.length; i++) {
                mPhotos[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.photo.setImageDrawable(mPhotos[position % mPhotos.length]);
            holder.name.setText(mNames[position % mNames.length]);
            holder.description.setText(mDescription[position % mDescription.length]);
            holder.age.setText(age);
            holder.occupation.setText(mOccupation[position % mDescription.length]);

            StringBuilder onFavMsg = new StringBuilder();
            onFavMsg.append((String) getText(R.string.faved_msg)).append(holder.name.getText().toString()).append((String) getText(R.string.THANKS_EXCLAMATION));

            holder.favMsg = onFavMsg.toString();
        }

        @Override
        public int getItemCount() {
            return Constants.VIEW_LENGTH;
        }
    }


}


