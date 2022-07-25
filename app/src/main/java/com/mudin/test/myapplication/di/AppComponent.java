package com.mudin.test.myapplication.di;

import com.mudin.test.myapplication.view.CommentActivity;
import com.mudin.test.myapplication.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


//interface to do the injection to any activity/fragment

@Component(modules = {UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(MainActivity mainActivity);
    void doInjection(CommentActivity commentActivity);

}
