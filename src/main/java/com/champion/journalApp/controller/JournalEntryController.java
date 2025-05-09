package com.champion.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.champion.journalApp.entity.JournalEntry;
import com.champion.journalApp.service.JournalEntryService;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
	
	@Autowired
	private JournalEntryService journalEntryService;

	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<JournalEntry> list = journalEntryService.getAll();
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
		try {
			JournalEntry entry = journalEntryService.saveEntry(myEntry);
			return new ResponseEntity<>(entry, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<JournalEntry> getEntryById(@PathVariable Long id) {
		JournalEntry entry = journalEntryService.getById(id);
		if(entry != null) {
			return new ResponseEntity<JournalEntry>(entry, HttpStatus.OK);
		}
		else
			return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteEntryById(@PathVariable Long id) {
		journalEntryService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<?> updateEntryById(@PathVariable Long id, @RequestBody JournalEntry newEntry) {
		JournalEntry entry = journalEntryService.updateById(id, newEntry);
		return (entry != null ? new ResponseEntity<>(entry, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
