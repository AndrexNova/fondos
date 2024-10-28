package com.btg.fondos.application.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Service
public class SmsService {

    private final SnsClient snsClient ;

    public SmsService(SnsClient snsClient) {
        this.snsClient = snsClient;
    }


    public String sendSMS(String phoneNumber, String message) {
        PublishRequest request = PublishRequest.builder()
                .message(message)
                .phoneNumber(phoneNumber)
                .build();

        PublishResponse result = snsClient.publish(request);
        return result.messageId();
    }

    public void sendEmail(String subject, String message) {

        try{
            PublishRequest request = PublishRequest.builder()
                    .topicArn("arn:aws:sns:us-east-2:183295443985:BTGPrueba")
                    .subject(subject)
                    .message(message)
                    .build();

            PublishResponse result = snsClient.publish(request);
            System.out.println("Mensaje enviado con ID: " + result.messageId());
        }catch (Exception e){
            System.out.println("TRYYYYYYY");

            e.printStackTrace();
        }

    }


}