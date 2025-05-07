package com.champion.journalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.champion.journalApp.JournalEntry;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

	private Map<Long, JournalEntry> journalEntries = new HashMap<>();
	
	@GetMapping
	public List<JournalEntry> getAll(){
		return new ArrayList<>(journalEntries.values());
	}
	
	@PostMapping
	public boolean createEntry(@RequestBody JournalEntry myEntry) {
		journalEntries.put(myEntry.getId(), myEntry);
		return true;
	}
	
	@GetMapping("/id/{id}")
	public JournalEntry getEntryById(@PathVariable Long id) {
		return journalEntries.get(id);
	}
	
	@DeleteMapping("/id/{id}")
	public JournalEntry deleteEntryById(@PathVariable Long id) {
		return journalEntries.remove(id);
	}
	
	@PutMapping("/id/{id}")
	public JournalEntry updateEntryById(@PathVariable Long id, @RequestBody JournalEntry entry) {
		return journalEntries.put(id, entry);
	}
}
