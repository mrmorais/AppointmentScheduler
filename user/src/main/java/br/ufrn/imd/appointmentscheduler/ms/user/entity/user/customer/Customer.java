package br.ufrn.imd.appointmentscheduler.ms.user.entity.user.customer;

//import br.ufrn.imd.appointmentscheduler.ms.user.entity.Appointment;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.Role;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.User;
import br.ufrn.imd.appointmentscheduler.ms.user.model.UserForm;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "id_customer")
public class Customer extends User {

//    @OneToMany(mappedBy = "customer")
//    private List<Appointment> appointments;

    public Customer() {
    }

    public Customer(UserForm userFormDTO, String encryptedPassword, Collection<Role> roles) {
        super(userFormDTO, encryptedPassword, roles);
    }


    public String getType() {
        if (super.hasRole("ROLE_CUSTOMER_CORPORATE")) {
            return "corporate";
        } else if (super.hasRole("ROLE_CUSTOMER_RETAIL")) {
            return "retail";
        }
        return "";
    }

//    public List<Appointment> getAppointments() {
//        return appointments;
//    }
//
//    public void setAppointments(List<Appointment> appointments) {
//        this.appointments = appointments;
//    }
}
