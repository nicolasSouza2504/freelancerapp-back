package model;

import dto.ServerSession;
import jakarta.persistence.*;

@MappedSuperclass
public class DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = UserLogin.class, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "user_login_id", updatable = false, insertable = false)
    private UserLogin userLogin;

    @Column(name = "user_login_id")
    private Long userLoginId;

    @PrePersist
    @PreUpdate
    public void setProperties() {

        if (userLoginId == null) {
            userLoginId = ServerSession.getSession().getUserId();
        }

    }

}
