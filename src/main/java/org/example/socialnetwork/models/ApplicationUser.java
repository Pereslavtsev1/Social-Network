package org.example.socialnetwork.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private String password;
    @Column(unique = true)
    private String username;
    private Date dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> authorities;

    public ApplicationUser() {
        authorities = new HashSet<>();
    }
}
