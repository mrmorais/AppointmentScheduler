package br.ufrn.imd.appointmentscheduler.ms.notification.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufrn.imd.appointmentscheduler.ms.notification.entity.Notification;
//import com.example.slabiak.appointmentscheduler.security.CustomUserDetails;
import br.ufrn.imd.appointmentscheduler.ms.notification.service.NotificationService;
//import br.ufrn.imd.appointmentscheduler.ms.notification.UserService;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
//    private final UserService userService;

    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
//        this.userService = userService;
    }

    @GetMapping()
    public String showUserNotificationList(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
        model.addAttribute("notifications", userService.getUserById(currentUser.getId()).getNotifications());
        return "notifications/listNotifications";
    }

    @GetMapping("/{notificationId}")
    public String showNotification(@PathVariable("notificationId") int notificationId, @AuthenticationPrincipal CustomUserDetails currentUser) {
        Notification notification = notificationService.getNotificationById(notificationId);
        notificationService.markAsRead(notificationId, currentUser.getId());
        return "redirect:" + notification.getUrl();
    }

    @PostMapping("/markAllAsRead")
    public String processMarkAllAsRead(@AuthenticationPrincipal CustomUserDetails currentUser) {
        notificationService.markAllAsRead(currentUser.getId());
        return "redirect:/notifications";
    }
}
