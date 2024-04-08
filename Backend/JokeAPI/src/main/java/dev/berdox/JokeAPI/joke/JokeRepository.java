package dev.berdox.JokeAPI.joke;

import dev.berdox.JokeAPI.joke.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {

    @Query("SELECT j FROM Joke j WHERE j.id = ?1")
    Optional<Joke> findJokeByID(Long id);

    @Query("SELECT j FROM Joke j WHERE j.joke = :jokeStr")
    Optional<Joke> findJokeByJoke(@Param("jokeStr") String jokeStr);

    @Query(value = "SELECT * FROM jokes ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Joke> findRandomRow();
}
