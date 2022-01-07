package com.example.cryptousersjwt.Roles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table

@NoArgsConstructor
@Getter
@Setter
public class Roles {
    @Id
    @SequenceGenerator(
            sequenceName = "role_sequence",
            name="role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    @Column
    private Long Id;

    @Enumerated(EnumType.STRING)
    @Column
    private ERoles name;

}
