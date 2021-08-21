package br.com.crinnger.salareuniao.apisalareuniao.repository;

import br.com.crinnger.salareuniao.apisalareuniao.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
}
