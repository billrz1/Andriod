package com.oyoung.diary.diaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.oyoung.diary.R;

public class AddDiaryFragment extends Fragment implements View.OnClickListener {
    private AddDiaryController mController;
    private EditText edit_title;
    private EditText edit_desc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mController = new AddDiaryController(this);
    }

    private void initView(View view) {
        Button btn_confirm = view.findViewById(R.id.add_diary_confirm);
        btn_confirm.setOnClickListener(this);
        edit_title = view.findViewById(R.id.edit_add_title);
        edit_desc = view.findViewById(R.id.edit_add_desc);
        View edit_layout = view.findViewById(R.id.edit_layout);
        edit_layout.setOnClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_cancel, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (R.id.menu_cancel == item.getItemId()) {
            mController.closeWriteDiary(requireActivity().getSupportFragmentManager(), this);
            mController.setNavigationVisibility();
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_diary, container, false);
        initView(root);
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.add_diary_confirm == id) {
            mController.addDiaryToRepository(edit_title.getText().toString().trim(), edit_desc.getText().toString().trim());
            mController.setNavigationVisibility();
            mController.closeWriteDiary(requireActivity().getSupportFragmentManager(), this);
        } else {
            mController.changeFocus(edit_desc);
        }
    }
}
