package com.oneafricamedia.classifieds.widget.swipestack.util;

import android.animation.Animator;

/**
 * Created by brad on 2016/09/06.
 */
public class AnimationUtils {
    public static abstract class AnimationEndListener implements Animator.AnimatorListener {
        @Override
        public void onAnimationStart(Animator animation) {
            // Do nothing
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            // Do nothing
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            // Do nothing
        }
    }
}
