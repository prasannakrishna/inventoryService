package com.bhagwat.scm.productService.dto;

public class NotifyCommunityCommand {
    public final String sellerId;
    public final String message;

    public NotifyCommunityCommand(String sellerId, String message) {
        this.sellerId = sellerId;
        this.message = message;
    }
}
