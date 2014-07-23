package com.tale.codefragment;

import com.squareup.otto.Bus;

import org.androidannotations.annotations.EBean;

/**
 * Created by TALE on 7/23/2014.
 */
@EBean(scope = EBean.Scope.Singleton)
public class MyBus extends Bus {
}
