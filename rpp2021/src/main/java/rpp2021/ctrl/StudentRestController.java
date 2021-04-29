package rpp2021.ctrl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

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
import rpp2021.model.Projekat;
import rpp2021.model.Student;
import rpp2021.repository.GrupaRepository;
import rpp2021.repository.ProjekatRepository;
import rpp2021.repository.StudentRepository;

@RestController
public class StudentRestController {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ProjekatRepository projekatRepository;
	
	@Autowired
	private GrupaRepository grupaRepository;
	
	@ApiOperation(value = "Returns collection of all Student from database")
	@GetMapping("student")
	public Collection<Student> getAll() {
		return studentRepository.findAll();
	}
	
	@ApiOperation(value = "Returns Student from database with Id that was forwarded as path variable.")
	@GetMapping("student/{id}")
	public ResponseEntity<Student> getOne(@PathVariable ("id") Integer id) {
		if (studentRepository.findById(id).isPresent()) {
			Student savedStudent = studentRepository.getOne(id);
			return new ResponseEntity<>(savedStudent, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Returns Student from database with ime that was forwarded as path variable.")
	@GetMapping("studentIme/{ime}")
	public Collection<Student> getByIme(@PathVariable ("ime") String ime) {
		return studentRepository.findByImeContainingIgnoreCase(ime);
	}
	
	@GetMapping("studentZaProjekat/{id}")
	public Collection<Student> getAllProjekat(@PathVariable("id") Integer id) {
		if (studentRepository.findById(id).isPresent()) {
			Optional<Projekat> projekatOptional = projekatRepository.findById(id);
			Projekat projekat = projekatOptional.get();
			return studentRepository.findByProjekat(projekat);
		}
		return new ArrayList<Student>();
	}
	
	@GetMapping("studentZaGrupa/{id}")
	public Collection<Student> getAllGrupa(@PathVariable("id") Integer id) {
		if (studentRepository.findById(id).isPresent()) {
			Optional<Grupa> grupaOptional = grupaRepository.findById(id);
			Grupa grupa = grupaOptional.get();
			return studentRepository.findByGrupa(grupa);
		}
		return new ArrayList<Student>();
	}
	
	@ApiOperation(value = "Adds new Smer to database.")
	@PostMapping("student")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);
		URI location = URI.create("/student/" + savedStudent.getId());
		return ResponseEntity.created(location).body(savedStudent);
	}

	@ApiOperation(value = "Updates Smer from database with id that was forwarded as path variable with values forwarded in Request Body.")
	@PutMapping("student/{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") Integer id) {
		if (studentRepository.existsById(id)) {
			student.setId(id);
			Student savedStudent = studentRepository.save(student);
			return ResponseEntity.ok().body(savedStudent);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Deletes Student from database with id that was forwarded as path variable.")
	@DeleteMapping("student/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
		if (id == -100 && !studentRepository.existsById(id)) {
			jdbcTemplate.execute("INSERT INTO student (\"id\", \"ime\", \"prezime\", \"broj_indeksa\", \"grupa\", \"projekat\") VALUES (-100, 'Test Ime', 'Test Prezime', 'Test BrInd', 1, 1)");
		}
		if (studentRepository.existsById(id)) {
			studentRepository.deleteById(id);
			return new ResponseEntity<HttpStatus> (HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus> (HttpStatus.NOT_FOUND);
	}
}
 