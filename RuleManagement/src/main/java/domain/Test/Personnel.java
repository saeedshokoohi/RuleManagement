package domain.Test;

import domain.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by saeed on 12/12/2015.
 */
@Entity
@Table(name = "te_personnel", schema = "public", catalog = "rm_db")
public class Personnel extends BaseEntity {
    private String firstName;
    private String lastName;
    private int age;
    private boolean gender;
    private  String nationalNumber;


    @Basic
    @Column(name = "firstname", nullable = false, length = 500)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String ruleName) {
        this.firstName = ruleName;
    }

    @Basic
    @Column(name = "lastname", nullable = true, length = 200)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String releaseNumber) {
        this.lastName = releaseNumber;
    }

    @Column(name = "national_number", nullable = true, length = 200)
    public String getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }
    @Column(name = "age", nullable = true, length = 200)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "gender", nullable = true, length = 200)
    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }


    private List<OrgPosition> orgPositions;

    /*@OneToMany
    public List<OrgPosition> getOrgPositions() {
        return orgPositions;
    }

    public void setOrgPositions(List<OrgPosition> oneToMany) {
        this.orgPositions = oneToMany;
    }*/
}
