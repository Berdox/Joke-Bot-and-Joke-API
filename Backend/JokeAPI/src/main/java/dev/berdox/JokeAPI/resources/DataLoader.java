package dev.berdox.JokeAPI.resources;

import dev.berdox.JokeAPI.joke.Joke;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class DataLoader {
    public List<Joke> loadData(String filePath) {
        List<Joke> listJoke = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scan = new Scanner(file);

            // Read file line by line until the end
            while (scan.hasNextLine()) {
                Joke joke = new Joke(scan.nextLine());
                listJoke.add(joke);
            }
            scan.close(); // Close the scanner to release resources
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listJoke;
    }
}
