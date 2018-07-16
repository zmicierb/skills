package com.barysevich.project.core.model;


import java.util.Objects;
import java.util.UUID;

import com.barysevich.project.api.NotificationType;
import com.barysevich.project.email.Email;
import com.barysevich.project.localization.Locale;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Класс содержит информацию, необходимую для отправки письма пользователю.
 */
public class NotificationData
{
    /**
     * Идентификатор уведомления.
     */
    private final UUID notificationId;

    /**
     * Объект, в котором находятся все необходимые данные для создания письма.
     */
    private final String messageData;

    /**
     * Адрес отправки сообщения (емейл, номер телефона и тп).
     */
    private final Email email;

    /**
     * Тип сообщения, которое нужно отправить пользователю.
     */
    private final NotificationType notificationType;

    /**
     * Локаль для уведомления
     */
    private final Locale locale;


    @JsonCreator
    private NotificationData(@JsonProperty("notificationId") final UUID notificationId,
                             @JsonProperty("messageData") final String messageData,
                             @JsonProperty("email") final Email email,
                             @JsonProperty("notificationType") final NotificationType notificationType,
                             @JsonProperty("locale") final Locale locale)
    {
        this.notificationId = Objects.requireNonNull(notificationId);
        this.messageData = Objects.requireNonNull(messageData);
        this.email = Objects.requireNonNull(email);
        this.notificationType = Objects.requireNonNull(notificationType);
        this.locale = Objects.requireNonNull(locale);
    }


    public static Builder builder()
    {
        return new Builder();
    }


    @JsonProperty("notificationId")
    public UUID getNotificationId()
    {
        return notificationId;
    }


    @JsonProperty("messageData")
    public String getMessageData()
    {
        return messageData;
    }


    @JsonProperty("email")
    public Email getEmail()
    {
        return email;
    }


    @JsonProperty("notificationType")
    public NotificationType getNotificationType()
    {
        return notificationType;
    }


    @JsonProperty("locale")
    public Locale getLocale()
    {
        return locale;
    }


    @Override
    public String toString()
    {
        return "NotificationData{" +
            "notificationId=" + notificationId +
            ", messageData='" + messageData + '\'' +
            ", email=" + email +
            ", notificationType=" + notificationType +
            ", locale=" + locale +
            '}';
    }


    public static final class Builder
    {
        private UUID notificationId;

        private String messageData;

        private Email email;

        private NotificationType notificationType;

        private Locale locale;


        public Builder withNotificationId(final UUID notificationId)
        {
            this.notificationId = notificationId;
            return this;
        }


        public Builder withMessageData(final String messageData)
        {
            this.messageData = messageData;
            return this;
        }


        public Builder withEmail(final Email email)
        {
            this.email = email;
            return this;
        }


        public Builder withNotificationType(final NotificationType notificationType)
        {
            this.notificationType = notificationType;
            return this;
        }


        public Builder withLocale(final Locale locale)
        {
            this.locale = locale;
            return this;
        }


        public NotificationData build()
        {
            return new NotificationData(notificationId, messageData, email, notificationType, locale);
        }
    }
}
