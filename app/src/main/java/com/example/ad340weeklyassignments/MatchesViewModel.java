package com.example.ad340weeklyassignments;

import android.content.Context;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MatchesViewModel {

    private MatchesDataModel model;

    public MatchesViewModel() { model = new MatchesDataModel(); }

    public void getMatchItems(Consumer<ArrayList<MatchItem>> responseCallback) {
        model.getMatchItems(
            (QuerySnapshot querySnapshot) -> {
                if (querySnapshot != null) {
                    ArrayList<MatchItem> matchItems = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                        MatchItem item = documentSnapshot.toObject(MatchItem.class);
                        assert item != null;
                        matchItems.add(item);
                    }
                    responseCallback.accept(matchItems);
                }
            },
            (databaseError -> System.out.println("Error reading Match Items: " + databaseError))
        );
    }

    public void clear() { model.clear(); }

    public void updateLiked(MatchItem item) { model.updateLiked(item); }
}
