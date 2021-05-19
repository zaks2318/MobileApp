package com.example.ad340app.datamodels;

import com.example.ad340app.MatchView;
import com.google.firebase.firestore.CollectionReference;
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

    public MatchesDataModel(){
        db = FirebaseFirestore.getInstance();
        listeners = new ArrayList<>();
    }

    public void addMatche(MatchView picture){
        CollectionReference matchRef = db.collection("Matches");
        matchRef.add(picture);
    }

    public void getMatche(Consumer<QuerySnapshot> dataChangedCallback, Consumer<FirebaseFirestoreException> dataErrorCallback){
        ListenerRegistration listener = db.collection("Matches")
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if(e !=null){
                        dataErrorCallback.accept(e);
                    }

                    dataChangedCallback.accept(queryDocumentSnapshots);
                });
        listeners.add(listener);
    }

    public void updateMatchById(MatchView picture){
        DocumentReference matchRef = db.collection("Matches").document(picture.uid);
        Map<String, Object> data = new HashMap<>();
        data.put("name", picture.name);
        data.put("liked", picture.liked);
        data.put("imageUrl", picture.imageUrl);
        matchRef.update(data);
    }

    public  void clear(){
        listeners.forEach(ListenerRegistration::remove);
    }
}
