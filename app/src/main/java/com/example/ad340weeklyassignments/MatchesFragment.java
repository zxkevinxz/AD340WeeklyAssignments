package com.example.ad340weeklyassignments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MatchesFragment extends Fragment implements LikedClickListener {
    
    private MatchesViewModel matchesViewModel;
    private ArrayList<MatchItem> matchItems;
    private ArrayList<MatchItem> filteredList;
    private double latitude, longitude;
    private boolean LOADING = true;
    private MatchesRecyclerViewAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filteredList = new ArrayList<>();
        if (getArguments() != null)
            matchItems = getArguments().getParcelableArrayList(Constants.KEY_MATCHES);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        matchesViewModel = new MatchesViewModel();
        View view = inflater.inflate(R.layout.recycler_view, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        latitude = UserHome.getLatitudeNetwork();
        longitude = UserHome.getLongitudeNetwork();

        // for testing if FTL location is on, set long/lat to 0.0 like location is off
        if (latitude == 37.422000885009766 && longitude == -122.08406066894531) {
            latitude = 0.0;
            longitude = 0.0;
        }

        for (int i = 0; i < matchItems.size(); i++) {
            if (distance(latitude, longitude, Double.parseDouble(matchItems.get(i).getLat()), Double.parseDouble(matchItems.get(i).getLon())) <= Constants.DEFAULT_DISTANCE) {
                filteredList.add(matchItems.get(i));
            }
        }

        // for no permissions load all
        if (latitude == 0.0 && longitude == 0.0) {
            filteredList.addAll(matchItems);
        }

        adapter = new MatchesRecyclerViewAdapter(recyclerView.getContext(), filteredList, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    void update() {
        if (LOADING) {
            LOADING = false;
        } else {
            latitude = UserHome.getLatitudeNetwork();
            longitude = UserHome.getLongitudeNetwork();
            filteredList.clear();
            for (int i = 0; i < matchItems.size(); i++) {
                if (distance(latitude, longitude, Double.parseDouble(matchItems.get(i).getLat()), Double.parseDouble(matchItems.get(i).getLon())) <= Constants.DEFAULT_DISTANCE) {
                    filteredList.add(matchItems.get(i));
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPause() {
        matchesViewModel.clear();
        super.onPause();
    }

    @Override
    public void onLikedClicked(MatchItem item) {
        matchesViewModel.updateLiked(item);
    }

    // distance calculator from https://www.geodatasource.com/developers/java
    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }
}