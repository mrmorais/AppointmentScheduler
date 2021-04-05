package br.ufrn.imd.appointmentscheduler.ms.user.entity.user.customer;

import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.Role;
import br.ufrn.imd.appointmentscheduler.ms.user.model.UserForm;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "retail_customers")
@PrimaryKeyJoinColumn(name = "id_customer")
public class RetailCustomer extends Customer {

    public RetailCustomer() {
    }

    public RetailCustomer(UserForm userFormDTO, String encryptedPassword, Collection<Role> roles) {
        super(userFormDTO, encryptedPassword, roles);
    }


}
