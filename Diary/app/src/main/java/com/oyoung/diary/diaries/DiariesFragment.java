package com.oyoung.diary.diaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.oyoung.diary.R;

public class DiariesFragment extends Fragment {
    private DiariesController mController;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mController = new DiariesController(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_diaries, container, false);
        mController.setDiariesList(root.findViewById(R.id.diaries_list));
        mController.setNavigationSelected();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mController.loadDiaries();
    }
}
