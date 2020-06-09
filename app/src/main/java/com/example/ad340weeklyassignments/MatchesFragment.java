package com.example.ad340weeklyassignments;

import android.os.Bundle;
import android.util.Log;
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

    final static String TAG = MatchesFragment.class.getSimpleName();
    private MatchesViewModel matchesViewModel;
    private ArrayList<MatchItem> matchItems;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            matchItems = getArguments().getParcelableArrayList(Constants.KEY_MATCHES);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        matchesViewModel = new MatchesViewModel();
        View view = inflater.inflate(R.layout.recycler_view, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        double latitude = UserHome.getLatitudeNetwork();
        double longitude = UserHome.getLongitudeNetwork();

        System.out.println(latitude);
        System.out.println(longitude);

        ArrayList<MatchItem> filteredList = new ArrayList<>();

        for (int i = 0; i < matchItems.size(); i++) {
            if (distance(latitude, longitude, Double.parseDouble(matchItems.get(i).getLat()), Double.parseDouble(matchItems.get(i).getLon())) <= Constants.DEFAULT_DISTANCE) {
                filteredList.add(matchItems.get(i));
            }
        }

        // for no permissions load all
        if (latitude == 0.0 && longitude == 0.0) {
            filteredList.addAll(matchItems);
        }

        // for testing load all if location is on in FTL
        if (latitude == 37.422000885009766 && longitude == -122.08406066894531)
            filteredList.addAll(matchItems);

        MatchesRecyclerViewAdapter adapter = new MatchesRecyclerViewAdapter(recyclerView.getContext(), filteredList, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
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
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            return (dist);
        }
    }
}