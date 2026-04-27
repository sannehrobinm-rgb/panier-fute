package com.panierfute.controller;

import com.panierfute.model.Alarme;
import com.panierfute.repository.AlarmeRepository;
import com.panierfute.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alarmes")
public class AlarmeController {

    private final AlarmeRepository alarmeRepository;
    private final UserRepository userRepository;

    public AlarmeController(AlarmeRepository alarmeRepository, UserRepository userRepository) {
        this.alarmeRepository = alarmeRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Alarme> getAlarmes(Authentication auth) {
        var user = userRepository.findByEmail(auth.getName()).orElseThrow();
        return alarmeRepository.findByUserId(user.getId());
    }

    @PostMapping
    public ResponseEntity<Alarme> createAlarme(@RequestBody Alarme alarme, Authentication auth) {
        var user = userRepository.findByEmail(auth.getName()).orElseThrow();
        alarme.setUser(user);
        return ResponseEntity.ok(alarmeRepository.save(alarme));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alarme> updateAlarme(@PathVariable Long id, @RequestBody Alarme updated) {
        var alarme = alarmeRepository.findById(id).orElseThrow();
        alarme.setSeuil(updated.getSeuil());
        alarme.setType(updated.getType());
        alarme.setCanal(updated.getCanal());
        alarme.setActive(updated.getActive());
        return ResponseEntity.ok(alarmeRepository.save(alarme));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlarme(@PathVariable Long id) {
        alarmeRepository.deleteById(id);
        return ResponseEntity.ok("Alarme supprimée");
    }
}
