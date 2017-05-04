package softuni.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import softuni.areas.characters.entities.Character;
import softuni.areas.characters.services.CharacterService;
import softuni.areas.tasks.entities.Task;
import softuni.areas.tasks.enums.Status;
import softuni.areas.tasks.models.view.CheckerViewModel;
import softuni.areas.tasks.services.TaskService;
import softuni.services.SocketService;

import java.util.Date;
import java.util.List;

@Component
public class TaskManager {
    private final TaskService taskService;
    private final CharacterService characterService;
    private final SocketService socketService;


    @Autowired
    public TaskManager(TaskService taskService, CharacterService characterService, SocketService socketService) {
        this.taskService = taskService;
        this.characterService = characterService;
        this.socketService = socketService;
    }

    @Scheduled(cron = "*/1 * * * * *")
    public void checkTasks() {
        List<CheckerViewModel> tasks = this.taskService.getActive();

        for (CheckerViewModel task : tasks) {
            if (!task.getEndDate().after(new Date())) {
                this.taskService.generateTaskOutcome(task.getId());
                this.socketService.sendCharUpdate(this.characterService.characterAjax(task.getId()));
            }
        }
    }

    @Scheduled(fixedRate = 300000)
    public void giveMoneyz() {
        for (Character character : this.characterService.getAll()) {
            int completedTasksCount = 0;
            int failedTasksCount = 0;

            for (Task task : character.getTasks()) {
                if (task.getStatus() == Status.FAILED) {
                    failedTasksCount++;
                } else if (task.getStatus() == Status.SUCCESS) {
                    completedTasksCount++;
                }
            }

            Long money = (completedTasksCount * 20L) - (failedTasksCount * 10L);

            character.addMoney(money);

            this.characterService.update(character);

            this.socketService.sendMoneyUpdate(this.characterService.characterMoney(character.getId()));
        }
    }
}
