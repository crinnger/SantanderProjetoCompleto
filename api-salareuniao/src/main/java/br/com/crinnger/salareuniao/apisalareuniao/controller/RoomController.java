package br.com.crinnger.salareuniao.apisalareuniao.controller;

import br.com.crinnger.salareuniao.apisalareuniao.exception.ResourceNotFoundException;
import br.com.crinnger.salareuniao.apisalareuniao.model.Room;
import br.com.crinnger.salareuniao.apisalareuniao.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService service;

    @PostMapping
    public ResponseEntity<Room> create(@Validated @RequestBody Room novo){
        return ResponseEntity.ok(service.save(novo));
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAll() throws ResourceNotFoundException {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<Room> update(@Validated @RequestBody Room novo,@PathVariable("roomId") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<Room>(service.update(novo,id), HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getById(@PathVariable("roomId") Long id) throws NoSuchElementException, ResourceNotFoundException {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("roomId") Long id) throws ResourceNotFoundException {
        service.delete(id);
    }
}
