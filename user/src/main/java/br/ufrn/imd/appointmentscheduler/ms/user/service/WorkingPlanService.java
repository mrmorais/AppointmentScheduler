package br.ufrn.imd.appointmentscheduler.ms.user.service;

import br.ufrn.imd.appointmentscheduler.ms.user.entity.WorkingPlan;
import br.ufrn.imd.appointmentscheduler.ms.user.model.TimePeroid;

public interface WorkingPlanService {
    void updateWorkingPlan(WorkingPlan workingPlan);

    void addBreakToWorkingPlan(TimePeroid breakToAdd, int planId, String dayOfWeek);

    void deleteBreakFromWorkingPlan(TimePeroid breakToDelete, int planId, String dayOfWeek);

    WorkingPlan getWorkingPlanByProviderId(int providerId);
}
