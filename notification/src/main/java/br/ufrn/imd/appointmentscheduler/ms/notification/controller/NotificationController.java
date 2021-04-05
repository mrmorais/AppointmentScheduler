package br.ufrn.imd.appointmentscheduler.ms.notification.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.appointmentscheduler.ms.notification.bodyparams.NewNotification;
import br.ufrn.imd.appointmentscheduler.ms.notification.entity.Notification;
//import com.example.slabiak.appointmentscheduler.security.CustomUserDetails;
import br.ufrn.imd.appointmentscheduler.ms.notification.service.NotificationService;
//import br.ufrn.imd.appointmentscheduler.ms.notification.UserService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    @PostMapping
    @ResponseBody
    public ResponseEntity<NewNotification> createNewNotification(@RequestBody NewNotification requestBody) {
    	notificationService.newNotification(
    			requestBody.getTitle(),
    			requestBody.getMessage(),
    			requestBody.getUrl(),
    			requestBody.getUserId());
    	return new ResponseEntity<>(requestBody, HttpStatus.OK);
    }
    
    @GetMapping("/user/{userId}/notification/{notificationId}")
    public ResponseEntity<Notification> getNotificationById(
    		@PathVariable("notificationId") int notificationId,
    		@PathVariable("userId") int userId) {
    	Notification notification = notificationService.getNotificationByIdAndUser(notificationId, userId);
    	notificationService.markAsRead(notificationId, userId);
    	
    	return new ResponseEntity<Notification>(notification, notification == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getAll(
    		@PathVariable("userId") int userId) {
    	List<Notification> notifications = notificationService.getAll(userId);
    	return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
    
    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications(
    		@PathVariable("userId") int userId) {
    	List<Notification> unreadNotifications = notificationService.getUnreadNotifications(userId);
    	return new ResponseEntity<>(unreadNotifications, HttpStatus.OK);
    }
    
    @PostMapping("/user/{userId}/notification/{notificationId}/markAsRead")
    public ResponseEntity<SuccessWrapper> markAsRead(
    		@PathVariable("notificationId") int notificationId,
    		@PathVariable("userId") int userId) {
    	notificationService.markAsRead(notificationId, userId);
    	
    	return new ResponseEntity<SuccessWrapper>(new SuccessWrapper(), HttpStatus.OK);
    }
    
    @PostMapping("/user/{userId}/markAllAsRead")
    public ResponseEntity<SuccessWrapper> markAllAsRead(
    		@PathVariable("userId") int userId) {
    	notificationService.markAllAsRead(userId);
    	return new ResponseEntity<>(new SuccessWrapper(), HttpStatus.OK);
    }
}
