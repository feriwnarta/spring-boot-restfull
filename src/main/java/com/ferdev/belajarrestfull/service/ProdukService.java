package com.ferdev.belajarrestfull.service;
import com.ferdev.belajarrestfull.enitity.Produk;

import java.util.List;

public interface ProdukService {
    List<Produk> findAllProduct();
    Produk findProductById(Long id);
    Produk saveOrUpdate(Produk produk);
    void deleteProduk(Long id);
}
