package com.ilelli.user;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String mail;
    private String password;
    private String name;
    private Timestamp lastActiveAt;
    @ElementCollection
    private Set<UUID> invitations;
    @ElementCollection
    private Set<UUID> friends;
    @ElementCollection
    private Set<UUID> blocked;

    public User(String mail, String password, String name) {
        this.mail = mail;
        this.name = name;
        this.password = password;
    }

    public void addToFriends(UUID id) {
        friends.add(id);
    }

    public void removeFromFriends(UUID id) {
        friends.remove(id);
    }

    public void addToBlocked(UUID id) {
        blocked.add(id);
    }

    public void removeFromBlocked(UUID id) {
        blocked.remove(id);
    }

    public void acceptInvitation(UUID id) {
        invitations.remove(id);
        friends.add(id);
    }

    public void rejectInvitation(UUID id) {
        invitations.remove(id);
    }
}
