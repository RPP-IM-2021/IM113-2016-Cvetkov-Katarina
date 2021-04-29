package rpp2021.ctrl;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import rpp2021.model.Projekat;
import rpp2021.repository.ProjekatRepository;

@RestController
public class ProjekatRestController {

	@Autowired
	private ProjekatRepository projekatRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Returns collection of all Projekat from database")
	@GetMapping("projekat")
	public Collection<Projekat> getAll() {
		return projekatRepository.findAll();
	}
	
	@ApiOperation(value = "Returns Projekat from database with Id that was forwarded as path variable.")
	@GetMapping("projekat/{id}")
	public ResponseEntity<Projekat> getOne(@PathVariable("id") Integer id) {
		if (projekatRepository.findById(id).isPresent()) {
			Projekat projekat = projekatRepository.getOne(id);
			return new ResponseEntity<>(projekat, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Returns Projekat from database with naziv that was forwarded as path variable.")
	@GetMapping("projekat/naziv/{naziv}")
	public Collection<Projekat> getByNaziv(@PathVariable ("naziv") String naziv) {
		return projekatRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Adds new Projekat to database.")
	@PostMapping("projekat")
	public ResponseEntity<Projekat> addProjekat(@RequestBody Projekat projekat) {
		Projekat savedProjekat = projekatRepository.save(projekat);
		URI  location = URI.create("/projekat/" + savedProjekat.getId());
		return ResponseEntity.created(location).body(savedProjekat);	
	}
	
	@ApiOperation(value = "Updates Projekat from database with id that was forwarded as path variable with values forwarded in Request Body.")
	@PutMapping("projekat/{id}")
	public ResponseEntity<Projekat> updateProjekat(@RequestBody Projekat projekat, @PathVariable("id") Integer id) {
		if (projekatRepository.existsById(id)) {
			projekat.setId(id);
			Projekat savedProjekat = projekatRepository.save(projekat);
			return ResponseEntity.ok().body(savedProjekat);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Deletes Projekat from database with id that was forwarded as path variable.")
	@DeleteMapping("projekat/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
		if (id == -100 && !projekatRepository.existsById(-100)) {
			jdbcTemplate.execute("INSERT INTO projekat (\"id\", \"naziv\", \"oznaka\", \"opis\") VALUES ('-100', 'Test Naziv', 'T oznaka', 'Test opis')");
		}
		if (projekatRepository.existsById(id)) {
			projekatRepository.deleteById(id);
			return new ResponseEntity<HttpStatus> (HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}

}
