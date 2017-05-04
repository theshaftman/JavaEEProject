package softuni.areas.chat.models.view;

import java.util.Date;

public class MessageViewModel {
    private String message;

    private Long date;

    public MessageViewModel(String message, Long date) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
