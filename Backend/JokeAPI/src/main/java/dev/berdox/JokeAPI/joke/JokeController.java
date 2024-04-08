package dev.berdox.JokeAPI.joke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class JokeController {

    private JokeService jokeService;

    @Autowired
    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
        //jokeService.loadJokes("src/main/java/dev/berdox/JokeAPI/resources/CleanJokes.txt");
    }

    @GetMapping("joke/{id}")
    public Optional<Joke> getJokeById(@PathVariable Long id) {
        return jokeService.getJoke(id);
    }

    @GetMapping("all_jokes")
    public List<Joke> getJoke() {
        return jokeService.getAllJokes();
    }

    @GetMapping("random")
    public Optional<Joke> randomJoke() {
        return jokeService.randomJoke();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("joke")
    public void newJoke(@RequestBody Joke joke) {
        jokeService.addJoke(joke);
    }

    @PutMapping("joke/{id}")
    public void updateJoke(@PathVariable Long id, @RequestParam(required = true) String jokeStr) {
        jokeService.updateJoke(id, jokeStr);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("joke/{id}")
    public void deleteJoke(@PathVariable Long id) {
        jokeService.deleteJoke(id);
    }
}
