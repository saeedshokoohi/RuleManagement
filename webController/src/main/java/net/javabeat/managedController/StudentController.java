package net.javabeat.managedController;

import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import rayten.GoodStudentRule;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Scope;
import java.io.Serializable;

/**
 * Created by saeed on 22/10/2015.
 */
@ManagedBean(name = "studentController")
@RequestScoped
@Named
public class StudentController implements Serializable {

    private Student student;
    private String result;


    @Autowired
    GoodStudentRule goodStudentRule;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    WebApplicationContext webApplicationContext;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Student getStudent() {
        if(student==null)student=new Student("","",0);
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void checkRule()
    {

        goodStudentRule.checkRule(getStudent());
    }

}
