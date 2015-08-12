package net.telesurtv.www.telesur.views.dialog;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.telesurtv.www.telesur.R;


/**
 * Created by Jhordan on 07/31/15.
 */
public class TermsTelesur extends DialogFragment {

    public TermsTelesur() {

    }

    public static TermsTelesur newInstance() {
        return new TermsTelesur();
    }

    private static final String TELESUR_URL_TERMNS = "http://www.telesurtv.net/pages/terminosdeuso.html";
    private WebView webView;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialog_about_read, container, false);
        webView = (WebView) rootView.findViewById(R.id.webView);
        initialize();
        return rootView;

    }


      private void initialize(){
          try {
              WebSettings webSettings = webView.getSettings();
              webSettings.setJavaScriptEnabled(true);
              webView.loadUrl(TELESUR_URL_TERMNS);
              progressDialog = ProgressDialog.show(getActivity(), null, getString(R.string.progress_dialog_help), true);
              webView.setWebViewClient(new WebViewClient() {
                  @Override
                  public void onPageFinished(WebView view, String url) {
                      super.onPageFinished(view, url);

                      progressDialog.dismiss();
                  }
              });

          } catch (Exception e) {
              e.printStackTrace();

              progressDialog.dismiss();


          }
      }

}



