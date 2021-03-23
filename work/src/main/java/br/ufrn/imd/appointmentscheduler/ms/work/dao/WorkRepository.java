package br.ufrn.imd.appointmentscheduler.ms.work.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufrn.imd.appointmentscheduler.ms.work.entity.Work;

public interface WorkRepository extends JpaRepository<Work, Integer> {
	
	@Query("select w from Work where w.providerId= :providerId")
    List<Work> findByProviderId(@Param("providerId") int id);

    @Query("select w from Work w where w.targetCustomer = :target ")
    List<Work> findByTargetCustomer(@Param("target") String targetCustomer);

    @Query("select w from Work where w.providerId in :providerId and w.targetCustomer = :target ")
    List<Work> findByTargetCustomerAndProviderId(@Param("target") String targetCustomer, @Param("providerId") int providerId);

}
