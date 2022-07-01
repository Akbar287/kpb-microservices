package com.kpb.authservice.web;

import com.kpb.authservice.dto.akun.AkunBankDetailResponseDTO;
import com.kpb.authservice.dto.akun.AkunBankRequestDTO;
import com.kpb.authservice.service.AkunBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/auth/api/akun")
public class AkunBankResource {

    @Autowired
    private AkunBankService akunBankService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<AkunBankDetailResponseDTO>> listResponseEntity(@PathVariable("userId") Long userId) {
        List<AkunBankDetailResponseDTO> dto = akunBankService.findAkun(userId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<AkunBankDetailResponseDTO> createAkun (@PathVariable("userId") Long userId, @RequestBody AkunBankRequestDTO dto) throws URISyntaxException {
        AkunBankDetailResponseDTO akunBankDetailResponseDTO = akunBankService.createAkun(userId, dto);
        return ResponseEntity.created(new URI("/akun")).body(akunBankDetailResponseDTO);
    }

    @PutMapping("/{akunId}")
    public ResponseEntity<Void> updateAkun (@PathVariable("akunId") Long akunId, @RequestBody AkunBankRequestDTO dto) {
         akunBankService.updateAkun(akunId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{akunId}")
    public ResponseEntity<AkunBankDetailResponseDTO> deleteAkun (@PathVariable("akunId") Long akunId)  {
         akunBankService.deleteAkun(akunId);
        return ResponseEntity.noContent().build();
    }
}
