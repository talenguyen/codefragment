package com.tale.codefragment;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * Created by TALE on 7/23/2014.
 */
@EFragment(R.layout.fragment_index)
public class IndexFragment extends Fragment {

    @ViewById
    TextView tvName;

    @FragmentArg int index;

    @Bean MyBus bus;

    @AfterViews
    public void onViewCreated() {
        tvName.setText("Name: " + index);
    }

    @Click(R.id.open_child)
    public void openChildFragment() {
        bus.post(new Integer(index * 10));
    }
}
