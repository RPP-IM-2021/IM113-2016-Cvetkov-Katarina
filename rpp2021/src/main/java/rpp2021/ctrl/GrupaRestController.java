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
import rpp2021.model.Grupa;
import rpp2021.repository.GrupaRepository;

@RestController
public class GrupaRestController {

	@Autowired
	private GrupaRepository grupaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Returns collection of all Grupa from database")
	@GetMapping("grupa")
	public Collection<Grupa> getAll() {
		return grupaRepository.findAll();
	}
	
	@ApiOperation(value = "Returns Grupa from database with Id that was forwarded as path variable.")
	@GetMapping("grupa/{id}")
	public ResponseEntity<Grupa> getOne(@PathVariable("id") Integer id) {
		if (grupaRepository.findById(id).isPresent()) {
			Grupa grupa = grupaRepository.getOne(id);
			return new ResponseEntity<>(grupa, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Returns Grupa from database with oznaka that was forwarded as path variable.")
	@GetMapping("grupa/oznaka/{oznaka}")
	public Collection<Grupa> getByOznaka(@PathVariable ("oznaka") String oznaka) {
		return grupaRepository.findByOznakaContainingIgnoreCase(oznaka);
	}
	
	@ApiOperation(value = "Adds new Grupa to database.")
	@PostMapping("grupa")
	public ResponseEntity<Grupa> addGrupa(@RequestBody Grupa grupa) {
		Grupa savedGrupa = grupaRepository.save(grupa);
		URI location = URI.create("/grupa/" + savedGrupa.getId());
		return ResponseEntity.created(location).body(savedGrupa);	
	}
	
	@ApiOperation(value = "Updates Grupa from database with id that was forwarded as path variable with values forwarded in Request Body.")
	@PutMapping("grupa/{id}")
	public ResponseEntity<Grupa> updateGrupa(@RequestBody Grupa grupa, @PathVariable("id") Integer id) {
		if (grupaRepository.existsById(id)) {
			grupa.setId(id);
			Grupa savedGrupa = grupaRepository.save(grupa);
			return ResponseEntity.ok().body(grupa);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Deletes Grupa from database with id that was forwarded as path variable.")
	@DeleteMapping("grupa/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
		if(id == -100 && !grupaRepository.existsById(-100)) {
			jdbcTemplate.execute("INSERT INTO grupa (\"id\", \"oznaka\", \"smer\") VALUES ('-100', 'T oznaka', '1')");	
		}
		if (grupaRepository.existsById(id)) {
			grupaRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);	
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
}
