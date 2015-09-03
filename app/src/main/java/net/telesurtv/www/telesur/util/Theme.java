package net.telesurtv.www.telesur.util;

import android.support.annotation.ColorRes;
import android.support.annotation.StyleRes;

import net.telesurtv.www.telesur.R;

/**
 * Category Themes
 * Created by Jhordan on 17/08/15.
 */

public enum Theme {

    news(R.color.theme_red_primary, R.color.theme_red_background, R.color.theme_red_text, R.style.Telesur_Red),
    interview(R.color.theme_yellow_primary, R.color.theme_yellow_background, R.color.theme_yellow_text, R.style.Telesur_Yellow),
    documental(R.color.theme_green_primary, R.color.theme_green_background, R.color.theme_green_text, R.style.Telesur_Green),
    infografi(R.color.theme_purple_primary, R.color.theme_purple_background, R.color.theme_purple_text, R.style.Telesur_Purple),
    special(R.color.theme_blue_primary, R.color.theme_blue_background, R.color.theme_blue_text, R.style.Telesur_Blue),
    report(R.color.theme_orange_primary, R.color.theme_orange_background, R.color.theme_orange_text, R.style.Telesur_Orange);

    private final int colorPrimary;
    private final int windowBackground;
    private final int txtColorPrimary;
    private final int style;

    Theme(int colorPrimary, int windowBackground, int txtColorPrimary, int style) {
        this.colorPrimary = colorPrimary;
        this.windowBackground = windowBackground;
        this.txtColorPrimary = txtColorPrimary;
        this.style = style;
    }

    @ColorRes
    public int getColorPrimary() {
        return colorPrimary;
    }

    @ColorRes
    public int getWindowBackground() {
        return windowBackground;
    }

    @ColorRes
    public int getTxtColorPrimary() {
        return txtColorPrimary;
    }

    @StyleRes
    public int getStyle() {
        return style;
    }


}

