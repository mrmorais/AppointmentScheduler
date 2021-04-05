package br.ufrn.imd.appointmentscheduler.ms.user.dao.user.provider;

import br.ufrn.imd.appointmentscheduler.ms.user.dao.user.CommonUserRepository;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.Work;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.provider.Provider;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProviderRepository extends CommonUserRepository<Provider> {

    List<Provider> findByWorks(Work work);

    @Query("select distinct p from Provider p inner join p.works w where w.targetCustomer = 'retail'")
    List<Provider> findAllWithRetailWorks();

    @Query("select distinct p from Provider p inner join p.works w where w.targetCustomer = 'corporate'")
    List<Provider> findAllWithCorporateWorks();
}
