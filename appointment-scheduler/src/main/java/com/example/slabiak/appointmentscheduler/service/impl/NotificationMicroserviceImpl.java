package com.example.slabiak.appointmentscheduler.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.example.slabiak.appointmentscheduler.entity.Appointment;
import com.example.slabiak.appointmentscheduler.entity.ChatMessage;
import com.example.slabiak.appointmentscheduler.entity.ExchangeRequest;
import com.example.slabiak.appointmentscheduler.entity.Invoice;
import com.example.slabiak.appointmentscheduler.entity.Notification;
import com.example.slabiak.appointmentscheduler.entity.user.User;
import com.example.slabiak.appointmentscheduler.service.NotificationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NotificationMicroserviceImpl implements NotificationService {
	
	private final String NOTIFICATION_URL;
	private final CloseableHttpClient httpClient;
	
	@Autowired
	private Environment env;
	
	public NotificationMicroserviceImpl() {
		this.NOTIFICATION_URL = "http://localhost:8081/notification";
		this.httpClient = HttpClients.createDefault();
	}

	@Override
	public void newNotification(String title, String message, String url, User user) {
		try {
			HttpPost httpPost = new HttpPost(NOTIFICATION_URL);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			
			Map<String, Object> requestMap = new HashMap<String, Object>();
			requestMap.put("title", title);
			requestMap.put("message", message);
			requestMap.put("url", url);
			requestMap.put("userId", user.getId());
			
			String json = new ObjectMapper().writeValueAsString(requestMap);
			
			StringEntity stringEntity = new StringEntity(json);
			httpPost.setEntity(stringEntity);
			
			httpClient.execute(httpPost);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void markAsRead(int notificationId, int userId) {
		try {
			HttpPost httpPost = new HttpPost(NOTIFICATION_URL + "/user/" + 
					String.valueOf(userId) + "/notification/" + 
					String.valueOf(notificationId) + "/markAsRead");
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			
			httpClient.execute(httpPost);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void markAllAsRead(int userId) {
		try {
			HttpPost httpPost = new HttpPost(NOTIFICATION_URL + "/user/" + 
					String.valueOf(userId) + "/markAllAsRead");
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			
			httpClient.execute(httpPost);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Notification getNotificationById(int notificationId) {
		try {
			HttpGet httpGet = new HttpGet(NOTIFICATION_URL + "/user/1/notification/" + String.valueOf(notificationId));
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-type", "application/json");
			
			ResponseHandler<Notification> responseHandler = response -> {
				Notification notification;
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					String jsonNotifications = EntityUtils.toString(entity);
					
					notification = new ObjectMapper().readValue(jsonNotifications, new TypeReference<Notification>() {});
				} else {
					notification = null;
				}
				
				return notification;
			};
			
			return httpClient.execute(httpGet, responseHandler);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Notification> getAll(int userId) {
		try {
			HttpGet httpGet = new HttpGet(NOTIFICATION_URL + "/user/" + String.valueOf(userId));
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-type", "application/json");
			
			ResponseHandler<List<Notification>> responseHandler = response -> {
				List<Notification> notifications;
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					String jsonNotifications = EntityUtils.toString(entity);
					
					notifications = new ObjectMapper().readValue(jsonNotifications, new TypeReference<List<Notification>>() {});
				} else {
					notifications = new ArrayList<Notification>();
				}
				
				return notifications;
			};
			
			return httpClient.execute(httpGet, responseHandler);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Notification>();
		}
	}

	@Override
	public List<Notification> getUnreadNotifications(int userId) {
		try {
			HttpGet httpGet = new HttpGet(NOTIFICATION_URL + "/user/" + String.valueOf(userId) + "/unread");
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-type", "application/json");
			
			ResponseHandler<List<Notification>> responseHandler = response -> {
				List<Notification> notifications;
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					String jsonNotifications = EntityUtils.toString(entity);
					
					notifications = new ObjectMapper().readValue(jsonNotifications, new TypeReference<List<Notification>>() {});
				} else {
					notifications = new ArrayList<Notification>();
				}
				
				return notifications;
			};
			
			return httpClient.execute(httpGet, responseHandler);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Notification>();
		}
	}
	

    @Override
    public void newAppointmentFinishedNotification(Appointment appointment, boolean sendEmail) {
        String title = "Appointment Finished";
        String message = "Appointment finished, you can reject that it took place until " + appointment.getEnd().plusHours(24).toString();
        String url = "/appointments/" + appointment.getId();
        newNotification(title, message, url, appointment.getCustomer());
    }

    @Override
    public void newAppointmentRejectionRequestedNotification(Appointment appointment, boolean sendEmail) {
        String title = "Appointment Rejected";
        String message = appointment.getCustomer().getFirstName() + " " + appointment.getCustomer().getLastName() + "rejected an appointment. Your approval is required";
        String url = "/appointments/" + appointment.getId();
        newNotification(title, message, url, appointment.getProvider());
    }

    @Override
    public void newNewAppointmentScheduledNotification(Appointment appointment, boolean sendEmail) {
        String title = "New appointment scheduled";
        String message = "New appointment scheduled with" + appointment.getCustomer().getFirstName() + " " + appointment.getProvider().getLastName() + " on " + appointment.getStart().toString();
        String url = "/appointments/" + appointment.getId();
        newNotification(title, message, url, appointment.getProvider());
    }

    @Override
    public void newAppointmentCanceledByCustomerNotification(Appointment appointment, boolean sendEmail) {
        String title = "Appointment Canceled";
        String message = appointment.getCustomer().getFirstName() + " " + appointment.getCustomer().getLastName() + " cancelled appointment scheduled at " + appointment.getStart().toString();
        String url = "/appointments/" + appointment.getId();
        newNotification(title, message, url, appointment.getProvider());
    }

    @Override
    public void newAppointmentCanceledByProviderNotification(Appointment appointment, boolean sendEmail) {
        String title = "Appointment Canceled";
        String message = appointment.getProvider().getFirstName() + " " + appointment.getProvider().getLastName() + " cancelled appointment scheduled at " + appointment.getStart().toString();
        String url = "/appointments/" + appointment.getId();
        newNotification(title, message, url, appointment.getCustomer());
    }

    public void newInvoice(Invoice invoice, boolean sendEmail) {
        String title = "New invoice";
        String message = "New invoice has been issued for you";
        String url = "/invoices/" + invoice.getId();
        newNotification(title, message, url, invoice.getAppointments().get(0).getCustomer());
    }

    @Override
    public void newExchangeRequestedNotification(Appointment oldAppointment, Appointment newAppointment, boolean sendEmail) {
        String title = "Request for exchange";
        String message = "One of the users sent you a request to exchange his appointment with your appointment";
        String url = "/appointments/" + newAppointment.getId();
        newNotification(title, message, url, newAppointment.getCustomer());
    }

    @Override
    public void newExchangeAcceptedNotification(ExchangeRequest exchangeRequest, boolean sendEmail) {
        String title = "Exchange request accepted";
        String message = "Someone accepted your appointment exchange request from " + exchangeRequest.getRequested().getStart() + " to " + exchangeRequest.getRequestor().getStart();
        String url = "/appointments/" + exchangeRequest.getRequested();
        newNotification(title, message, url, exchangeRequest.getRequested().getCustomer());
    }

    @Override
    public void newExchangeRejectedNotification(ExchangeRequest exchangeRequest, boolean sendEmail) {
        String title = "Exchange request rejected";
        String message = "Someone rejected your appointment exchange request from " + exchangeRequest.getRequestor().getStart() + " to " + exchangeRequest.getRequested().getStart();
        String url = "/appointments/" + exchangeRequest.getRequestor();
        newNotification(title, message, url, exchangeRequest.getRequestor().getCustomer());
    }

    @Override
    public void newAppointmentRejectionAcceptedNotification(Appointment appointment, boolean sendEmail) {
        String title = "Rejection accepted";
        String message = "You provider accepted your rejection request";
        String url = "/appointments/" + appointment.getId();
        newNotification(title, message, url, appointment.getCustomer());
    }

    @Override
    public void newChatMessageNotification(ChatMessage chatMessage, boolean sendEmail) {
        String title = "New chat message";
        String message = "You have new chat message from " + chatMessage.getAuthor().getFirstName() + " regarding appointment scheduled at " + chatMessage.getAppointment().getStart();
        String url = "/appointments/" + chatMessage.getAppointment().getId();
        newNotification(title, message, url, chatMessage.getAuthor() == chatMessage.getAppointment().getProvider() ? chatMessage.getAppointment().getCustomer() : chatMessage.getAppointment().getProvider());
    }
}
