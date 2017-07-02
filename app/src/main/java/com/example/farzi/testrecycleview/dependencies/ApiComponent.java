package com.example.farzi.testrecycleview.dependencies;

import com.example.farzi.testrecycleview.ui.MainActivity;

import dagger.Component;

/**
 * Created by Farzi on 25/06/2017.
 */
//Component is a gateway what we asking and what module provide
    //That's whay inside the module there is @Provide annotation
// Where do you want to inject it
    //this component need to work with "ApiModule" module to know how to custroct
@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {
    void inject(MainActivity mainActivity);
}
