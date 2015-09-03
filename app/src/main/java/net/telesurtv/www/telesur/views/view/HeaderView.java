package net.telesurtv.www.telesur.views.view;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.novoda.notils.caster.Views;

import net.telesurtv.www.telesur.R;

/**
 * Created by Jhordan on 05/08/15.
 */
public class HeaderView extends LinearLayout {

    private TextView txtTitle, txtDate, txtAuthor;

    public HeaderView(Context context) {
        super(context);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.view_toolbar_header, this, true);
        txtTitle = Views.findById(this, R.id.txt_header_title);
        txtAuthor = Views.findById(this, R.id.txt_header_author);
    }

    public void updateWith(String title, String date, String author) {

        txtTitle.setText(Html.fromHtml(title));

        if (date.equals(""))
            txtAuthor.setText("Escrito por " +Html.fromHtml(author));
        else if(!author.equals(""))
            txtAuthor.setText(Html.fromHtml(date) + " - " + Html.fromHtml(author));
        else
            txtAuthor.setText(Html.fromHtml(date));


    }
}
