package com.btg.fondos.application.aws;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final SesClient sesClient;

    public EmailService() {
        AwsBasicCredentials awsCreds =
                AwsBasicCredentials.create("AKIASVLKCQQIV52BS3FF",
                        "8u6BPfqaaKWbeOOjUpiOmhMFLyp1GoaKwPA39EZE");

        this.sesClient = SesClient.builder()
                .region(Region.US_EAST_2) // Change to your preferred region
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

    public String sendEmail(String toEmail, String subject, String body) {
        // Create destination
        Destination destination = Destination.builder()
                .toAddresses(toEmail)
                .build();

        // Create message subject and body
        Content subjectContent = Content.builder()
                .data(subject)
                .build();

        Content bodyContent = Content.builder()
                .data(body)
                .build();

        Message message = Message.builder()
                .subject(subjectContent)
                .body(Body.builder()
                        .text(bodyContent)
                        .build())
                .build();

        // Create send email request
        SendEmailRequest emailRequest = SendEmailRequest.builder()
                .source("andrexjahifer@gmail.com") // Replace with your verified email
                .destination(destination)
                .message(message)
                .build();

        // Send email
        SendEmailResponse response = sesClient.sendEmail(emailRequest);
        return response.messageId();
    }
}