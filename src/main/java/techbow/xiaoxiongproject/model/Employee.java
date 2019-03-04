package techbow.xiaoxiongproject.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String LastName;

    @NotNull
    @Min(1)
    @Max(100)
    private int age;

    public int getAge(){
        return age;
    }
}
