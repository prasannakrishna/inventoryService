package com.bhagwat.scm.productService.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final SnsClient snsClient;

    public CommunityNotificationService(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    public void notifyCommunity(String sellerId, String message) {
        PublishRequest request = PublishRequest.builder()
                .message(message)
                .topicArn("arn:aws:sns:region:account-id:community-notifications")
                .build();
        snsClient.publish(request);
    }
}
