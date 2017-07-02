package com.example.farzi.testrecycleview.dependencies;

import com.example.farzi.testrecycleview.service.FlowerService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Farzi on 25/06/2017.
 */
@Module
public class ApiModule {

    @Provides
    @CustomScope
    FlowerService provideFlowerService(Retrofit retrofit) {
        return retrofit.create(FlowerService.class);
    }
}
