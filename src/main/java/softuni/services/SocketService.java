package softuni.services;

import softuni.areas.characters.models.view.CharacterAjaxModel;
import softuni.areas.characters.models.view.CharacterPointsModel;

public interface SocketService {
     void sendCharUpdate(CharacterAjaxModel character);

    void sendPointsUpdate(CharacterPointsModel characterPoints);
}
