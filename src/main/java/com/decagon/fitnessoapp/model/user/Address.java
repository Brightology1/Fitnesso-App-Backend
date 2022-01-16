package com.decagon.fitnessoapp.model.user;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "street_detail")
    private String streetDetail;

    @NotNull
    private String state;

    @NotNull
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private List<Person> person;

    @NotNull
    private String country;

}