package net.telesurtv.www.telesur.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import net.telesurtv.www.telesur.R;

import java.io.InputStream;

/**
 * Created by Jhordan on 18/11/15.
 */
public class GlideImageGetter implements Html.ImageGetter {
    final Resources resources;
   // final Picasso pablo;
    final TextView textView;
    final Context context;


    public GlideImageGetter(final TextView textView, final Resources resources, final Context context) {
        this.textView  = textView;
        this.resources = resources;
        this.context = context;
    }

    @Override public Drawable getDrawable(final String source) {
        final BitmapDrawablePlaceHolder result = new BitmapDrawablePlaceHolder();

        new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(final Void... meh) {
                try {



                    return Glide.with(context).load("http://www.telesurtv.net"+ source).asBitmap().centerCrop().into(500, 500).get();
                   // return pablo.load("http://www.telesurtv.net"+ source).get();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(final Bitmap bitmap) {
                try {

                    ///

                    int targetWidth = 400;
                    double aspectRatio = (double) bitmap.getHeight() / (double) bitmap.getWidth();
                    int targetHeight = (int) (targetWidth * aspectRatio);
                    Bitmap resultBitmap = Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, false);
                    if (resultBitmap != bitmap) {
                        // Same bitmap is returned if sizes are the same
                       bitmap.recycle();
                    }

                    //7

                    final BitmapDrawable drawable = new BitmapDrawable(resources, resultBitmap);

                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

                    result.setDrawable(drawable);
                    result.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

                    textView.setText(textView.getText()); // invalidate() doesn't work correctly...*/



                } catch (Exception e) {
                e.printStackTrace();
                }
            }

        }.execute((Void) null);

        return result;
    }

    static class BitmapDrawablePlaceHolder extends BitmapDrawable {

        protected Drawable drawable;

        @Override
        public void draw(final Canvas canvas) {
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
        }

    }


    Point scaleImage(int w, int h, Point maxSize) {
        // Which is out of scale the most?
        float ratio = ((float) w) / ((float) maxSize.x);
        // Calculate new size. Maintains aspect ratio.
        int newWidth = (int) ((float) w / ratio);
        int newHeight = (int) ((float) h / ratio);

        return new Point(newWidth, newHeight);
    }


}
