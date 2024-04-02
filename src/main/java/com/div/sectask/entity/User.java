package com.div.sectask.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "users")
@Table(schema = "security_users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
}
