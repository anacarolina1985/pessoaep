package br.com.ana.cast.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ana.cast.vo.Person;

public interface PersonRespository extends JpaRepository<Person, Long> {
}