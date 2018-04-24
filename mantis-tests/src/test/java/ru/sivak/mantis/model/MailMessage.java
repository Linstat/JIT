package ru.sivak.mantis.model;

/**
 * @author p.sivak.
 * @since 24.04.2018.
 */
public class MailMessage {
    public String to;
    public String text;

    public MailMessage(String to, String text){
        this.to = to;
        this.text = text;
    }

}
