package com.sdgt.medcare.master.repository.lab;

 import com.sdgt.medcare.master.entity.lab.TestParameterMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TestParameterMapperRepository extends JpaRepository<TestParameterMapper,Long> {

    List<TestParameterMapper> findAllByTestMasterId(Long testMasterId);
    @Transactional
     public void deleteByTestMaster(Long testMaster);

}
