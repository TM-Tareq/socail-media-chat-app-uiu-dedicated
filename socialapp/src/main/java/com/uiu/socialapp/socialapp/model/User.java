package com.uiu.socialapp.socialapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
//    Internal DB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    University ID
    @Column(unique = true, nullable = false)
    private String universityId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}


/*
package com.uiu.socialapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String bio;

    private String profileImage;
}
 */


// @Data automatically getter, setter, toString, equals, hashCode all creates automatically