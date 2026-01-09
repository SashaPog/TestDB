package com.example.dbtest.jparepository;

import com.example.dbtest.dto.TaskResponseDto;
import com.example.dbtest.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositoryJpa extends JpaRepository<Task, Long> {

    List<Task> findAllByIdGreaterThanEqualAndIdLessThan(Long start, Long end);

    @Query("select t from Task t left join fetch t.submissions where t.id >= ?1 and t.id <= ?2")
    List<Task> findAllByIdJoinFetch(Long start, Long end);


    @Query("""
        select new com.example.dbtest.dto.TaskResponseDto(
          t.title,
          t.description,
          t.fileUrl,
          t.author.id,
          t.competition.id,
          null
        )
        from Task t
        where t.id between ?1 and ?2
        """)
    List<TaskResponseDto> findTasksOnly(Long start, Long end);

    @Query("""
    select t
    from Task t
    inner join fetch t.author
    inner join fetch t.competition
    where t.id between ?1 and ?2
""")
    List<Task> findAllByIdQuery(Long start, Long end);

}