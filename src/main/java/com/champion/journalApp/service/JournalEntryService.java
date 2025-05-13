package com.champion.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champion.journalApp.entity.JournalEntry;
import com.champion.journalApp.entity.User;
import com.champion.journalApp.repository.JournalEntryRepository;

import jakarta.transaction.Transactional;

@Service
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalEntryRepository;
	@Autowired
	private UserService userService;
	
	@Transactional
	public JournalEntry saveEntry(JournalEntry entry, String username) {
		User user = userService.getByUsername(username);
		entry.setDate(LocalDateTime.now());
		entry.setUser(user);
		journalEntryRepository.save(entry);
		user.getJournalEntries().add(entry);
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
	@Transactional
	public JournalEntry updateById(Long id, JournalEntry newEntry) {
		JournalEntry oldEntry = getById(id);
		if(oldEntry != null) {
			oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
			oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
//			saveEntry(oldEntry);
			return oldEntry;
		}
		else
			return null;
	}
}
