package com.tale.codefragment;

import android.support.v7.app.ActionBarActivity;

/**
 * Created by TALE on 7/23/2014.
 */
public class CallbackActionBarActivity extends ActionBarActivity {

    public static class Callback {
        public void onStart(){};
        public void onResume(){};
        public void onPause(){};
        public void onStop(){};
        public void onDestroy(){};
        public boolean onBackPressed(){return false;};

    }

    private Callback callback;

    public void registerCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (callback != null) {
            callback.onResume();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (callback != null) {
            callback.onStop();
        }
    }

    @Override
    public void onBackPressed() {
        if (callback != null && callback.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (callback != null) {
            callback.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (callback != null) {
            callback.onDestroy();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (callback != null) {
            callback.onStart();
        }
    }
}
