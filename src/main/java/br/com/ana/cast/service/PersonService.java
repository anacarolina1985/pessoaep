package br.com.ana.cast.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ana.cast.repository.PersonRespository;
import br.com.ana.cast.vo.Person;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

	@Autowired
	private PersonRespository personRespository;

	public List<Person> findAll() {
		return personRespository.findAll();
	}

	public Optional<Person> findById(Long id) {
		return personRespository.findById(id);
	}

	public Person save(Person stock) {
		return personRespository.save(stock);
	}

	public void deleteById(Long id) {
		personRespository.deleteById(id);
	}
}