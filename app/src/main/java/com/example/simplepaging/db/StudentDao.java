package com.example.simplepaging.db;

import java.util.List;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface StudentDao {

    @Query("SELECT * FROM Student ORDER BY name COLLATE NOCASE ASC")
    DataSource.Factory<Integer, Student> getAllStudent();

    @Insert
    void insert(List<Student> students);

    @Insert
    void insert(Student student);
}
