import com.lyj.bean.Department;
import com.lyj.bean.Employee;
import com.lyj.dao.DepartmentMapper;
import com.lyj.dao.EmployeeMapper;
import com.lyj.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest{
    /*
     *流程第一步先根据xml配置文件（全局配置文件）创建一个sqlSessionFactory对象；
     *sqlSessionFactory.openSession()是属于自动提交的；
     *
     */
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "conf/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }


    @Test
    public void test02() throws IOException {
        //得到一个sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //获取一次会话；
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //获取接口的实现类对象;会为接口自动的创造一个代理对象
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }


    }
    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
        //获取到的session是不会自动提交的。。。。
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            //测试添加
            Employee employee = new Employee(null, "jerry", "1", null);
            mapper.addEmp(employee);
            System.out.println(employee.getId());
            //测试修改
           /* Employee employee = new Employee(1, "Lucy", "0", "163@nima");
           //mybatis对于基本数据的映射，只需要在java方法里写返回值就ok；
            boolean b=mapper.updateEmp(employee);
            System.out.println(b);*/
            //测试删除
           // mapper.deleteEmpById(3);
            //3,手动提交
            sqlSession.commit();

        }finally {
            sqlSession.close();

        }
    }
    @Test
    public void test04() throws IOException {
        SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
        //获取到的session是不会自动提交的。。。。
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try {
           EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//          //测试多参数的方法
//
//            Employee employee=mapper.getEmpByIdAndLastName(1,"Lucy");
         /*   Map<String,Object> map=new HashMap<>();
            map.put("id",1);
            map.put("lastName","Lucy");
            map.put("tableName","tbl_employee");
            Employee employee = mapper.getEmpByMap(map);
            System.out.println(employee);*/
         //进行模糊查询返回集合
//            List<Employee> like = mapper.getEmpByLastNameLike("%y");
//            for (Employee e:like
//                 ) {
//                System.out.println(e);

//
// 通过ResultType的作用返回一条记录Map<String, Object>，key就是列名，值就是对应列的值
//            Map<String, Object> empByIdReturnMap = mapper.getEmpByIdReturnMap(1);
//            System.out.println(empByIdReturnMap);
// 通过ResultType的作用返回一个Map<Integer,Pojo>,pojo为具体的实体 key通过注解设置；
//            Map<Integer, Employee> empByLastNameLikeReturnMap = mapper.getEmpByLastNameLikeReturnMap("%y");
//            System.out.println(empByLastNameLikeReturnMap);
            Map<String, Employee> empByLastNameLikeReturnMap = mapper.getEmpByLastNameLikeReturnMap("%y");
            System.out.println(empByLastNameLikeReturnMap);


        }finally {
            sqlSession.close();

        }
    }
    @Test
    public void test05() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
        EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
//        Employee employee = mapper.getEmpById(1);
//            System.out.println(employee);
//            Employee empAndDept = mapper.getEmpAndDept(1);
//            System.out.println(empAndDept);
//            System.out.println(empAndDept.getDepartment());
            //分布查询对应的员工
            Employee emp = mapper.getEmpByIdStep(2);
            System.out.println(emp);
            //不理解为什么使用鉴别器之后还是输出了其他值；
            System.out.println(emp.getDepartment());
        }
        finally {
            sqlSession.close();
        }

    }
    @Test
    public void test06() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
           DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
//            Department dept = mapper.getDeptByIdPlus(1);
//            System.out.println(dept);
//            List<Employee> emps = dept.getEmps();
//            for (Employee e:
//                 emps) {
//                System.out.println(e);}
            //分步查询出来的结果
            Department deptStepToEmp = mapper.getDeptStepToEmp(1);
            System.out.println(deptStepToEmp);
            System.out.println(deptStepToEmp.getEmps());




        }
        finally {
            sqlSession.close();
        }
    }
    /**
     *插件原理；
     * 在四大对象创建的时候，
     * 1.每个创建出来的对象不是直接返回的，而是interceptorChain.pluginAll()
     *
     */
    /**
     * 1.编写Interceptor的实现类；
     * 2.使用@Intercepts完成插件签名；
     * 3.将写好的插件注册在全局配置文件中；
     */
}
