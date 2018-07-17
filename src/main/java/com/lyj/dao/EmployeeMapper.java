package com.lyj.dao;

import com.lyj.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    //返回一条记录，key通过注解设置，值就是封装的javaBean；
    @MapKey("last_name")
    public Map<String,Employee> getEmpByLastNameLikeReturnMap(String lastName);
    //返回一条记录的map，key就是列名，值就是对应的值
    public Map<String,Object> getEmpByIdReturnMap(Integer id);
    public Employee getEmpById(Integer id);
    public void  addEmp(Employee employee);
    public boolean updateEmp(Employee employee);
    public void deleteEmpById(Integer id);
    public Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);
    public Employee getEmpByMap(Map<String,Object> map);
    public List<Employee> getEmpByLastNameLike(String lastName);





}