package com.barysevich.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class CompanyInfo extends AbstractPersistable<Long> {

    @Id
    @SequenceGenerator(name = "company_info_id_seq",
            sequenceName = "company_info_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "company_info_id_seq")
    private Long id;

    private String name;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.NONE)
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.NONE)
    private LocalDate endDate;

    public CompanyInfo() {
        //default constructor
    }

    public CompanyInfo(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
