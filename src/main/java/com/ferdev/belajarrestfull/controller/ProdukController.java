package com.ferdev.belajarrestfull.controller;

import com.ferdev.belajarrestfull.enitity.Produk;
import com.ferdev.belajarrestfull.service.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/produk")
public class ProdukController {
    @Autowired
    ProdukService produkService;

    @GetMapping
    public ResponseEntity<List<Produk>> getAllProduk(){
        return new ResponseEntity<>(produkService.findAllProduct(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Produk> saveOrUpdate(@Validated @RequestBody Produk produk){
        return new ResponseEntity<>(produkService.saveOrUpdate(produk), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produk> getOneProduk(@PathVariable Long id){
        return new ResponseEntity<>(produkService.findProductById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteProduk(@PathVariable Long id){
        produkService.deleteProduk(id);
        return "data berhasil dihapus dengan id : " + id;
    }




}
