package com.UST.CompanyEmployee.controller;

import com.UST.CompanyEmployee.entity.Company;
import com.UST.CompanyEmployee.exception.IdNotFound;
import com.UST.CompanyEmployee.repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    private CompanyRepo companyRepo;

    @PostMapping("/addcompany")
    public ResponseEntity<Company>createCompany(@RequestBody Company company){
        return ResponseEntity.ok().body(companyRepo.save(company));
    }

    @GetMapping("/getcompany")
    public ResponseEntity <List<Company>> getAllCompany(){
        return ResponseEntity.ok().body(companyRepo.findAll());
    }

    @GetMapping("getcompany/{id}")
    public ResponseEntity <Company> getById(@PathVariable Long id) throws IdNotFound {
        Optional<Company> comps = companyRepo.findById(id);
        if(comps.isPresent()){
            return ResponseEntity.ok().body(companyRepo.findById(id).orElse(null));
        }
        else {
            throw new IdNotFound("id not found");
        }

    }

    @DeleteMapping("/deletecompany/{id}")
    public ResponseEntity<String>deleteById(@PathVariable Long id){
        companyRepo.deleteById(id);
        return ResponseEntity.ok().body("deleted");
    }

    @PutMapping("/updatecompany/{id}")
    public ResponseEntity<Company> updateById(@PathVariable Long id, @RequestBody Company company){
        Company old = null;
        Optional<Company> comps = companyRepo.findById(company.getId());
        old = comps.get();
        old.setId(id);
        old.setName(company.getName());
        companyRepo.save(old);
        return ResponseEntity.ok().body(old);


    }
}
