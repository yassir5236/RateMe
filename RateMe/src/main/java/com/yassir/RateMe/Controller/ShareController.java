package com.yassir.RateMe.Controller;

import com.yassir.RateMe.Dto.Share.ShareRequestDTO;
import com.yassir.RateMe.Dto.Share.ShareResponseDTO;
import com.yassir.RateMe.Service.IShareService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/shares")
public class ShareController {

    private final IShareService shareService;

    @Autowired
    public ShareController(IShareService shareService) {
        this.shareService = shareService;
    }

    @PostMapping
    public ResponseEntity<ShareResponseDTO> createShare(@Valid @RequestBody ShareRequestDTO shareRequestDTO) {
        ShareResponseDTO createdShare = shareService.createShare(shareRequestDTO);
        return new ResponseEntity<>(createdShare, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShareResponseDTO> getShareById(@PathVariable Long id) {
        ShareResponseDTO share = shareService.getShareById(id);
        return ResponseEntity.ok(share);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShareResponseDTO> updateShare(
            @PathVariable Long id,
            @Valid @RequestBody ShareRequestDTO shareRequestDTO) {
        ShareResponseDTO updatedShare = shareService.updateShare(id, shareRequestDTO);
        return ResponseEntity.ok(updatedShare);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShare(@PathVariable Long id) {
        shareService.deleteShare(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ShareResponseDTO>> getAllShares() {
        List<ShareResponseDTO> shares = shareService.getAllShares();
        return ResponseEntity.ok(shares);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ShareResponseDTO>> getSharesByUserId(@PathVariable Long userId) {
        List<ShareResponseDTO> shares = shareService.getSharesByUserId(userId);
        return ResponseEntity.ok(shares);
    }


}



