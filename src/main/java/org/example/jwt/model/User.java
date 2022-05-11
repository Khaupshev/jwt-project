package org.example.jwt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

/**
 * The type User.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
@ToString(callSuper = true)
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<Message> messages;

    /**
     * Sets messages.
     *
     * @param messages
     *         the messages
     */
    public void setMessages(List<Message> messages) {
        this.messages.clear();
        messages.forEach(message -> message.setUser(this));
        this.messages.addAll(messages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}