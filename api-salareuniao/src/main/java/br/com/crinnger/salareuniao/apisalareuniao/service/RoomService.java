package br.com.crinnger.salareuniao.apisalareuniao.service;

import br.com.crinnger.salareuniao.apisalareuniao.exception.ResourceNotFoundException;
import br.com.crinnger.salareuniao.apisalareuniao.model.Room;
import br.com.crinnger.salareuniao.apisalareuniao.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.spi.ServiceRegistry;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    public Room save(Room newElement){
        return repository.save(newElement);
    }

    public Room getById(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not Found"));
    }

    public List<Room> getAll() throws ResourceNotFoundException {
        List<Room> retorno = repository.findAll();
        if(retorno.size()==0){
            throw  new ResourceNotFoundException("Not Found Rooms");
        }
        return repository.findAll();
    }

    public void delete(Long id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not Found"));
        repository.deleteById(id);
    }

    public Room update(Room newElement,Long id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not Found"));
        newElement.setId(id);
        return repository.save(newElement);
    }
}
