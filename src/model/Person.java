package model;
public class Person {
    private String lastName;
    private String firstName;
    private String telephone;

    private int tableNumber = -1;
    

    public Person(String lastName, String firstName, String telephone) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.telephone = telephone;
    }

    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Person(Person person) {
        this.lastName = person.lastName;
        this.firstName = person.firstName;
        this.telephone = person.telephone;
    }

    public Person() {
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }


    @Override
    public String toString() {
        return lastName + ", " + firstName + "\nTel: " + telephone;
    }
    
}
