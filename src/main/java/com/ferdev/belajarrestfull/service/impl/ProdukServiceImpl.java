package com.ferdev.belajarrestfull.service.impl;

import com.ferdev.belajarrestfull.enitity.Produk;
import com.ferdev.belajarrestfull.repository.ProdukRepository;
import com.ferdev.belajarrestfull.service.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdukServiceImpl implements ProdukService {
    @Autowired
    ProdukRepository produkRepository;

    @Override
    public List<Produk> findAllProduct() {
        return produkRepository.findAll();
    }

    @Override
    public Produk findProductById(Long id) {
        return produkRepository.findById(id).orElse(new Produk());
    }

    @Override
    public Produk saveOrUpdate(Produk produk) {
        return produkRepository.save(produk);
    }

    @Override
    public void deleteProduk(Long id) {
        produkRepository.delete(findProductById(id));
    }
}
