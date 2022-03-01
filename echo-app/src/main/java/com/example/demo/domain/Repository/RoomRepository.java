package com.example.demo.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {


}
