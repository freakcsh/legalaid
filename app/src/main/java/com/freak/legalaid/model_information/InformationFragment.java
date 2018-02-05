package com.freak.legalaid.model_information;

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

public class InformationFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View information=inflater.inflate(R.layout.fragment_information,container,false);
        return information;
    }
}
