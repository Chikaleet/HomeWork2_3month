package com.geektech.homework2_3month;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements NotesAdapter.Transaction {

    private RecyclerView rvNotes;
    private NotesAdapter adapter;
    private FloatingActionButton btnOpenAddNoteFragment;


    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        rvNotes = view.findViewById(R.id.rv_notes);
        btnOpenAddNoteFragment = view.findViewById(R.id.btm_open_add_note);
        rvNotes.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new NotesAdapter(this);
        rvNotes.setAdapter(adapter);


        btnOpenAddNoteFragment.setOnClickListener(view1 -> {
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new AddNoteFragment());
            transaction.addToBackStack("AddNoteFragment");
            transaction.commit();
        });

        listenGetDate();
        return view;
    }


    private void listenGetDate() {
        adapter.setNotesList(App.appDataBase.notesDao().getList());
    }

    @Override
    public void translate(NoteModel noteModel) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", noteModel);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, AddNoteFragment.class, bundle)
                .addToBackStack("Lol")
                .commit();
    }
}