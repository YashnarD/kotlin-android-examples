package com.example.navigation.utils;

import androidx.navigation.NavOptions;

import com.example.navigation.R;

public class MyNavOptions {

    public static NavOptions getNavOptions() {
        return new NavOptions.Builder()
                .setEnterAnim(R.anim.enter)
                .setExitAnim(R.anim.exit)
                .setPopEnterAnim(R.anim.pop_enter)
                .setPopExitAnim(R.anim.pop_exit).build();
    }
}
