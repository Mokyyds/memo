package com.sealiu.memo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by root
 * on 6/18/16.
 */
public class StatisticFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.fragment_toolbar);
        toolbar.setTitle(R.string.statistic_toolbar_title);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        return view;
    }
}
