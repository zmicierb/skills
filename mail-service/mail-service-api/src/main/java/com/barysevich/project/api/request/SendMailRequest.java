package com.barysevich.project.api.request;


import java.util.Objects;
import java.util.UUID;

import com.barysevich.project.api.NotificationType;
import com.barysevich.project.email.Email;
import com.barysevich.project.localization.Locale;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static java.util.Objects.requireNonNull;


public class SendMailRequest
{
    /**
     * Идентификатор уведомления.
     */
    private final UUID notificationId;

    /**
     * Строковое представление json-объекта, в котором находятся все необходимые данные для создания письма.
     */
    private final String messageData;

    /**
     * Адрес отправки сообщения (емейл, номер телефона и т.п.).
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
    private SendMailRequest(@JsonProperty("notificationId") final UUID notificationId,
                                    @JsonProperty("messageData") final String messageData,
                                    @JsonProperty("email") final Email email,
                                    @JsonProperty("notificationType") final NotificationType notificationType,
                                    @JsonProperty("locale") final Locale locale)
    {
        this.notificationId = requireNonNull(notificationId);
        this.messageData = requireNonNull(messageData);
        this.email = requireNonNull(email);
        this.notificationType = requireNonNull(notificationType);
        this.locale = requireNonNull(locale);
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
        return "MailNotificationMessage{" +
            "notificationId=" + notificationId +
            ", messageData='" + messageData + '\'' +
            ", email=" + email +
            ", notificationType=" + notificationType +
            ", locale=" + locale +
            '}';
    }


    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        final SendMailRequest that = (SendMailRequest) o;
        return Objects.equals(notificationId, that.notificationId) &&
            Objects.equals(messageData, that.messageData) &&
            Objects.equals(email, that.email) &&
            notificationType == that.notificationType &&
            Objects.equals(locale, that.locale);
    }


    @Override
    public int hashCode()
    {

        return Objects.hash(notificationId, messageData, email, notificationType, locale);
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


        public SendMailRequest build()
        {
            return new SendMailRequest(notificationId, messageData, email, notificationType, locale);
        }
    }
}
