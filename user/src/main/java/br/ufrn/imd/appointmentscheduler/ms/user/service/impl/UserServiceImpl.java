package br.ufrn.imd.appointmentscheduler.ms.user.service.impl;

import br.ufrn.imd.appointmentscheduler.ms.user.dao.RoleRepository;
import br.ufrn.imd.appointmentscheduler.ms.user.dao.user.UserRepository;
import br.ufrn.imd.appointmentscheduler.ms.user.dao.user.customer.CorporateCustomerRepository;
import br.ufrn.imd.appointmentscheduler.ms.user.dao.user.customer.CustomerRepository;
import br.ufrn.imd.appointmentscheduler.ms.user.dao.user.customer.RetailCustomerRepository;
import br.ufrn.imd.appointmentscheduler.ms.user.dao.user.provider.ProviderRepository;
//import br.ufrn.imd.appointmentscheduler.ms.user.entity.Work;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.WorkingPlan;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.Role;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.User;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.customer.CorporateCustomer;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.customer.Customer;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.customer.RetailCustomer;
import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.provider.Provider;
import br.ufrn.imd.appointmentscheduler.ms.user.model.ChangePasswordForm;
import br.ufrn.imd.appointmentscheduler.ms.user.model.UserForm;
import br.ufrn.imd.appointmentscheduler.ms.user.service.UserService;

//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final ProviderRepository providerRepository;
    private final CustomerRepository customerRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final RetailCustomerRepository retailCustomerRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(ProviderRepository providerRepository, 
    		CustomerRepository customerRepository, 
    		CorporateCustomerRepository corporateCustomerRepository, 
    		RetailCustomerRepository retailCustomerRepository, 
    		UserRepository userRepository, 
    		RoleRepository roleRepository
//    		,PasswordEncoder passwordEncoder
    		) {
        this.providerRepository = providerRepository;
        this.customerRepository = customerRepository;
        this.corporateCustomerRepository = corporateCustomerRepository;
        this.retailCustomerRepository = retailCustomerRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    @PreAuthorize("#userId == principal.id")
//    public User getUserById(int userId) {
//        return userRepository.findById(userId)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//
//    @Override
//    @PreAuthorize("#customerId == principal.id or hasRole('ADMIN')")
//    public Customer getCustomerById(int customerId) {
//        return customerRepository.getOne(customerId);
//    }
//
//    @Override
//    public Provider getProviderById(int providerId) {
//        return providerRepository.findById(providerId)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
//    }
//
//    @Override
//    @PreAuthorize("#retailCustomerId == principal.id or hasRole('ADMIN')")
//    public RetailCustomer getRetailCustomerById(int retailCustomerId) {
//        return retailCustomerRepository.findById(retailCustomerId)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
//
//    }
//
//    @Override
//    @PreAuthorize("#corporateCustomerId == principal.id or hasRole('ADMIN')")
//    public CorporateCustomer getCorporateCustomerById(int corporateCustomerId) {
//        return corporateCustomerRepository.findById(corporateCustomerId)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
//    }
//
//    @Override
//    @PreAuthorize("hasRole('ADMIN')")
//    public List<Provider> getAllProviders() {
//        return providerRepository.findAll();
//    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<RetailCustomer> getAllRetailCustomers() {
        return retailCustomerRepository.findAll();
    }


