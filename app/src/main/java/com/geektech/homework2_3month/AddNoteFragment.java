package com.geektech.homework2_3month;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddNoteFragment extends Fragment {

    private EditText etTitle, etDescription;
    private Button btnSave;
    String date = new SimpleDateFormat("dd.MM.yyyy   HH:mm", Locale.getDefault()).format(new Date());


    public AddNoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);
        etTitle = view.findViewById(R.id.et_title);
        etDescription = view.findViewById(R.id.et_description);
        btnSave = view.findViewById(R.id.btn_save);

        btnSave.setOnClickListener(view1 -> {
            String title = etTitle.getText().toString();
            String description = etDescription.getText().toString();
            NoteModel noteModel = new NoteModel(title, description, date);
            App.appDataBase.notesDao().createNotes(noteModel);
            requireActivity().getSupportFragmentManager().popBackStack();
        });
        checkIsEdit();
        return view;
    }

    private void checkIsEdit() {
        if (getArguments() != null) {
            NoteModel noteModel = (NoteModel) requireArguments().getSerializable("model");
            etTitle.setText(noteModel.getTitle());
            etDescription.setText(noteModel.getDescription());
            btnSave.setOnClickListener(view -> {
                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();
                noteModel.setTitle(title);
                noteModel.setDescription(description);
                noteModel.setDate(date);
                App.appDataBase.notesDao().updateNotes(noteModel);
                requireActivity().getSupportFragmentManager().popBackStack();
            });
        }
    }
}