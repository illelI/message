package com.ilelli.user;

import jakarta.annotation.Nullable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String mail;
    private String password;
    private String name;
    @Lob
    @Nullable
    private byte[] profilePic;
    private Timestamp lastActiveAt;
    @ElementCollection
    private Set<UUID> invitations;
    @ElementCollection
    private Set<UUID> friends;
    @ElementCollection
    private Set<UUID> blocked;

    public void addInvitation(UUID id) {
        invitations.add(id);
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
