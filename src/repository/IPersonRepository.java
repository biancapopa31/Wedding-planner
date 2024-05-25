package repository;


public interface IPersonRepository <T> {

    T getById(int id);

    void add(T person);

    void update(T person);

}
