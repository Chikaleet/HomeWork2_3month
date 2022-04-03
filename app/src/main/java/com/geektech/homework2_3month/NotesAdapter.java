package com.geektech.homework2_3month;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<NoteModel> notes = new ArrayList<>();
    private final Transaction transaction;

    public NotesAdapter(Transaction transaction) {
        this.transaction = transaction;
    }



    public void setNotesList(List<NoteModel> list) {
        this.notes = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder,  int position) {
        holder.txtTitle.setText(notes.get(position).getTitle());
        holder.txtDescription.setText(notes.get(position).getDescription());
        holder.txtDate.setText(notes.get(position).getDate());

        holder.itemView.setOnClickListener(view -> transaction.translate(notes.get(position)));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    public static class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDescription, txtDate;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDescription = itemView.findViewById(R.id.txt_description);
            txtDate = itemView.findViewById(R.id.txt_date);
        }
    }


    public interface Transaction {
        void translate(NoteModel noteModel);
    }
}
