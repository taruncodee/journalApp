package com.champion.journalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<JournalEntry> getAll(){
		return journalEntryService.getAll();
	}
	
	@PostMapping
	public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
		return journalEntryService.saveEntry(myEntry);
	}
	
	@GetMapping("/id/{id}")
	public JournalEntry getEntryById(@PathVariable Long id) {
		return journalEntryService.getById(id);
	}
	
	@DeleteMapping("/id/{id}")
	public boolean deleteEntryById(@PathVariable Long id) {
		journalEntryService.deleteById(id);
		return true;
	}
	
	@PutMapping("/id/{id}")
	public JournalEntry updateEntryById(@PathVariable Long id, @RequestBody JournalEntry entry) {
		return null;
	}
}
