package br.ufrn.imd.appointmentscheduler.ms.notification.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufrn.imd.appointmentscheduler.ms.notification.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query("select N from Notification N where N.userId = :userId and N.isRead=false")
    List<Notification> getAllUnreadNotifications(@Param("userId") int userId);
    
    @Query("select N from Notification N where N.userId = :userId")
    List<Notification> getAllByUserId(@Param("userId") int userId);
}
