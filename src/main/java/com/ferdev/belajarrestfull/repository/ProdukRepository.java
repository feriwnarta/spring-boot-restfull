package com.ferdev.belajarrestfull.repository;

import com.ferdev.belajarrestfull.enitity.Produk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdukRepository extends JpaRepository<Produk, Long> {
}
