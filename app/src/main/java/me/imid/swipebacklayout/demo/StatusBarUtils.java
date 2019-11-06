package me.imid.swipebacklayout.demo;

import android.content.Context;
import android.content.res.Resources;

public class StatusBarUtils {
    public static int getHeight(Context context) {
        Resources resources = context.getResources();
        int resId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resId == 0) {
            return 0;
        }
        return resources.getDimensionPixelSize(resId);
    }
}
