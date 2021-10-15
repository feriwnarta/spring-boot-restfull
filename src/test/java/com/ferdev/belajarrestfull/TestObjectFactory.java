package com.ferdev.belajarrestfull;

import com.ferdev.belajarrestfull.enitity.Produk;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestObjectFactory {
    public static Produk createProduk(){
        final Produk produk = new Produk();
        produk.setId(new Random().nextLong());
        produk.setNama(RandomStringUtils.randomAlphabetic(10));
        produk.setHarga(new Random().nextLong());
        produk.setQuantity(new Random().nextLong());
        return produk;
    }

    public static List<Produk> createProdukOfList(final int size) {
        List<Produk> produks = new ArrayList<>();
        for(int i = 1; i <= size; i++){
            produks.add(createProduk());
        }
        return produks;
    }
}
