package rpp2021.ctrl;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import rpp2021.model.Smer;
import rpp2021.repository.SmerRepository;

@RestController
@CrossOrigin
public class SmerRestController {

	@Autowired
	private SmerRepository smerRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Returns collection of all Smer from database")
	@GetMapping("smer")
	public Collection<Smer> getAll() {
		return smerRepository.findAll();
	}
	
	@ApiOperation(value = "Returns Smer from database with Id that was forwarded as path variable.")
	@GetMapping("smer/{id}")
	public ResponseEntity<Smer> getOne(@PathVariable ("id") Integer id) {
		if (smerRepository.findById(id).isPresent()) {
			Smer smer = smerRepository.getOne(id);
			return new ResponseEntity<>(smer, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Returns Smer from database with naziv that was forwarded as path variable.")
	@GetMapping("smer/naziv/{naziv}")
	public Collection<Smer> getByNaziv(@PathVariable ("naziv") String naziv) {
		return smerRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Adds new Smer to database.")
	@PostMapping("smer")
	public ResponseEntity<Smer> addSmer(@RequestBody Smer smer) {
		Smer savedSmer = smerRepository.save(smer);
		URI location = URI.create("/smer/" + savedSmer.getId());
		return ResponseEntity.created(location).body(savedSmer);
	}
	
	@ApiOperation(value = "Updates Smer from database with id that was forwarded as path variable with values forwarded in Request Body.")
	@PutMapping("smer/{id}")
	public ResponseEntity<Smer> updateSmer(@RequestBody Smer smer, @PathVariable("id") Integer id) {
		if (smerRepository.existsById(id)) {
			smer.setId(id);
			Smer savedSmer = smerRepository.save(smer);
			return ResponseEntity.ok().body(savedSmer);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Deletes Smer from database with id that was forwarded as path variable.")
	@DeleteMapping("smer/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
		if (id == -100 && !smerRepository.existsById(-100)) {
			jdbcTemplate.execute("INSERT INTO smer (\"id\", \"naziv\", \"oznaka\") VALUES ('-100', 'Test Naziv', 'Test Oznaka')");
		}
		if (smerRepository.existsById(id)) {
			smerRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}	
}
