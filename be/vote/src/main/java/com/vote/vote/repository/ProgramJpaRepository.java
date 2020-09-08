package com.vote.vote.repository;

import com.vote.vote.db.dto.Program;

import javax.transaction.Transactional;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramJpaRepository extends JpaRepository<Program, Long> {

    public Program findById(int id);
    
    @Modifying
    @Transactional
    @Query("update Program set p_name =:name, p_image =:image, p_category =:category ,logo =:logo where program_id = :id ")
    void programUpdate(@Param("id") int program_id,
    					@Param("name") String name,
    					@Param("image") String image,
                        @Param("category") String category,
                        @Param("logo") String logo
                        );
                        

    
    
}
