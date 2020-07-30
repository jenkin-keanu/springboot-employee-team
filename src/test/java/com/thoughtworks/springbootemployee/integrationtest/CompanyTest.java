package com.thoughtworks.springbootemployee.integrationtest;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CompanyTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
    }

    @Test
    void should_add_a_company_when_add_company_given_valid_company() throws Exception {
        String companyJsonStr = "{\n" +
                " \"name\": \"TW\"\n" +
                "}";

        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyJsonStr))
                .andExpect(status().isCreated());
        List<Company> companies = companyRepository.findAll();
        Assertions.assertEquals(1, companies.size());
        Assertions.assertEquals("TW", companies.get(1).getName());
    }

    @Test
    void should_return_ok_when_get_companies_given_none() throws Exception {
        Company company1 = new Company();
        company1.setName("oocl");
        companyRepository.save(company1);

        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_specific_company_when_get_company_by_id_given_companyId() throws Exception {
        int companyId = 1;
        Company company1 = new Company();
        company1.setName("tw");
        companyRepository.save(company1);

        mockMvc.perform(get("/companies/" + companyId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("tw"));
    }

    @Test
    void should_return_none_when_delete_company_by_id_given_company_id() throws Exception {
        int companyId = 1;
        Company company1 = new Company();
        company1.setName("tw");
        companyRepository.save(company1);

        mockMvc.perform(delete("/companies/" + companyId))
                .andExpect(status().isOk());

        Assertions.assertEquals(0, companyRepository.findAll().size());
    }

    @Test
    void should_return_oocl_when_update_company_given_company_id_and_new_company_name() throws Exception {

        Company company1 = new Company();
        company1.setName("tw");
        companyRepository.save(company1);

        int companyId = 1;
        String companyName = "oocl";
        String companyJsonStr = "{\n" +
                " \"name\": \"" + companyName + "\"\n" +
                "}";
        mockMvc.perform(put("/companies/" + companyId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyJsonStr))
                .andExpect(status().isOk());
        Assertions.assertEquals(companyName, companyRepository.findById(companyId).get().getName());
    }

    @Test
    void should_2_employees_when_get_employees_by_company_id_given_company_id() throws Exception {
        int companyId = 1;
        mockMvc.perform(get("/companies/" + companyId + "/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));


    }

    @Test
    void should_return_paged_companies_when_get_paged_companies_given_page_and_size() throws Exception {
        Integer page = 1;
        Integer size = 2;
        Company company1 = new Company();
        company1.setName("oocl");
        companyRepository.save(company1);
        Company company2 = new Company();
        company2.setName("oocl");
        companyRepository.save(company2);
        Company company3 = new Company();
        company3.setName("oocl");
        companyRepository.save(company3);

        mockMvc.perform(get("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1));
    }

}
