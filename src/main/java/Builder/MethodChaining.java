package Builder;

import lombok.Getter;
import lombok.ToString;

public class MethodChaining {

    public static void main(String[] args) {

        Student student1 = new Student();
        student1.setAddress("Scottsdale").setId("1").setName("Arun");
        Student student = new Student();
        student.setName("Pushpa").setId("2").setAddress("Scottsdale");
        System.out.println(student1);
        System.out.println(student);

        StudentReceiver studentReceiver = new StudentReceiver();
        Student student2 = studentReceiver.getStudent();

        //This may give inconsistent result
        System.out.println(student2);

    }
}

@Getter
@ToString
class Student {
    private String id;
    private String name;
    private String address;

    public Student setId(String id) {
        this.id = id;
        return this;

    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Student setAddress(String address) {
        this.address = address;
        return this;
    }
}

@Getter
class StudentReceiver {
    private final Student student = new Student();

    public StudentReceiver() {

        Thread t1 = new Thread(() -> student.setId("1").setAddress("Scottsdale").setName("Arun"));
        Thread t2 = new Thread(() -> student.setId("1").setAddress("Scottsdale").setName("Pushpa"));

        t1.start();
        t2.start();
    }
}

