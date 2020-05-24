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

        MatchesRecyclerViewAdapter adapter = new MatchesRecyclerViewAdapter(recyclerView.getContext(), matchItems, this);

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
}


