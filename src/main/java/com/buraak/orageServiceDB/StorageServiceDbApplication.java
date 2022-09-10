package com.buraak.orageServiceDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RestController
//@RequestMapping("/image")
public class StorageServiceDbApplication {
	/*@Autowired
	private StorageService storageService;

	@PostMapping
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
		String uploadImage = storageService.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName){
		byte[] downloadImage = storageService.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(downloadImage);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(StorageServiceDbApplication.class, args);
	}

}
