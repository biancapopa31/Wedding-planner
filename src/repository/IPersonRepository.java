package repository;

import java.util.List;

public interface IPersonRepository <T> {

    T getById(int id);

    List<T> getAll();

    int add(T person);

    void update(T person);

    void delete(T person);

}
