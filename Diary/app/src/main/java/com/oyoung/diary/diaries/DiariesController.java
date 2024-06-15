package com.oyoung.diary.diaries;

import android.app.AlertDialog;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.oyoung.diary.R;
import com.oyoung.diary.application.YyApplication;
import com.oyoung.diary.model.Diary;
import com.oyoung.diary.model.DiaryHelper;
import com.oyoung.diary.view.InputDialog;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DiariesController {
    private final Fragment mView;
    private DiariesAdapter mListAdapter;
    private final DiaryHelper diaryHelper;
    public DiariesController(@NonNull DiariesFragment diariesFragment) {
        diaryHelper = DiaryHelper.getInstance(YyApplication.get());
        mView = diariesFragment;
        mView.setHasOptionsMenu(true);
        initAdapter();
    }

    private void initAdapter() {
        mListAdapter = new DiariesAdapter(new ArrayList<>());
        mListAdapter.setOnLongClickListener((v, data) -> {
            showDeleteDialog(data);
            return false;
        });
        mListAdapter.setOnClickListener((v, data) -> showInputDialog(data));
    }

    private void showDeleteDialog(Diary data) {
        new AlertDialog.Builder(mView.getContext()).setMessage(YyApplication.get().getString(R.string.dialog_delete) + data.getTitle())
                .setPositiveButton(YyApplication.get().getString(R.string.ok),
                        (dialogInterface, i) -> {
                            diaryHelper.delete(data.getId());
                            loadDiaries();
                        })
                .setNegativeButton(YyApplication.get().getString(R.string.cancel), null).show();
    }

    private void showInputDialog(final Diary data) {
        final InputDialog inputDialog = new InputDialog(mView.requireContext(), data.getTitle(), data.getDescription());
        inputDialog.setOnCommitClickListener(v -> {
            diaryHelper.modify(data.getId(), inputDialog.getTitle(), inputDialog.getContent());
            loadDiaries();
        }).show();
    }

    public void setDiariesList(RecyclerView recycleView) {
        recycleView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        recycleView.setAdapter(mListAdapter);
        recycleView.addItemDecoration(new DividerItemDecoration(mView.requireContext(), DividerItemDecoration.VERTICAL));
        recycleView.setItemAnimator(new DefaultItemAnimator());
    }

    public void setNavigationSelected() {
        BottomNavigationView navigation_bottom = Objects.requireNonNull(mView.requireActivity()).findViewById(R.id.navigation_bottom);
        navigation_bottom.getMenu().getItem(0).setChecked(true);
    }

    public void loadDiaries() {
        processDiaries(diaryHelper.query());
    }

    private void processDiaries(List<Diary> diaryList) {
        mListAdapter.update(diaryList);
    }

}
