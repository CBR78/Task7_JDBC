package ua.com.foxminded.malzam.university.dao;

import java.util.Set;

public interface BaseDao<T> {
    
    public void insert(T t);
    public Set<T> findAll();
}
