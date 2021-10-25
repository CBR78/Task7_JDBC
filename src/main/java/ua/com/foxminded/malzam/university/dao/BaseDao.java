package ua.com.foxminded.malzam.university.dao;

import java.util.Set;

public interface BaseDao<T> {

    void insert(T t);
    Set<T> findAll();
}
