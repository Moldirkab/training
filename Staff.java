package models;

public class Staff {
    private int id;
    private String name;
    private String surname;
    private int salary;
    private String password;

    public Staff(String name, String surname, int salary) {
       setName(name);
       setSurname(surname);
       setSalary(salary);
    }

    public Staff(int id, String name, String surname, int salary) {
        setId(id);
        setName(name);
        setSurname(surname);
        setSalary(salary);
    }
    public Staff(String name, String surname, int salary, String password) {
        this(name, surname, salary);
        setPassword(password);
    }
    public Staff(int id, String name, String surname, int salary, String password) {
        this(id, name, surname, salary);
        setPassword(password);
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }


    @Override
    public String toString() {
        return "id:" + id + ", name: '" + name + "', surname: '" + surname + "', salary: " + salary ;
    }
}
