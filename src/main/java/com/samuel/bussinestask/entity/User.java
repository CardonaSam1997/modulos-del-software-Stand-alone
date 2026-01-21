package com.samuel.bussinestask.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username")
    private String userName;
    @Column(length = 15)
    private String password;
    @Column(unique = true)
    private String email;
    private boolean authentication;
    private boolean enable;
    private boolean completed;
    private Date createdAt;
    private Date updatedAt;
}
