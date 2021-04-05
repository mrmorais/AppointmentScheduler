package br.ufrn.imd.appointmentscheduler.ms.user.entity.user.provider;

import br.ufrn.imd.appointmentscheduler.ms.user.entity.Work;
//import br.ufrn.imd.appointmentscheduler.ms.user.entity.Appointment;
//import br.ufrn.imd.appointmentscheduler.ms.user.entity.Work;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.WorkingPlan;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.Role;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.User;
import br.ufrn.imd.appointmentscheduler.ms.user.model.UserForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "providers")
@PrimaryKeyJoinColumn(name = "id_provider")
public class Provider extends User {

//    @OneToMany(mappedBy = "provider")
//    private List<Appointment> appointments;
//
    @ManyToMany
    @JoinTable(name = "works_providers", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_work"))
    private List<Work> works;

    @OneToOne(mappedBy = "provider", cascade = {CascadeType.ALL})
    private WorkingPlan workingPlan;

    public Provider() {
    }

    public Provider(UserForm userFormDTO, String encryptedPassword, Collection<Role> roles, WorkingPlan workingPlan) {
        super(userFormDTO, encryptedPassword, roles);
        this.workingPlan = workingPlan;
        workingPlan.setProvider(this);
//        this.works = userFormDTO.getWorks();
    }

    @Override
    public void update(UserForm updateData) {
        super.update(updateData);
//        this.works = updateData.getWorks();
    }

//    public List<Appointment> getAppointments() {
//        return appointments;
//    }
//
//    public void setAppointments(List<Appointment> appointments) {
//        this.appointments = appointments;
//    }
//
//    public List<Work> getWorks() {
//        return works;
//    }
//
//    public void setWorks(List<Work> works) {
//        this.works = works;
//    }

    public WorkingPlan getWorkingPlan() {
        return workingPlan;
    }

    public void setWorkingPlan(WorkingPlan workingPlan) {
        this.workingPlan = workingPlan;
    }

//    public List<Work> getCorporateWorks() {
//        List<Work> corporateWorks = new ArrayList<>();
//        for (Work w : works) {
//            if (w.getTargetCustomer().equals("corporate")) {
//                corporateWorks.add(w);
//            }
//        }
//        return corporateWorks;
//    }
//
//    public List<Work> getRetailWorks() {
//        List<Work> retailWorks = new ArrayList<>();
//        for (Work w : works) {
//            if (w.getTargetCustomer().equals("retail")) {
//                retailWorks.add(w);
//            }
//        }
//        return retailWorks;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Provider provider = (Provider) o;
        return provider.getId().equals(this.getId());
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(appointments, works, workingPlan);
//    }

}
