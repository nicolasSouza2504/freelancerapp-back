package model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "user_login")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "salt_password", columnDefinition = "TEXT")
    private String saltPassword;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

}
