package com.ferdev.belajarrestfull.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Produk {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nama;
    @Column(nullable = false)
    private Long harga;
    @Column(nullable = false)
    private Long quantity;
    // use lombok no getter and setter
}
