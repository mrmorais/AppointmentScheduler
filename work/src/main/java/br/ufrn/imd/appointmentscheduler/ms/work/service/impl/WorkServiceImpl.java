package br.ufrn.imd.appointmentscheduler.ms.work.service.impl;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;


import br.ufrn.imd.appointmentscheduler.ms.work.dao.WorkRepository;
import br.ufrn.imd.appointmentscheduler.ms.work.entity.Work;
import br.ufrn.imd.appointmentscheduler.ms.work.exception.WorkNotFoundException;
import br.ufrn.imd.appointmentscheduler.ms.work.service.WorkService;

public class WorkServiceImpl implements WorkService {
	private final WorkRepository workRepository;
   

    public WorkServiceImpl(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Override
    public void createNewWork(Work work) {
        workRepository.save(work);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void updateWork(Work workUpdateData) {
        Work work = getWorkById(workUpdateData.getId());
        work.setName(workUpdateData.getName());
        work.setPrice(workUpdateData.getPrice());
        work.setDuration(workUpdateData.getDuration());
        work.setDescription(workUpdateData.getDescription());
        work.setEditable(workUpdateData.getEditable());
        work.setTargetCustomer(workUpdateData.getTargetCustomer());
        workRepository.save(work);
    }

    @Override
    public Work getWorkById(int workId) {
        return workRepository.findById(workId).orElseThrow(WorkNotFoundException::new);
    }

    @Override
    public List<Work> getAllWorks() {
        return workRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteWorkById(int workId) {
        workRepository.deleteById(workId);
    }

    @Override
    public boolean isWorkForCustomer(int workId, int customerId) {
        Work work = getWorkById(workId);
        return false;
        //TODO: API CALL
        /*Customer customer = userService.getCustomerById(customerId);
        if (customer.hasRole("ROLE_CUSTOMER_RETAIL") && !work.getTargetCustomer().equals("retail")) {
            return false;
        } else return !customer.hasRole("ROLE_CUSTOMER_CORPORATE") || work.getTargetCustomer().equals("corporate");*/
    }

    @Override
    public List<Work> getWorksByProviderId(int providerId) {
        return workRepository.findByProviderId(providerId);
    }

    @Override
    public List<Work> getRetailCustomerWorks() {
        return workRepository.findByTargetCustomer("retail");
    }

    @Override
    public List<Work> getCorporateCustomerWorks() {
        return workRepository.findByTargetCustomer("corporate");
    }

    @Override
    public List<Work> getWorksForRetailCustomerByProviderId(int providerId) {
        return workRepository.findByTargetCustomerAndProviderId("retail", providerId);
    }

    @Override
    public List<Work> getWorksForCorporateCustomerByProviderId(int providerId) {
        return workRepository.findByTargetCustomerAndProviderId("corporate", providerId);
    }

}
