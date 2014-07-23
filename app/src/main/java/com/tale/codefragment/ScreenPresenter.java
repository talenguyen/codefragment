package com.tale.codefragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

/**
 * Created by TALE on 7/23/2014.
 */
public class ScreenPresenter {

    public final int fragmentContainerId;
    public final FragmentManager fragmentManager;
    private Stack<Fragment> mBackStack;

    private ScreenPresenter(final CallbackActionBarActivity activity, int fragmentContainerId) {
        activity.registerCallback(new CallbackActionBarActivity.Callback(){
            @Override
            public boolean onBackPressed() {
                return navigateBack();
            }
        });
        this.fragmentContainerId = fragmentContainerId;
        this.fragmentManager = activity.getSupportFragmentManager();
    }

    public static ScreenPresenter create(CallbackActionBarActivity activity, int fragmentContainerId) {
        return new ScreenPresenter(activity, fragmentContainerId);
    }

    public void clearStack() {
        if (mBackStack != null) {
            mBackStack.clear();
            mBackStack = null;
        }
    }

    /**
     * Change main fragment screen to target screen
     */
    public void showScreen(Fragment fragment, boolean addToBackStack) {
        if (addToBackStack) {
            final Fragment curFrag = fragmentManager.findFragmentById(fragmentContainerId);
            if (curFrag != null) {
                if (mBackStack == null) {
                    mBackStack = new Stack<Fragment>();
                }
                mBackStack.push(curFrag);
            }
        }

        final String tag = "FRAG_" + fragment.hashCode();
        if (fragmentManager.findFragmentByTag(tag) == null) {
            final FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_right,
                    R.anim.slide_out_left);
            ft.replace(fragmentContainerId, fragment, tag);
            ft.commit();
        }
    }

    public boolean navigateBack() {
        if (mBackStack == null || mBackStack.isEmpty()) {
            return false;
        }
        final Fragment fragment = mBackStack.pop();
        final String tag = "FRAG_" + fragment.hashCode();
        if (fragmentManager.findFragmentByTag(tag) == null) {
            final FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_left,
                    R.anim.slide_out_right);
            ft.replace(fragmentContainerId, fragment, tag);
            ft.commit();
        }
        return true;
    }

}
