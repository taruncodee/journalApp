package com.champion.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champion.journalApp.entity.JournalEntry;
import com.champion.journalApp.repository.JournalEntryRepository;

@Service
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
	public JournalEntry saveEntry(JournalEntry entry) {
		entry.setDate(LocalDateTime.now());
		journalEntryRepository.save(entry);
		return entry;
	}
	
	public List<JournalEntry> getAll(){
		return journalEntryRepository.findAll()	;
	}
	
	public JournalEntry getById(Long id) {
		return journalEntryRepository.findById(id).orElse(null);
	}
	
	public void deleteById(Long id) {
		journalEntryRepository.deleteById(id);
	}
}
