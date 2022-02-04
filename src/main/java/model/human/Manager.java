package model.human;

public class Manager extends User {
    private Double salary;

    public Manager(Integer id, String firstName, String lastname, String userName, String password, String email, Double salary) {
        super(id, firstName, lastname, userName, password, email);
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
