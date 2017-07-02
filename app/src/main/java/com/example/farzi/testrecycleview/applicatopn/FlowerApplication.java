package com.example.farzi.testrecycleview.applicatopn;

import android.app.Application;

import com.example.farzi.testrecycleview.dependencies.ApiComponent;
import com.example.farzi.testrecycleview.dependencies.DaggerApiComponent;
import com.example.farzi.testrecycleview.dependencies.DaggerNetworkComponent;
import com.example.farzi.testrecycleview.dependencies.NetworkComponent;
import com.example.farzi.testrecycleview.dependencies.NetworkModule;
import com.example.farzi.testrecycleview.model.Constant;

/**
 * Created by Farzi on 25/06/2017.
 */

public class FlowerApplication extends Application {
    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();
    }

    private void resolveDependency() {
         mApiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent())
                .build();
    }

    private NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constant.BASE_URL))
                .build();
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }
}
