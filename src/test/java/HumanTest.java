import org.junit.Assert;
import org.mockito.Mockito;

import com.mercadolivre.model.Human;
import com.mercadolivre.repository.HumanRepository;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * HumanTest.
 */
public class HumanTest {

	private Human human;
	private HumanRepository humanRepository;

	/**
	 * Parametriza o Human
	 */
	@Given("^Parametriza o Human")
	public void configureImageAndRepository() {

		humanRepository = Mockito.mock(HumanRepository.class);
		human = new Human();
		human.setDnaSequence("ATAtatCAGTGCTTATGGATAtatCCCCTATCACTG");

	}

	/**
	 * Salva o Humane 'mockado'
	 */
	@When("^Salva o humane mockado")
	public void saveHuman() {

		Mockito.when(humanRepository.save(human)).thenReturn(human);

	}

	/**
	 * Verifica se tem nome no Humane salvo
	 */
	@Then("^Verifica se tem DNA no Human salvo")
	public void verifyDnaOnHuman() {

		boolean temValor = !"".equals(human.getDnaSequence());

		Assert.assertEquals(temValor, true);

	}
}