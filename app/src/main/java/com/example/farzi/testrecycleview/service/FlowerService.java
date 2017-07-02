package com.example.farzi.testrecycleview.service;

import com.example.farzi.testrecycleview.model.FlowerResponse;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Farzi on 24/06/2017.
 */

public interface FlowerService {

    @GET("/feeds/flowers.json")
    Observable<List<FlowerResponse>> getFlower();
}
