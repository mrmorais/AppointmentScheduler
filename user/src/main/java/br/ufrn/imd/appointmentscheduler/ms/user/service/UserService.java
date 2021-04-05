package br.ufrn.imd.appointmentscheduler.ms.user.service;

import br.ufrn.imd.appointmentscheduler.ms.user.entity.Work;
//import br.ufrn.imd.appointmentscheduler.ms.user.entity.Work;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.Role;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.User;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.customer.CorporateCustomer;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.customer.Customer;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.customer.RetailCustomer;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.provider.Provider;
import br.ufrn.imd.appointmentscheduler.ms.user.model.ChangePasswordForm;
import br.ufrn.imd.appointmentscheduler.ms.user.model.UserForm;

import java.util.Collection;
import java.util.List;

public interface UserService {
    /*
     * User
     * */
    User getUserById(int userId);

    User getUserByUsername(String userName);

    List<User> getUsersByRoleName(String roleName);

    List<User> getAllUsers();

    void deleteUserById(int userId);

    void updateUserPassword(ChangePasswordForm passwordChangeForm);

    /*
     * Provider
     * */
    Provider getProviderById(int providerId);

    List<Provider> getProvidersWithRetailWorks();

    List<Provider> getProvidersWithCorporateWorks();

    List<Provider> getProvidersByWork(Work work);

    List<Provider> getAllProviders();

    void saveNewProvider(UserForm userForm);

    void updateProviderProfile(UserForm updateData);

    Collection<Role> getRolesForProvider();

    /*
     * Customer
     * */
    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();

    /*
     * RetailCustomer
     * */
    RetailCustomer getRetailCustomerById(int retailCustomerId);

    void saveNewRetailCustomer(UserForm userForm);

    void updateRetailCustomerProfile(UserForm updateData);

    Collection<Role> getRolesForRetailCustomer();

    /*
     * CorporateCustomer
     * */
    CorporateCustomer getCorporateCustomerById(int corporateCustomerId);

    List<RetailCustomer> getAllRetailCustomers();

    void saveNewCorporateCustomer(UserForm userForm);

    void updateCorporateCustomerProfile(UserForm updateData);

    Collection<Role> getRoleForCorporateCustomers();


}

