package com.example.farzi.testrecycleview.service;

import com.example.farzi.testrecycleview.model.FlowerResponse;

import java.util.List;

import rx.Observable;

/**
 * Created by Farzi on 25/06/2017.
 */

public interface FlowerViewInterface {
    void onComplete();

    void onError(String message);

    void onFlower(List<FlowerResponse> flowerResponses);

    Observable<List<FlowerResponse>> getFlowers();
}
