package com.example.frolo.racingby.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.frolo.racingby.R;

public class HelpFragment extends Fragment {

    private WebView wvHelp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Справка");
        wvHelp = view.findViewById(R.id.webViewHelp);
        wvHelp.setWebViewClient(new WebViewClient());
        wvHelp.loadUrl("https://docs.google.com/document/d/1Dbm_D5g69-7Ch8V1ldWOYpo9IiAQXbLr5zLFsBxbERE/");
    }
}
