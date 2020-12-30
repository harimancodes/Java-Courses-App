package com.example.javacoursesapp.ui.courses;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.javacoursesapp.R;
import com.example.javacoursesapp.adapter.ModuleAdapter;
import com.example.javacoursesapp.model.Module;

import java.util.ArrayList;

public class CoursesFragment extends Fragment {
    private static final int FULL_SCREEN_SETTING = View.SYSTEM_UI_FLAG_FULLSCREEN |
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
            View.SYSTEM_UI_FLAG_IMMERSIVE;
    private static int i = 0;
    private WebView webView;
    private ArrayList<Module> beginnerModuleArrayList,intermediateArrayList;
    private ModuleAdapter beginnerModuleAdapter,intermediateModuleAdapter;
    private ListView beginnerListView,intermediateListView;
    private ProgressBar progressBar;
    private View lastClickedView=null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_courses, container, false);
        progressBar=rootView.findViewById(R.id.progressBar);
        progressBar.setFocusable(true);

        beginnerListView = rootView.findViewById(R.id.beginner_list_view);
        intermediateListView=rootView.findViewById(R.id.intermediate_list_view);

        beginnerModuleArrayList = new ArrayList<>();
        intermediateArrayList=new ArrayList<>();
        beginnerModuleAdapter = new ModuleAdapter(getActivity(), beginnerModuleArrayList);
        intermediateModuleAdapter=new ModuleAdapter(getActivity(),intermediateArrayList);

        loadBeginnerModuleData("Java - Session 01-Introduction to java","<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/r59xYe3Vyks\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\\\" allowfullscreen></iframe>");
        loadBeginnerModuleData("Java - Session 02-Java History ","<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/eX7VnkcXMdM\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
        loadBeginnerModuleData("Java - Session 03-Java Features","<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/O5hShUO6wxs\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
        loadBeginnerModuleData("Java - Session 04-Java Installation","<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/JONbFDp41VI\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
        loadBeginnerModuleData("Java - Session 05-Java Hello World","<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/I2wvhRUVNTM\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
        loadIntermediateModuleData("Java - Session 06-Java Hello World Explanation","<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/4f82EYG81c0\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
        loadIntermediateModuleData("Java - Session 07-Java IDE","<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/N-wXTRpR03U\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
        loadIntermediateModuleData("Java - Session 08-Java Variables and Types","<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/4ekASokneGU\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
        loadIntermediateModuleData("Java - Session 09-Getting User Input","<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qgMH6jOOFOE\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");

        beginnerListView.setAdapter(beginnerModuleAdapter);
        intermediateListView.setAdapter(intermediateModuleAdapter);
        beginnerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(lastClickedView!=null)lastClickedView.setSelected(false);
                view.setSelected(true);

                loadWebViewData(beginnerModuleArrayList.get(position).getModuleUrl());
                lastClickedView=view;
            }
        });
        intermediateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(lastClickedView!=null)lastClickedView.setSelected(false);
                view.setSelected(true);
                loadWebViewData(intermediateArrayList.get(position).getModuleUrl());
                lastClickedView=view;
            }
        });
        webView = rootView.findViewById(R.id.webView);
        webView.setPadding(0, 0, 0, 0);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new MyWebChromeClient());
        loadWebViewData(beginnerModuleArrayList.get(0).getModuleUrl());
        return rootView;
    }


    void loadWebViewData(String url) {
        webView.loadData(url, "text/html", "utf-8");
    }
    void loadBeginnerModuleData(String title,String url){
        beginnerModuleArrayList.add(new Module(title, url));
       beginnerModuleAdapter.notifyDataSetChanged();
    }
    void loadIntermediateModuleData(String title,String url){
        intermediateArrayList.add(new Module(title, url));
        intermediateModuleAdapter.notifyDataSetChanged();
    }

    private class MyWebChromeClient extends WebChromeClient {

        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        public void onHideCustomView() {
            ((FrameLayout) getActivity().getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getActivity().getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            getActivity().setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
        }

        @Override
        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback) {
            if (this.mCustomView != null) {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getActivity().getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getActivity().getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout) getActivity().getWindow()
                    .getDecorView())
                    .addView(this.mCustomView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            getActivity().getWindow().getDecorView().setSystemUiVisibility(FULL_SCREEN_SETTING);
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
            this.mCustomView.setOnSystemUiVisibilityChangeListener(visibility -> updateControls());
        }

        @Override
        public Bitmap getDefaultVideoPoster() {
            return Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressBar.setProgress(newProgress);
            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);

            } else{
                progressBar.setVisibility(View.VISIBLE);
            }
        }

        void updateControls() {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) this.mCustomView.getLayoutParams();
            params.bottomMargin = 0;
            params.topMargin = 0;
            params.leftMargin = 0;
            params.rightMargin = 0;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            this.mCustomView.setLayoutParams(params);
            getActivity().getWindow().getDecorView().setSystemUiVisibility(FULL_SCREEN_SETTING);
        }
    }
}
