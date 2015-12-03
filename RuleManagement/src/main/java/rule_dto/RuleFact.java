package rule_dto;

import entities.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saeed on 03/12/2015.
 */
public class   RuleFact {
    private List<FactObject> factObjects;

    public List<FactObject> getFactObjects() {
        if(factObjects==null)factObjects=new ArrayList<FactObject>();
        return factObjects;
    }

    public void setFactObjects(List<FactObject> factObjects) {
        this.factObjects = factObjects;
    }

    public void insertBObject(Object bo) {
        FactObject factObject=new FactObject();
        factObject.setBObject(bo);
        getFactObjects().add(factObject);
    }
}
