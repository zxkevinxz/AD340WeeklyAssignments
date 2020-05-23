package com.example.ad340weeklyassignments;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MatchesDataModel {

    private FirebaseFirestore db;
    private List<ListenerRegistration> listeners;

    public MatchesDataModel() {
        db = FirebaseFirestore.getInstance();
        listeners = new ArrayList<>();
    }

    public void getMatchItems(Consumer<QuerySnapshot> dataChangedCallback, Consumer<FirebaseFirestoreException> dataErrorCallback) {
        ListenerRegistration listener = db.collection("matches")
                .addSnapshotListener(((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        dataErrorCallback.accept(e);
                    }
                    dataChangedCallback.accept(queryDocumentSnapshots);
                }));
        listeners.add(listener);
    }

    public void clear() {
        listeners.forEach(ListenerRegistration::remove);
    }

    public void updateLiked(MatchItem item) {
        DocumentReference dr = db.collection("matches").document(item.getUid());
        Map<String, Object> data = new HashMap<>();
        data.put("liked", item.isLiked());
        dr.update(data);
    }
}
