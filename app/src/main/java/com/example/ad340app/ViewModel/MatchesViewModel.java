package com.example.ad340app.ViewModel;

import com.example.ad340app.MatchView;
import com.example.ad340app.datamodels.MatchesDataModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MatchesViewModel {
    private MatchesDataModel matchesDataModel;

    public MatchesViewModel(){
        matchesDataModel = new MatchesDataModel();
    }

    public void addMatch(MatchView picture){
        matchesDataModel.addMatche(picture);
    }

    public void getMatche(Consumer<ArrayList<MatchView>> responseCallback){
        matchesDataModel.getMatche(
                (QuerySnapshot querySnapshot) ->{
                    if(querySnapshot != null){
                        ArrayList<MatchView> matchViews = new ArrayList<>();
                        for (DocumentSnapshot matchesSnap : querySnapshot.getDocuments()){
                            MatchView picture = matchesSnap.toObject(MatchView.class);
                            assert  picture !=null;
                            picture.uid =matchesSnap.getId();
                            matchViews.add(picture);
                        }
                        responseCallback.accept(matchViews);
                    }
                },
                (databaseError -> System.out.println("Error reading Matches" + databaseError))
        );
    }

    public void updateMatch(MatchView picture){
        matchesDataModel.updateMatchById(picture);
    }

    public void clear(){
        matchesDataModel.clear();
    }
}
