package com.example.notification_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mail_configs")
public class MailConfig {
    @Id
    private String id;
    private String smtpHost;
    private int smtpPort;
    private String mailUser;
    private String mailPassword;

    // Standard Getters and Setters
    public String getSmtpHost() { return smtpHost; }
    public void setSmtpHost(String smtpHost) { this.smtpHost = smtpHost; }
    public int getSmtpPort() { return smtpPort; }
    public void setSmtpPort(int smtpPort) { this.smtpPort = smtpPort; }
    public String getMailUser() { return mailUser; }
    public void setMailUser(String mailUser) { this.mailUser = mailUser; }
    public String getMailPassword() { return mailPassword; }
    public void setMailPassword(String mailPassword) { this.mailPassword = mailPassword; }
}