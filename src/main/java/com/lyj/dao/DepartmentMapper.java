package com.lyj.dao;


import com.lyj.bean.Department;

public interface DepartmentMapper {
  public Department getDeptById(Integer id);
  public Department getDeptByIdPlus(Integer id);
  public Department getDeptStepToEmp(Integer id);
}
