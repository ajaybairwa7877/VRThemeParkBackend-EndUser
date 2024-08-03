package com.VRThemePark.entity;


import com.VRThemePark.models.NavItems;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class HeaderSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String logoImage;
    @Column(nullable = false)
    private String navlink1;
    @Column(nullable = false)
    private String navlink2;
    @Column(nullable = false)
    private String navlink3;
    @Column(nullable = false)
    private String navlink4;
    @Column(nullable = false)
    private String buttonText1;
    @Column(nullable = false)
    private String buttonText2;

}
