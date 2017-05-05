package softuni;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import softuni.areas.tasks.entities.Task;
import softuni.areas.tasks.models.binding.CreateBindingModel;
import softuni.areas.tasks.models.view.TaskInfoModel;
import softuni.areas.tasks.repositories.TaskRepository;
import softuni.areas.tasks.services.TaskService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TasksApplicationTests {

	public static final long VALID_ID = 1;

	public static final long INVALID_ID = -1;

	public static final String MODEL = "BMX";

	public static final long EXPECTED_GEARS = 0;

	@Autowired
	private ModelMapper modelMapper;

	@MockBean
	private TaskRepository taskRepository;

	@Autowired
	private TaskService taskService;

	@Before
	public void setUp() throws Exception {
		Task task = new Task();
		task.setId(VALID_ID);
		task.setTitle(MODEL);
		when(this.taskRepository.findOne(VALID_ID)).thenReturn(task);
	}

	@Test
	public void findByIdGivenValidBike_ShouldReturnValidModel() throws Exception {
		//Act
		Task createBindingModel = this.taskService.getById(VALID_ID);

		//Assert Id
		assertEquals(java.util.Optional.ofNullable(VALID_ID), createBindingModel.getId());
		//Assert Title
		assertEquals(MODEL, createBindingModel.getTitle());
	}
}
