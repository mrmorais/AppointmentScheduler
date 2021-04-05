package br.ufrn.imd.appointmentscheduler.ms.user.entity.user.customer;

import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.Role;
import br.ufrn.imd.appointmentscheduler.ms.user.model.UserForm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "corporate_customers")
@PrimaryKeyJoinColumn(name = "id_customer")
public class CorporateCustomer extends Customer {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "vat_number")
    private String vatNumber;


    public CorporateCustomer() {
    }

    public CorporateCustomer(UserForm userFormDTO, String encryptedPassword, Collection<Role> roles) {
        super(userFormDTO, encryptedPassword, roles);
        this.companyName = userFormDTO.getCompanyName();
        this.vatNumber = userFormDTO.getVatNumber();
    }

    @Override
    public void update(UserForm updateData) {
        super.update(updateData);
        this.companyName = updateData.getCompanyName();
        this.vatNumber = updateData.getVatNumber();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

}
