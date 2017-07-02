package com.example.farzi.testrecycleview.base;

import com.example.farzi.testrecycleview.model.FlowerResponse;
import com.example.farzi.testrecycleview.service.FlowerViewInterface;

import java.util.List;

import rx.Observer;

/**
 * Created by Farzi on 25/06/2017.
 */

public class FlowerPresenter extends BasePresenter implements Observer<List<FlowerResponse>> {

    private FlowerViewInterface mViewInterface;

    public FlowerPresenter(FlowerViewInterface viewInterface) {
        this.mViewInterface = viewInterface;
    }

    @Override
    public void onCompleted() {
        mViewInterface.onComplete();
    }

    @Override
    public void onError(Throwable e) {
        mViewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(List<FlowerResponse> flowerResponses) {
        mViewInterface.onFlower(flowerResponses);
    }

    public void fetchFlowers() {
        unSubscribeAll();
        subscribe(mViewInterface.getFlowers(), FlowerPresenter.this);
    }
}
