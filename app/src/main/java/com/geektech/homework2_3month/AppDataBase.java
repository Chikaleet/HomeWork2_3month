package com.geektech.homework2_3month;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {NoteModel.class}, version = 1)
abstract public class AppDataBase extends RoomDatabase {
    public abstract NotesDao notesDao();
}
