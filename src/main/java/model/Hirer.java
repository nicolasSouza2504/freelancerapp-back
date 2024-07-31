package model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hirer")
public class Hirer extends DefaultEntity{

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "name")
    private String name;

    @Column(name = "hex_color")
    private String hexColor;

}
