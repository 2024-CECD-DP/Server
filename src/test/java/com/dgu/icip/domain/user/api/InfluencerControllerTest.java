//package com.dgu.icip.domain.user.api;
//
//import com.dgu.icip.domain.user.dao.InfluencerRepository;
//import com.dgu.icip.domain.user.entity.Influencer;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.result.StatusResultMatchers;
//
//import java.util.stream.IntStream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class InfluencerControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private InfluencerRepository influencerRepository;
//
//    @Test
//    @DisplayName("/report/{userID} : 리포팅 API")
//    public void test() throws Exception {
//        //given
//        Influencer influencer = Influencer.builder()
//                .influencer_id("123")
//                .instaName("limwngur")
//                .build();
//
//        influencerRepository.save(influencer);
//
//        //expected
//        mockMvc.perform(MockMvcRequestBuilders.get("/user/influencer-report/{userId}",influencer.getId())
//                .contentType(APPLICATION_JSON)
//        )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$."))
//
//    }
//
//}