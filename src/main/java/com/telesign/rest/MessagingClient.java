package com.telesign.rest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/**
 * TeleSign's Messaging API allows you to easily send SMS messages. You can send alerts, reminders, and notifications,
 * or you can send verification messages containing one-time passcodes (OTP).
 */
public class MessagingClient extends RestClient {

    private static final String MESSAGING_RESOURCE = "/v1/messaging";
    private static final String MESSAGING_STATUS_RESOURCE = "/v1/messaging/%s";

    public MessagingClient(String customerId, String secretKey) {
        super(customerId, secretKey);
    }

    /**
     * Send a message to the target phone_number.
     * <p>
     * See https://developer.telesign.com/v2.0/docs/messaging-api for detailed API documentation.
     */
    public TelesignResponse message(String phoneNumber, String message, String messageType, Map<String, String> params) throws IOException, GeneralSecurityException {

        if (params == null) {
            params = new HashMap<>();
        }

        params.put("phone_number", phoneNumber);
        params.put("message", message);
        params.put("message_type", messageType);

        return this.post(MESSAGING_RESOURCE, params);
    }

    /**
     * Retrieves the current status of the message.
     * <p>
     * See https://developer.telesign.com/v2.0/docs/messaging-api for detailed API documentation.
     */
    public TelesignResponse status(String referenceId, Map<String, String> params) throws IOException, GeneralSecurityException {

        return this.get(String.format(MESSAGING_STATUS_RESOURCE, referenceId), params);
    }
}