package br.com.ana.cast;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ana.cast.service.PersonService;
import br.com.ana.cast.vo.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/person")
@Slf4j
@RequiredArgsConstructor
@Api(value = "Person")
public class PersonRestApi {

	@Autowired
	private PersonService personService;

	@ApiOperation(value = "persons of database")
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Person>> findAll() {
		return ResponseEntity.ok(personService.findAll());
	}

	@ApiOperation(value = "Create a person")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Person> create(@Valid @RequestBody Person Person) {
		return ResponseEntity.ok(personService.save(Person));
	}

	@ApiOperation(value = "get a person of database")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Person> findById(@PathVariable Long id) {
		Optional<Person> stock = personService.findById(id);
		if (!stock.isPresent()) {
			// log.error("Id " + id + " is not existed");
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(stock.get());
	}

	@ApiOperation(value = "get a person of database")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "/{id}")
	public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person Person) {
		if (!personService.findById(id).isPresent()) {
			// log.error("Id " + id + " is not existed");
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(personService.save(Person));
	}

	@ApiOperation(value = "remove a person from database")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (!personService.findById(id).isPresent()) {
			// log.error("Id " + id + " is not existed");
			ResponseEntity.badRequest().build();
		}

		personService.deleteById(id);

		return ResponseEntity.ok().build();
	}
}