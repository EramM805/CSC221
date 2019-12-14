package application;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "EmployeeList")


public class EmployeeList {

    private List<Employee> lst = new ArrayList<>();

    public List<Employee> getLst() {

        return lst;
    }

    public void createNew() {

        lst.add(new Employee());
    }

    public void setLst(List<Employee> lst) {

        this.lst = lst;
    }
}