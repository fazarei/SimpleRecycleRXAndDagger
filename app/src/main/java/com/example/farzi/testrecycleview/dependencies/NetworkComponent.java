package com.example.farzi.testrecycleview.dependencies;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Farzi on 25/06/2017.
 */
@Singleton
@Component(modules=NetworkModule.class)
public interface NetworkComponent {
    Retrofit retrofit();
}
