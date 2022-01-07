package com.example.cryptousersjwt.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Cryptos {

    @SequenceGenerator(
            name="crypto_sequence",
            sequenceName = "crypto_seequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "crypto_sequence"
    )
    @Id
    @Column
    private Long Id;

    @Column
    private String cryptoname;

    @Column
    private Long cryptoid;

    @Column
    private String username;

    public Cryptos(String cryptoname, Long cryptoid) {
        this.cryptoname = cryptoname;
        this.cryptoid = cryptoid;
    }
}
