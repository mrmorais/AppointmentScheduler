package br.ufrn.imd.appointmentscheduler.ms.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String roleName);
}
