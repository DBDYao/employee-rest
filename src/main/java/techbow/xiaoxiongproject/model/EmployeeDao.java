package techbow.xiaoxiongproject.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//这里Spring 底层会生成一个默认的class 如果你extend JpaRepository
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
