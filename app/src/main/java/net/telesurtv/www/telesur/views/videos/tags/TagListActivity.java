package net.telesurtv.www.telesur.views.videos.tags;

import android.os.Bundle;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;


public class TagListActivity extends NavigatorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_list);
        setupSubActivityWithTitle();

        if (getIntent() != null) {
            String title = getIntent().getStringExtra("video_tag");
            String generic = getIntent().getStringExtra("video_generico");

            if (generic.equals("no_es_nulo"))
                setTitleToolbar(title);
            else
                setTitle(generic);
        }


    }

    private void setTheme() {
        if (getIntent() != null)
            setTheme(getIntent().getIntExtra("video_theme", 0));
    }

    private void setTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    private void setTitleToolbar(String title) {
        String titleToolbar = "";
        switch (title) {
            case "Corresponsales":
                titleToolbar = "Corresponsales";
                break;
            case "Temas":
                titleToolbar = "Temas";


                break;
            case "Países":
                titleToolbar = "Países";

                break;

            case "Categorias":
                titleToolbar = "Categorias";

                break;
        }

        setTitle(titleToolbar);

    }

}
