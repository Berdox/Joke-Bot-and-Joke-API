package dev.berdox.JokeAPI.joke;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "jokes")
public class Joke {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "joke_sequence",
            sequenceName = "joke_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "joke_sequence"
    )
    private Long id;

    @Column(name = "joke")
    private String joke;

    public Joke() {}
    public Joke(String joke) {
        this.joke = joke;
    }
}
