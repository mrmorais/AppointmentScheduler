package br.ufrn.imd.appointmentscheduler.ms.notification.controller;

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
    
    @GetMapping("/user/{userId}/not/{notificationId}")
    public ResponseEntity<Notification> getNotificationById(
    		@PathVariable("notificationId") int notificationId,
    		@PathVariable("userId") int userId) {
    	Notification notification = notificationService.getNotificationById(notificationId);
    	notificationService.markAsRead(notificationId, userId);
    	
    	return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }
    

//    @GetMapping()
//    public String showUserNotificationList(Model model, int userId) {
//        model.addAttribute("notifications", notificationService.getAll(userId));
//        return "notifications/listNotifications";
//    }
//
//    @GetMapping("/{notificationId}")
//    public String showNotification(@PathVariable("notificationId") int notificationId, int userId) {
//        Notification notification = notificationService.getNotificationById(notificationId);
//        notificationService.markAsRead(notificationId, userId);
//        return "redirect:" + notification.getUrl();
//    }
//
//    @PostMapping("/markAllAsRead")
//    public String processMarkAllAsRead(int userId) {
//        notificationService.markAllAsRead(userId);
//        return "redirect:/notifications";
//    }
}
