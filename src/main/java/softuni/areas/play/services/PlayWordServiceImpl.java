package softuni.areas.play.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.areas.play.entities.PlayWord;
import softuni.areas.play.models.binding.PlayWordModel;
import softuni.areas.play.repositories.PlayWordRepository;

@Service
public class PlayWordServiceImpl implements PlayWordService {
    private final PlayWordRepository playWordRepository;

    @Autowired
    public PlayWordServiceImpl(PlayWordRepository playWordRepository) {
        this.playWordRepository = playWordRepository;
    }

    @Override
    public PlayWord getPlayWord() {
        return null;
    }

    @Override
    public void checkAnswer(PlayWordModel playWordModel) {

    }
}
