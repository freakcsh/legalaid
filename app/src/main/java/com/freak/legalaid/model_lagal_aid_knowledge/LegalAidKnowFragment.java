package com.freak.legalaid.model_lagal_aid_knowledge;

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

public class LegalAidKnowFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View legal=inflater.inflate(R.layout.fragment_legal_aid_know,container,false);
        return legal;
    }
}
