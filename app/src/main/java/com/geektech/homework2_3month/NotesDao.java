package com.geektech.homework2_3month;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert
    void createNotes(NoteModel noteModel);

    @Query("SELECT * FROM NoteModel")
    List<NoteModel> getList();

    @Delete
    void deleteNotes(NoteModel noteModel);

    @Update
    void updateNotes(NoteModel noteModel);
}
