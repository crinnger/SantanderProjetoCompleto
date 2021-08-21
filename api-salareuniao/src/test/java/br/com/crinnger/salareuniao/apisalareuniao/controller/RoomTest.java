package br.com.crinnger.salareuniao.apisalareuniao.controller;

import br.com.crinnger.salareuniao.apisalareuniao.model.Room;
import br.com.crinnger.salareuniao.apisalareuniao.repository.RoomRepository;
import br.com.crinnger.salareuniao.apisalareuniao.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(RoomController.class)
@OverrideAutoConfiguration(enabled=true)
public class RoomTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RoomService service;

    @MockBean
    RoomRepository repository;

    private final String host = "/usuario";

    private final Room expected = Room.builder()
            .id(1L)
            .date("02/01/2010")
            .endHour("21hs")
            .startHour("20hs")
            .build();

    @TestConfiguration
    static class serviceImplTestContextConfiguration {
        @Bean
        public RoomService service() {
            return new RoomService();
        }
    }

    @Test
    public void testGetById() throws Exception{
        given(repository.findById(any())).willReturn(Optional.of(expected));
        String json=this.objectMapper.writeValueAsString(expected);
        this.mockMvc.perform(MockMvcRequestBuilders.get(host.concat("/{id}"), "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    public void testFindAll() throws Exception{
        given(repository.findAll()).willReturn(Collections.singletonList(expected));
        String json=this.objectMapper.writeValueAsString(Collections.singletonList(expected));
        this.mockMvc.perform(MockMvcRequestBuilders.get(host)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    public void testCreate() throws Exception {

        given(repository.save(any(Room.class))).willReturn(expected);

        String json=this.objectMapper.writeValueAsString(expected);

        this.mockMvc.perform(MockMvcRequestBuilders.post(host)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    public void testUpdate()   throws Exception {

        given(repository.save(any(Room.class))).willReturn(expected);

        String json=this.objectMapper.writeValueAsString(expected);

        this.mockMvc.perform(MockMvcRequestBuilders.put(host)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    public void testDelete() throws Exception{
        given(repository.findById(any())).willReturn(Optional.of(expected));
        this.mockMvc.perform(MockMvcRequestBuilders.delete(host.concat("/{id}"),"1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}