package com.ferdev.belajarrestfull.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferdev.belajarrestfull.TestObjectFactory;
import com.ferdev.belajarrestfull.enitity.Produk;
import com.ferdev.belajarrestfull.service.ProdukService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;


import java.util.List;
import java.util.Random;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProdukController.class) // testing layer controller seperti pakai postman
                                                  // tapi dalam bentuk java testing
public class ProdukControllerTest {
    @MockBean
    private ProdukService produkService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper; // mengubah objek menjadi json

    @Test
    void testSaveOrUpdate() throws Exception {
        Produk produk = TestObjectFactory.createProduk();
        Mockito.when(produkService.saveOrUpdate(produk)).thenReturn(produk);

        mockMvc.perform(post("/api/produk")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(produk)))
                        .andExpect(status().isOk());
    }

    @Test
    public void testGetAllProducts() throws Exception {
        final List<Produk> datas = TestObjectFactory.createProdukOfList(10);
        Mockito.when(produkService.findAllProduct()).thenReturn(datas);
        mockMvc.perform(get("/api/produk"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(datas.size())));
    }

    @Test
    void testGetOneProduk() throws Exception {
        final Long id = new Random().nextLong();
        Produk testProduk = TestObjectFactory.createProduk();
        Mockito.when(produkService.findProductById(id)).thenReturn(testProduk);
        mockMvc.perform(get("/api/produk/{id}", id)
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteProduk() throws Exception {
        final Long id = new Random().nextLong();

        Mockito.doNothing().when(produkService).deleteProduk(id);
        mockMvc.perform(delete("/api/produk/{id}", id))
                .andExpect(status().isOk());
    }
}
