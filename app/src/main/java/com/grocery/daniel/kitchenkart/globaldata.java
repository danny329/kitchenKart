package com.grocery.daniel.kitchenkart;

import android.app.Application;

/**
 * Created by DANIEL on 17-03-2017.
 */

public class globaldata extends Application {
    private int OrderId=100;
    public int getData(){
        return this.OrderId;
    }

    public void setData(int d){
        this.OrderId=d;
    }
}
