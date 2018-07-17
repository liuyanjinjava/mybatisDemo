package com.lyj.dao;

import com.lyj.bean.Employee;

public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);
    public Employee getEmpAndDept(Integer id);
    public Employee getEmpByIdStep(Integer id);
    public Employee getEmpByDeptId(Integer deptID);
}
