package com.reference.apublic.coordinatorlayout;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HideScrollListener {

    private RecyclerView recyclerview;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //		recyclerview.setOnScrollListener(null);
        //		recyclerview.addOnScrollListener(new FabScrollListener(this));

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            list.add("Item" + i);
        }
        Adapter adapter = new FabRecyclerAdapter(list);
        recyclerview.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(MainActivity.this, fab, "shareView");
                startActivity(new Intent(MainActivity.this, MainActivity2.class),
                        optionsCompat.toBundle());

            }
        });

        findViewById(R.id.title_iv_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = ((ImageView) v).getDrawable();
                if(drawable instanceof Animatable){
                    ((Animatable) drawable).start();
                }
            }
        });
    }

    @Override
    public void onHide() {
        // ���ض���--���Զ���
        toolbar.animate().translationY(-toolbar.getHeight())
                .setInterpolator(new AccelerateInterpolator(3));
        LayoutParams layoutParams = (LayoutParams) fab.getLayoutParams();

        fab.animate().translationY(fab.getHeight() + layoutParams.bottomMargin)
                .setInterpolator(new AccelerateInterpolator(3));
    }

    @Override
    public void onShow() {
        // ��ʾ����--���Զ���
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

        LayoutParams layoutParams = (LayoutParams) fab.getLayoutParams();
        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

    }

}
