package model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hirer")
public class Hirer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "name")
    private String name;

    @Column(name = "hex_color")
    private String hexColor;

    @ManyToOne(targetEntity = UserLogin.class, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "user_login_id", updatable = false, insertable = false, foreignKey = @ForeignKey(name = "fk_hirer_user_login"))
    private UserLogin userLogin;

    @Column(name = "user_login_id")
    private Long userLoginId;

}
