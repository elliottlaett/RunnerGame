package ninjagame;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.util.LinkedList;
import org.junit.Test;

public class HighscoreTest {
	
	@Test
	public void readFileShouldReurnLinkedList() {
		assertThat(Highscore.readFile(), instanceOf(LinkedList.class));
	}

	@Test(timeout=10)	
	public void readFileShouldBeFast(){
		
		Highscore.readFile();
	}
}
