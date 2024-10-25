package com.btg.fondos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class SnsConfig {

    @Bean
    public SnsClient snsClient() {
        AwsBasicCredentials awsCreds =
                AwsBasicCredentials.create("AKIASVLKCQQIV52BS3FF",
                        "8u6BPfqaaKWbeOOjUpiOmhMFLyp1GoaKwPA39EZE");

        return SnsClient.builder()
                .region(Region.US_EAST_2) // Elige la región que necesitas
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }
}