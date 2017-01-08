package cn.mandroid.wechatrobot.ui.widget.faboptions;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by wrBug on 2017/1/8.
 */

public class FloatMenuBehavior extends CoordinatorLayout.Behavior<FabOptions> {
    private static final String TAG = FloatMenuBehavior.class.getSimpleName();

    public FloatMenuBehavior() {
    }

    public FloatMenuBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FabOptions child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FabOptions child, View dependency) {
        float translationY = getFabTranslationYForSnackbar(parent, child);
        float percentComplete = -translationY / dependency.getHeight();
        child.setRotation(-90 * percentComplete);
        child.setTranslationY(translationY);
        return false;
    }

    private float getFabTranslationYForSnackbar(CoordinatorLayout parent,
                                                FabOptions fab) {
        float minOffset = 0;
        final List<View> dependencies = parent.getDependencies(fab);
        for (int i = 0, z = dependencies.size(); i < z; i++) {
            final View view = dependencies.get(i);
            if (view instanceof Snackbar.SnackbarLayout && parent.doViewsOverlap(fab, view)) {
                minOffset = Math.min(minOffset,
                        ViewCompat.getTranslationY(view) - view.getHeight());
            }
        }

        return minOffset;
    }
}
