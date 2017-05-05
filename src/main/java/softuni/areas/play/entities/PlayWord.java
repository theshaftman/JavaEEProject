package softuni.areas.play.entities;

import javax.persistence.*;

@Entity
@Table(name = "play_words")
public class PlayWord {

    private Integer id;

    private String playWord;

    private String playAnswer;

    public PlayWord() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "current_word", nullable = false)
    public String getPlayWord() {
        return playWord;
    }

    public void setPlayWord(String playWord) {
        this.playWord = playWord;
    }

    @Column(name = "current_answer", nullable = false)
    public String getPlayAnswer() {
        return playAnswer;
    }

    public void setPlayAnswer(String playAnswer) {
        this.playAnswer = playAnswer;
    }
}
