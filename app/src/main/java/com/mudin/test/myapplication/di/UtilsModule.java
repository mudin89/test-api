package com.mudin.test.myapplication.di;


import com.mudin.test.myapplication.viewmodel.MainActivityViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


//the injection class is define at here
@Module
public class UtilsModule {

    @Provides
    @Singleton
    CompositeDisposable getCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    MainActivityViewModel getViewModelFactory() {
        return new MainActivityViewModel();
    }

}
