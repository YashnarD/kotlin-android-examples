package com.example.viewpager1.utils

import android.view.View
import androidx.viewpager.widget.ViewPager

class CubeInScalingTransformation : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.cameraDistance = 20000f

        if (position < -1) {     // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.setAlpha(0f)

        } else if (position <= 0) {    // [-1,0]
            page.setAlpha(1f);
            page.setPivotX(page.width.toFloat());
            page.setRotationY(90 * Math.abs(position));

        } else if (position <= 1) {    // (0,1]
            page.setAlpha(1f);
            page.setPivotX(0f);
            page.setRotationY(-90 * Math.abs(position));

        } else {    // (1,+Infinity]
            // This page is way off-screen to the right.
            page.setAlpha(0f);

        }



        if (Math.abs(position) <= 0.5) {
            page.setScaleY(Math.max(.4f, 1 - Math.abs(position)));
        } else if (Math.abs(position) <= 1) {
            page.setScaleY(Math.max(.4f, Math.abs(position)));

        }
    }

}