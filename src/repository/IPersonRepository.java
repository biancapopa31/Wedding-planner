package repository;

import model.Person;

public interface IPersonRepository {

    Person getPersonById(int id);

    void addPerson(Person person);

    void updatePerson(Person person);

}
