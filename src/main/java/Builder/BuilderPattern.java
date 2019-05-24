package Builder;

import lombok.Getter;
import lombok.ToString;

public class BuilderPattern {

    public static void main(String[] args) {
        Employee employee = Employee.Builder.newInstance().setId("1").setName("Arun").setAddress("Scottsdale").build();
        System.out.println(employee);

        EmployeeReceiver employeeReceiver = new EmployeeReceiver();
        Employee employee1 = employeeReceiver.getEmployee();
        System.out.println(employee1);
    }
}

@ToString
class Employee {
    private String id;
    private String name;
    private String address;

    public Employee(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.address = builder.address;
    }

    public static class Builder {
        private String id;
        private String name;
        private String address;

        private Builder() {
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}

@Getter
class EmployeeReceiver {

    private Employee employee;

    public EmployeeReceiver() {
        Thread t1 = new Thread(() -> employee = Employee.Builder.newInstance().setId("1").setName("Arun").setAddress("Scottsdale").build());
        Thread t2 = new Thread(() -> employee = Employee.Builder.newInstance().setId("2").setName("Pushpa").setAddress("Scottsdale").build());

        t1.start();
        t2.start();
    }


}
