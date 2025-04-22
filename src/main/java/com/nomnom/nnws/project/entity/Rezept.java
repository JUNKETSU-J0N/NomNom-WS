package com.nomnom.nnws.project.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
    @Table(name = "rezept")
    @Data
    public class Rezept {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private String beschreibung;
}
