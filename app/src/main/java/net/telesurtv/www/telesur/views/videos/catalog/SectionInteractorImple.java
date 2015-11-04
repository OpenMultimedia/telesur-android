package net.telesurtv.www.telesur.views.videos.catalog;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.VideoMenu;
import net.telesurtv.www.telesur.util.Theme;

import java.util.ArrayList;

/**
 * Created by Jhordan on 28/10/15.
 */
public class SectionInteractorImple implements SectionInteractor {

    private final static String[] titleSection = {
            "Noticias",
            "Entrevistas",
            "Documentales",
            "Infografias",
            "Especiales",
            "Reportajes"
    };


    private final static String[] enumNamesSection = {
            "news",
            "report",
            "interview",
            "documental",
            "infografi",
            "special"
    };


    private final static int[] iconSection = {
            R.drawable.ic_v_menu_news,
            R.drawable.ic_v_menu_interview,
            R.drawable.ic_v_menu_documental,
            R.drawable.ic_v_menu_info,
            R.drawable.ic_v_menu_specials,
            R.drawable.ic_v_menu_report
    };


    private ArrayList<VideoMenu> getMenuList() {
        ArrayList<VideoMenu> videoMenus = new ArrayList<>();
        Theme theme;
        for (int i = 0; i < titleSection.length; i++) {
            VideoMenu videoMenu = new VideoMenu();
            videoMenu.setTitle(titleSection[i]);
            videoMenu.setIcon(iconSection[i]);
            videoMenu.setTheme(enumNamesSection[i]);
            theme = Theme.valueOf(videoMenu.getTheme());
            videoMenu.setStyle(theme.getStyle());
            videoMenus.add(videoMenu);
        }
        return videoMenus;

    }

    @Override public void loadItems(LoaderListenerSection loaderListenerSection) {
        loaderListenerSection.onLoadData(getMenuList());
    }
}