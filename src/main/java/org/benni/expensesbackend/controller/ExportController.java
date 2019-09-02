package org.benni.expensesbackend.controller;

import java.text.SimpleDateFormat;

import org.benni.expensesbackend.model.dto.MovementDTO;
import org.benni.expensesbackend.services.AccountsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExportController {

	@Autowired
	private AccountsServices accountsServices;

	@GetMapping("/api/accounts/{accountId}/movements/export")
	public ResponseEntity<ByteArrayResource> download(@PathVariable(name = "accountId") int accountId) {
		
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat df = (SimpleDateFormat) SimpleDateFormat.getInstance();
		df.applyPattern("dd.MM.yyy");
		
		char separator = ',';
		
		accountsServices.findAccountForCurrentUser(accountId).getMovements().stream()
				.map(m -> new MovementDTO(m))
				.sorted()
				.forEach(m -> sb
						.append(m.getId())
						.append(separator)
						.append(m.getDescription())
						.append(separator)
						.append(m.getAmountInCents() / 100.00)
						.append(separator)
						.append(df.format(m.getDate()))
						.append("\n")
					);

		byte[] bytes = sb.toString().getBytes();
		ByteArrayResource resource = new ByteArrayResource(bytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=export.csv");

		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(bytes.length)
				.contentType(MediaType.parseMediaType("text/csv"))
				.body(resource);
	}

}
