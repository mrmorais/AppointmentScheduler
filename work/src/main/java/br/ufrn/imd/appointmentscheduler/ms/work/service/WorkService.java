package br.ufrn.imd.appointmentscheduler.ms.work.service;

import java.util.List;
import br.ufrn.imd.appointmentscheduler.ms.work.entity.Work;

public interface WorkService {
	void createNewWork(Work work);

    Work getWorkById(int workId);

    List<Work> getAllWorks();

    List<Work> getWorksByProviderId(int providerId);

    List<Work> getRetailCustomerWorks();

    List<Work> getCorporateCustomerWorks();

    List<Work> getWorksForRetailCustomerByProviderId(int providerId);

    List<Work> getWorksForCorporateCustomerByProviderId(int providerId);

    void updateWork(Work work);

    void deleteWorkById(int workId);

    boolean isWorkForCustomer(int workId, int customerId);
}
