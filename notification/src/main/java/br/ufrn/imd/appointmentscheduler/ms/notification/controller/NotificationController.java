package br.ufrn.imd.appointmentscheduler.ms.notification.controller;

//import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping()
    public String showUserNotificationList(Model model, int userId) {
        model.addAttribute("notifications", notificationService.getAll(userId));
        return "notifications/listNotifications";
    }

    @GetMapping("/{notificationId}")
    public String showNotification(@PathVariable("notificationId") int notificationId, int userId) {
        Notification notification = notificationService.getNotificationById(notificationId);
        notificationService.markAsRead(notificationId, userId);
        return "redirect:" + notification.getUrl();
    }

    @PostMapping("/markAllAsRead")
    public String processMarkAllAsRead(int userId) {
        notificationService.markAllAsRead(userId);
        return "redirect:/notifications";
    }
}
