
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import interfaces.AnswerFormatter;
import international.InternationalFormatter;
import org.junit.Test;


public class InternationalizedTests {

	@Test
	public void testEnglish() {
		AnswerFormatter subject = new InternationalFormatter(Locale.forLanguageTag("en-UK"));
		assertThat(subject.printGuessStatistics('c', 0), is(equalTo("There are no cs")));
		assertThat(subject.printGuessStatistics('d', 0), is(equalTo("There are no ds")));
		assertThat(subject.printGuessStatistics('k', 1), is(equalTo("There is 1 k")));
		assertThat(subject.printGuessStatistics('l', 3), is(equalTo("There are 3 ls")));
	}
	
	@Test
	public void testDanish() {
		AnswerFormatter subject = new InternationalFormatter(Locale.forLanguageTag("da-DK"));
		assertThat(subject.printGuessStatistics('c', 0), is(equalTo("Der er ingen c'er")));
		assertThat(subject.printGuessStatistics('d', 0), is(equalTo("Der er ingen d'er")));
		assertThat(subject.printGuessStatistics('k', 1), is(equalTo("Der er 1 k")));
		assertThat(subject.printGuessStatistics('l', 3), is(equalTo("Der er 3 l'er")));
	}

	@Test
	public void testDoesntCountOver1000() {
		AnswerFormatter danish = new InternationalFormatter(Locale.forLanguageTag("da-DK"));
		AnswerFormatter english = new InternationalFormatter(Locale.forLanguageTag("en-UK"));
		assertThat(danish.printGuessStatistics('l',1000), is(equalTo("Der er 1000 l'er")));
		assertThat(danish.printGuessStatistics('l',1001), is(equalTo("Der er over 1000 l'er")));
		assertThat(english.printGuessStatistics('l',1000), is(equalTo("There are 1000 ls")));
		assertThat(english.printGuessStatistics('l',1001), is(equalTo("There are over 1000 ls")));
	}

}
