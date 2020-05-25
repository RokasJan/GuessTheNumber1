package com.example.guessthenumber;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "difficulty LIKE :last LIMIT 1")
//    User findByName(String first, String last);

    @Query("DELETE FROM user")
    void deleteAll();

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
