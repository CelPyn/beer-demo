package com.axxes.demo;

import com.axxes.demo.domain.Beer;
import com.axxes.demo.service.BeerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BeerController.class)
class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeerService beerService;

    @Test
    void getAll() throws Exception {
        when(beerService.getBeers()).thenReturn(List.of(new Beer(14, "Westalle", "Dark and sweet abbay beer.")));

        mockMvc.perform(get("/api/beer")).andExpect(status().isOk()).andExpect(content().json("[{\"id\":14,\"name\":\"Westalle\"}]"));

        verify(beerService, times(1)).getBeers();
        verifyNoMoreInteractions(beerService);
    }

    @Test
    void getOne() throws Exception {
        when(beerService.findById(14)).thenReturn(Optional.of(new Beer(14, "Westalle", "Dark and sweet abbay beer.")));

        mockMvc.perform(get("/api/beer/14"))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{\"id\":14,\"name\":\"Westalle\",\"description\":\"Dark and sweet abbay beer.\"}"));

        verify(beerService, times(1)).findById(14);
        verifyNoMoreInteractions(beerService);
    }

    @Test
    void getOne_notFound() throws Exception {
        when(beerService.findById(eq(14))).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/beer/14")).andExpect(status().isNotFound());

        verify(beerService, times(1)).findById(eq(14));
        verifyNoMoreInteractions(beerService);
    }

    @Test
    void search() throws Exception {
        when(beerService.searchByName("West")).thenReturn(List.of(new Beer(14, "Westalle", "Dark and sweet abbay beer.")));

        mockMvc.perform(get("/api/beer/search").param("q", "West"))
                    .andExpect(status().isOk())
                    .andExpect(content().json("[{\"id\":14,\"name\":\"Westalle\"}]"));

        verify(beerService, times(1)).searchByName("West");
        verifyNoMoreInteractions(beerService);
    }

    @Test
    void search_notFound() throws Exception {
        when(beerService.searchByName("West")).thenReturn(List.of());

        mockMvc.perform(get("/api/beer/search").param("q", "West")).andExpect(status().isNoContent());

        verify(beerService, times(1)).searchByName("West");
        verifyNoMoreInteractions(beerService);
    }
}
