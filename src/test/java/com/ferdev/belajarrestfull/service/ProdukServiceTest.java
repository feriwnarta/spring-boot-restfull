package com.ferdev.belajarrestfull.service;

import com.ferdev.belajarrestfull.TestObjectFactory;
import com.ferdev.belajarrestfull.enitity.Produk;
import com.ferdev.belajarrestfull.repository.ProdukRepository;
import com.ferdev.belajarrestfull.service.impl.ProdukServiceImpl;
import net.bytebuddy.dynamic.DynamicType;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@ExtendWith(SpringExtension.class)
public class ProdukServiceTest {
    @InjectMocks // menginjek objek yang dibutuhkan
    ProdukServiceImpl produkService;
    @Mock // membuat objek mock
    ProdukRepository produkRepository; // produk service butuh dependency produk repository
    // kita akan bikin dummy objek


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(produkService, "produkRepository", produkRepository);
    }

    @Test
    void testFindAll() {
        List<Produk> produks = TestObjectFactory.createProdukOfList(10);
        Mockito.when(produkRepository.findAll()).thenReturn(produks); // manipulasi findAll() dri prduk repository

        List<Produk> actual = produkService.findAllProduct(); // menyimpan objek produks

        MatcherAssert.assertThat(actual.size(), Matchers.equalTo(produks.size())); // cocokin jumlah
    }

    @Test
    void testGetProdukBy() {
        final Long id = new Random().nextLong();
        final Produk produk = TestObjectFactory.createProduk();

        Mockito.when(produkRepository.findById(id)).thenReturn(Optional.of(produk));

        final Produk actual = produkService.findProductById(id);

        // pencocokan
        MatcherAssert.assertThat(actual.getId(), Matchers.equalTo(produk.getId()));
        MatcherAssert.assertThat(actual.getNama(), Matchers.equalTo(produk.getNama()));
        MatcherAssert.assertThat(actual.getHarga(), Matchers.equalTo(produk.getHarga()));
        MatcherAssert.assertThat(actual.getQuantity(), Matchers.equalTo(produk.getQuantity()));
    }

    @Test
    void testProdukByIdWithNullData(){
//        final Long id = new Random().nextLong();
//        Mockito.when(produkRepository.findById(id)).thenReturn(Optional.of(nullable));
//        final Produk produk = produkService.findProductById(id);
//        MatcherAssert.assertThat(produk, Matchers.nullValue());
    }

    @Test
    void testSaveOrUpdate(){
        final Produk produk = TestObjectFactory.createProduk();
        // mockito ketika save dipanggil(produk) kembalikan objek produk
        Mockito.when(produkRepository.save(produk)).thenReturn(produk);
        final Produk actual = produkService.saveOrUpdate(produk);
        MatcherAssert.assertThat(actual, Matchers.notNullValue());
    }

    @Test
    void testDeleteProduk(){
        final Long id = new Random().nextLong();
        Produk produk = TestObjectFactory.createProduk();
        Mockito.when(produkRepository.findById(id)).thenReturn(Optional.of(produk));
        Mockito.doNothing().when(produkRepository).delete(produk);
        produkService.deleteProduk(id);
        // memastikan method delete dari produk repository dipanggil minimal 1 kali
        Mockito.verify(produkRepository, Mockito.times(1)).delete(produk);
    }
}
