package application;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employee")

public class Employee {

    private String name;
    private String department;
    private String extension;

    public Employee() {

        this("", "", "");
    }

    public Employee(String name, String department, String extension) {

        this.name = name;
        this.department = department;
        this.extension = extension;
    }

    public String getName() {
    	
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDepartment() {

        return department;
    }

    public void setDepartment(String department) {

        this.department = department;
    }

    public String getExtension() {

        return extension;
    }

    public void setExtension(String extension) {

        this.extension = extension;
    }
}
