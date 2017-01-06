package bphc.tech.com.arena17.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import bphc.tech.com.arena17.R;
import bphc.tech.com.arena17.app.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    WebView webView;
    LinearLayout linearLayout;
    Button tryAgain;
    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        webView = (WebView) view.findViewById(R.id.reg_web);
        linearLayout = (LinearLayout) view.findViewById(R.id.no_connection);
        tryAgain = (Button) view.findViewById(R.id.try_again);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()){
                    onViewCreated(view.getRootView(),savedInstanceState);
                }else {
                    Toast.makeText(getActivity(),"Check Your Internet Connection!!!!",Toast.LENGTH_LONG).show();
                }

            }
        });
        if (isNetworkAvailable()) {
            linearLayout.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.setWebViewClient(new WebViewClient() {
                                         public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                             Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
                                         }

                                         @Override
                                         public void onPageStarted(WebView view, String url, Bitmap favicon)
                                         {
                                             dialog.setMessage("Loading... Please Wait!!!");
                                             dialog.show();
                                         }


                                         @Override
                                         public void onPageFinished(WebView view, String url) {
                                             String webUrl = webView.getUrl();
                                             dialog.dismiss();
                                         }

            });
                    webView.loadUrl(Constants.REGISTRATION_LINK);
        }else {
            webView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
