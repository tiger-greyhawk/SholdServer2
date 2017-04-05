package ru.rsoft.shold.core.entity;

import javax.persistence.*;

/**
 * Created by Admin on 29.06.2016.
 */
@Entity
@Table(name = "ADMIN_MESSAGE")
public class AdminMessage {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private final int id;

    @Column (name = "RUSSIAN", nullable = true)
    private final String russianMessage;

    @Column (name = "ENGLISH", nullable = true)
    private final String englishMessage;

    public AdminMessage() { this(0, "", "");
    }

    public AdminMessage(int id, String russianMessage, String englishMessage) {
        this.id = id;
        this.russianMessage = russianMessage;
        this.englishMessage = englishMessage;
    }

    public int getId() {
        return id;
    }

    public String getRussianMessage() {
        return russianMessage;
    }

    public String getEnglishMessage() {
        return englishMessage;
    }

    @Override
    public String toString() {
        return "AdminMessage{" +
                "id=" + id +
                ", russianMessage='" + russianMessage + '\'' +
                ", englishMessage='" + englishMessage + '\'' +
                '}';
    }
}
