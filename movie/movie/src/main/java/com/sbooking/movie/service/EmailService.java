package com.sbooking.movie.service;


import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import com.sbooking.movie.dto.EmailMessage;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Service
public class EmailService  {
    public String sendMail (EmailMessage emailMessageDetails) throws MailjetException, MailjetSocketTimeoutException {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient("078d5057f11c29a82048d27f18b0feef","8b4ba303d300be508aeea1c17d8384fb", new ClientOptions("v3.1"));
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "moviemngmnt@gmail.com")
                                        .put("Name", "Movie"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", emailMessageDetails.getEmail())
                                                .put("Name", emailMessageDetails.getName())))
                                .put(Emailv31.Message.SUBJECT, "OCBC Movie Booking Confirmation")
                                .put(Emailv31.Message.TEXTPART, "Movie Booking Acknowledgement")
                                .put(Emailv31.Message.HTMLPART,"Hi " +emailMessageDetails.getName() + ",<br><br>"+
                                        "Your movie booking have been confirmed.<br><br>" +
                                        "Movie Title: " + emailMessageDetails.getMovieName() + "<br><br>" +
                                        "Movie Date: " + emailMessageDetails.getMovieTime() + "<br><br>" +
                                        "Total Price Paid: $" + emailMessageDetails.getPrice() + "<br><br>Seats number booked: " + StringUtils.join(ArrayUtils.toObject(emailMessageDetails.getSeatList()), ", "))
                                .put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));
        response = client.post(request);
        return response.toString();
        }

    public boolean checkValidEmail(String email) {
        boolean isValid = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            isValid = false;
        }
        return isValid;
    }
}
