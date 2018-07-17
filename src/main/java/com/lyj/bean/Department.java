package com.lyj.bean;

import java.util.List;

public class Department {
    private Integer id;
    private String deptment;
    private List<Employee> emps;

    public Department() {
        super();
    }

    public Department(Integer id, String deptment) {

        this.id = id;
        this.deptment = deptment;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    public String getDeptment() {
        return deptment;
    }

    public void setDeptment(String deptment) {
        this.deptment = deptment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptment='" + deptment +
                '}';
    }
}
