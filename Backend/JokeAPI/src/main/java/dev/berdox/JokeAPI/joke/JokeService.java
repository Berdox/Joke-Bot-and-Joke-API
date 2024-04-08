package dev.berdox.JokeAPI.joke;

import dev.berdox.JokeAPI.resources.DataLoader;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JokeService {
    private final JokeRepository jokeRepository;

    private final DataLoader dataLoader;

    @Autowired
    public JokeService(JokeRepository jokeRepository, DataLoader dataLoader) {
        this.jokeRepository = jokeRepository;
        this.dataLoader = dataLoader;
    }

    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    public Optional<Joke> getJoke(Long id) {
        return  jokeRepository.findById(id);
    }

    public Optional<Joke> randomJoke() {
        return  jokeRepository.findRandomRow();
    }

    public void addJoke(Joke joke) {
        Optional<Joke> jokeOptional = jokeRepository.findJokeByJoke(joke.getJoke());
        if (jokeOptional.isPresent()) {
            throw new IllegalStateException("Joke Taken");
        }
        jokeRepository.save(joke);
    }

    public void loadJokes(String filePath) {
        List<Joke> userList = this.dataLoader.loadData(filePath);
        jokeRepository.saveAll(userList);
    }

    @Transactional
    public void updateJoke(Long id, String jokeStr) {
        Joke joke = jokeRepository.findJokeByID(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Joke with id " + id + " doesn't exist to update"
                ));

        if (!jokeStr.isEmpty() && !Objects.equals(joke.getJoke(), jokeStr)) {
            joke.setJoke(jokeStr);
        }
    }

    public void deleteJoke(Long id) {
        if (!jokeRepository.existsById(id)) {
            throw new IllegalStateException("Joke doesn't exist");
        }
        jokeRepository.deleteById(id);
    }
}
