package org.example.jwt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The type Message.
 */
@Entity
@Table(name = "messages")
@Getter
@Setter
@ToString(callSuper = true)
public class Message extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Sets user.
     *
     * @param user
     *         the user
     */
    public void setUser(User user) {
        this.user = user;
    }

}
