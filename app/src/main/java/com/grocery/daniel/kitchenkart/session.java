package com.grocery.daniel.kitchenkart;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DANIEL on 14-01-2017.
 */

public class session {
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    Context ct;

    public session(Context context) {
        this.ct = context;
        sp = ct.getSharedPreferences("myreg", Context.MODE_PRIVATE);
        ed = sp.edit();
    }

    public void setloggedin(boolean loggedin) {
        ed.putBoolean("loginmode", loggedin);
        ed.commit();
    }
    public boolean loggedin(){
        return sp.getBoolean("loginmode",false);
    }
}
