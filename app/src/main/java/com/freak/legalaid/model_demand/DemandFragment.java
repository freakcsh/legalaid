package com.freak.legalaid.model_demand;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freak.legalaid.R;
/**
 * Created by Administrator on 2018/2/5.
 */

public class DemandFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View demand=inflater.inflate(R.layout.fragment_demand,container,false);
        return demand;
    }
}
