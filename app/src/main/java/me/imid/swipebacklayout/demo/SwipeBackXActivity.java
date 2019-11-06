package me.imid.swipebacklayout.demo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public abstract class SwipeBackXActivity extends SwipeBackActivity {
    protected abstract int getStatusBarColorId();

    protected boolean makeStatusBarContentBlack() {
        return false;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setStatusBarToTransparent();
        setSystemUiVisibilityForDecorView();
        initContentView();
    }

    private void setStatusBarToTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setSystemUiVisibilityForDecorView() {
        int visibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        if (makeStatusBarContentBlack()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                visibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
        }
        getWindow().getDecorView().setSystemUiVisibility(visibility);
    }

    private void initContentView() {
        ViewGroup content = getWindow().getDecorView().findViewById(android.R.id.content);
        content.setFitsSystemWindows(true);
        int height = StatusBarUtils.getHeight(this);
        content.getChildAt(0).setPadding(0, height, 0, 0);
        View statusBar = new View(this);
        int id = getStatusBarColorId();
        if (id != 0) {
            statusBar.setBackgroundColor(getResources().getColor(id));
        }
        content.addView(statusBar, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, StatusBarUtils.getHeight(this)));
    }
}