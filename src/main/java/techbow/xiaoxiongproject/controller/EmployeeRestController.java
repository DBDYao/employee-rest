package techbow.xiaoxiongproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import techbow.xiaoxiongproject.model.Cache;
import techbow.xiaoxiongproject.model.Employee;
import techbow.xiaoxiongproject.model.EmployeeDao;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeRestController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private Cache cache;

    @RequestMapping(path = "/employee", method = RequestMethod.POST)
    public Employee createEmployee(@RequestBody Employee input) {
         Employee res = employeeDao.save(input);
         cache.put(res.getId(), res);
         return res;
    }

    @RequestMapping(path = "/employee/{empId}", method = RequestMethod.GET)
    public Object getEmployeeById(@PathVariable Long empId) {
        Object cacheEmp = cache.get(empId);
        if(cacheEmp != null) return cacheEmp;

        Object emp = employeeDao.findById(empId);
        if(emp != null) {
            cache.put(empId, emp);
        }
        return emp;
    }

    @RequestMapping(path = "employee/{empId}", method = RequestMethod.PUT)
    public Employee putEmployeeById(@PathVariable Long empId, @RequestBody Employee input) {

        Employee emp = employeeDao.findById(empId).get();

        if(emp == null) {
            throw new IllegalArgumentException("id is not valid");
        }
        emp.setLastName(input.getLastName());
        emp.setFirstName(input.getFirstName());
        emp.setAge(input.getAge());

        employeeDao.save(emp);
        cache.put(empId, emp);
        return emp;
    }

}
