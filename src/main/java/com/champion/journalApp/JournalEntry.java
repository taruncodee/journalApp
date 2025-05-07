package com.champion.journalApp;

import lombok.Data;

@Data
public class JournalEntry {

	private Long id;
	private String title;
	private String content;
}
