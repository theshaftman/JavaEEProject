package softuni.areas.play.services;

import softuni.areas.play.entities.PlayWord;
import softuni.areas.play.models.binding.PlayWordModel;

public interface PlayWordService {
    PlayWord getPlayWord();
    void checkAnswer(PlayWordModel playWordModel);
}
