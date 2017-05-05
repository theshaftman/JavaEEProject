package softuni.areas.play.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.areas.play.entities.PlayWord;
import softuni.areas.play.models.binding.PlayWordModel;

public interface PlayWordRepository extends JpaRepository<PlayWord, Long> {
    //PlayWord getPlayWord();
    //void checkAnswer(PlayWordModel playWordModel);
}