//    @Override
//    public User getUserByUsername(String userName) {
//        return userRepository.findByUserName(userName)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
//    }

    @Override
    public List<User> getUsersByRoleName(String roleName) {
        return userRepository.findByRoleName(roleName);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    @Override
//    @PreAuthorize("hasRole('ADMIN')")
//    public void deleteUserById(int userId) {
//        userRepository.deleteById(userId);
//    }

    @Override
    public List<Provider> getProvidersWithRetailWorks() {
        return providerRepository.findAllWithRetailWorks();
    }

    @Override
    public List<Provider> getProvidersWithCorporateWorks() {
        return providerRepository.findAllWithCorporateWorks();
    }

//    @Override
//    public List<Provider> getProvidersByWork(Work work) {
//        return providerRepository.findByWorks(work);
//    }

//    @Override
//    @PreAuthorize("#passwordChangeForm.id == principal.id")
//    public void updateUserPassword(ChangePasswordForm passwordChangeForm) {
//        User user = userRepository.getOne(passwordChangeForm.getId());
//        user.setPassword(passwordEncoder.encode(passwordChangeForm.getPassword()));
//        userRepository.save(user);
//    }

//    @Override
//    @PreAuthorize("#updateData.id == principal.id or hasRole('ADMIN')")
//    public void updateProviderProfile(UserForm updateData) {
//        Provider provider = providerRepository.getOne(updateData.getId());
//        provider.update(updateData);
//        providerRepository.save(provider);
//    }

//    @Override
//    @PreAuthorize("#updateData.id == principal.id or hasRole('ADMIN')")
//    public void updateRetailCustomerProfile(UserForm updateData) {
//        RetailCustomer retailCustomer = retailCustomerRepository.getOne(updateData.getId());
//        retailCustomer.update(updateData);
//        retailCustomerRepository.save(retailCustomer);
//
//    }

//    @Override
//    @PreAuthorize("#updateData.id == principal.id or hasRole('ADMIN')")
//    public void updateCorporateCustomerProfile(UserForm updateData) {
//        CorporateCustomer corporateCustomer = corporateCustomerRepository.getOne(updateData.getId());
//        corporateCustomer.update(updateData);
//        corporateCustomerRepository.save(corporateCustomer);
//
//    }
//
//    @Override
//    public void saveNewRetailCustomer(UserForm userForm) {
//        RetailCustomer retailCustomer = new RetailCustomer(userForm, passwordEncoder.encode(userForm.getPassword()), getRolesForRetailCustomer());
//        retailCustomerRepository.save(retailCustomer);
//    }
//
//    @Override
//    public void saveNewCorporateCustomer(UserForm userForm) {
//        CorporateCustomer corporateCustomer = new CorporateCustomer(userForm, passwordEncoder.encode(userForm.getPassword()), getRoleForCorporateCustomers());
//        corporateCustomerRepository.save(corporateCustomer);
//    }
//
//    @Override
//    public void saveNewProvider(UserForm userForm) {
//        WorkingPlan workingPlan = WorkingPlan.generateDefaultWorkingPlan();
//        Provider provider = new Provider(userForm, passwordEncoder.encode(userForm.getPassword()), getRolesForProvider(), workingPlan);
//        providerRepository.save(provider);
//    }

    @Override
    public Collection<Role> getRolesForRetailCustomer() {
        HashSet<Role> roles = new HashSet();
        roles.add(roleRepository.findByName("ROLE_CUSTOMER_RETAIL"));
        roles.add(roleRepository.findByName("ROLE_CUSTOMER"));
        return roles;
    }


    @Override
    public Collection<Role> getRoleForCorporateCustomers() {
        HashSet<Role> roles = new HashSet();
        roles.add(roleRepository.findByName("ROLE_CUSTOMER_CORPORATE"));
        roles.add(roleRepository.findByName("ROLE_CUSTOMER"));
        return roles;
    }

    @Override
    public Collection<Role> getRolesForProvider() {
        HashSet<Role> roles = new HashSet();
        roles.add(roleRepository.findByName("ROLE_PROVIDER"));
        return roles;
    }

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserPassword(ChangePasswordForm passwordChangeForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Provider getProviderById(int providerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Provider> getAllProviders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNewProvider(UserForm userForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProviderProfile(UserForm updateData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RetailCustomer getRetailCustomerById(int retailCustomerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNewRetailCustomer(UserForm userForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRetailCustomerProfile(UserForm updateData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CorporateCustomer getCorporateCustomerById(int corporateCustomerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNewCorporateCustomer(UserForm userForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCorporateCustomerProfile(UserForm updateData) {
		// TODO Auto-generated method stub
		
	}


}

