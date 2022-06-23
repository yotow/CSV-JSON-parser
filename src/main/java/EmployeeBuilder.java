public class EmployeeBuilder {
    public long id;
    public String firstName;
    public String lastName;
    public String country;
    public int age;

    public EmployeeBuilder id(long id) {
        this.id = id;
        return this;
    }

    public EmployeeBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeBuilder country(String country) {
        this.country = country;
        return this;
    }

    public EmployeeBuilder age(int age) {
        this.age = age;
        return this;
    }

    public Employee build(){
        return new Employee(this.id, this.firstName, this.lastName, this.country, this.age);
    }
}
