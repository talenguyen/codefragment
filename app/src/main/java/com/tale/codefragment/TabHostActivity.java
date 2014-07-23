package com.tale.codefragment;

import android.os.Bundle;

import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

/**
 * Created by TALE on 7/23/2014.
 */
@EActivity
public class TabHostActivity extends CallbackActionBarActivity {

    private ScreenPresenter presenter;
    private int currentPage;

    @Bean MyBus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tabhost);

        presenter = ScreenPresenter.create(this, R.id.fragmen_container);

        if (savedInstanceState == null) {
            presenter.showScreen(IndexFragment_.builder().index(1).build(), false);
        }
    }

    @Subscribe
    public void openChildFragment(Integer index) {
        presenter.showScreen(IndexFragment_.builder().index(index).build(), true);
        currentPage = index;
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Click(R.id.btn_1)
    public void openFragmentOne() {
        if (currentPage == 1) {
            return;
        }

        presenter.showScreen(IndexFragment_.builder().index(1).build(), false);
        presenter.clearStack();
        currentPage = 1;
    }

    @Click(R.id.btn_2)
    public void openFragmentTwo() {
        if (currentPage == 2) {
            return;
        }

        presenter.showScreen(IndexFragment_.builder().index(2).build(), false);
        presenter.clearStack();
        currentPage = 2;
    }
}
