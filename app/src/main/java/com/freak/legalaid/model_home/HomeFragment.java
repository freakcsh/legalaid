package com.freak.legalaid.model_home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freak.legalaid.R;
import com.freak.legalaid.library.base.BaseFragment;
import com.freak.legalaid.library.base.BasePresenter;
import com.freak.legalaid.library.base.SimpleFragment;

/**
 * Created by Administrator on 2018/2/5.
 */

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View home=inflater.inflate(R.layout.fragment_home,container,false);
        return home;
    }
}
