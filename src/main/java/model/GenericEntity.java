package model;

import dto.ServerSession;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public class GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(targetEntity = UserLogin.class, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "user_login_id", updatable = false, insertable = false)
    private UserLogin userLogin;

    @Column(name = "user_login_id")
    private Long userLoginId;

    @PrePersist
    public void prePersist() {

        setCreatedAt(new Date());

        setProperties();

    }

    @PreUpdate
    public void preUpdate() {

        setUpdatedAt(new Date());

        setProperties();

    }

    public void setProperties() {

        if (userLoginId == null) {
            userLoginId = ServerSession.getSession().getUserId();
        }

    }

}
