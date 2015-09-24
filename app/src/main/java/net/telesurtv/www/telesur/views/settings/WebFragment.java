package net.telesurtv.www.telesur.views.settings;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class WebFragment extends Fragment {


    public static WebFragment newInstance(String url) {
        WebFragment webFragment = new WebFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        webFragment.setArguments(bundle);
        return webFragment;
    }


    private WebView webView;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings_privacity, container, false);
        webView = (WebView) rootView.findViewById(R.id.webView);



        initialize(getArguments().getString("url"));

        return rootView;

    }

    private void initialize(String url) {
        try {
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.loadUrl(url);
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



