import org.junit.Assert;
import org.mockito.Mockito;

import com.mercadolivre.model.Mutant;
import com.mercadolivre.repository.MutantRepository;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * MutantTest.
 */
public class MutantTest {

	private Mutant mutant;
	private MutantRepository mutantRepository;

	/**
	 * Parametriza o Mutant
	 */
	@Given("^Parametriza o Mutant")
	public void configureImageAndRepository() {

		mutantRepository = Mockito.mock(MutantRepository.class);
		mutant = new Mutant();
		mutant.setDnaSequence("ATAtatCAGTGCTTATGGATAtatCCCCTATCACTG");

	}

	/**
	 * Salva o Mutante 'mockado'
	 */
	@When("^Salva o mutante mockado")
	public void saveMutant() {

		Mockito.when(mutantRepository.save(mutant)).thenReturn(mutant);

	}

	/**
	 * Verifica se tem nome no Mutante salvo
	 */
	@Then("^Verifica se tem DNA no Mutante salvo")
	public void verifyDnaOnMutant() {

		boolean temValor = !"".equals(mutant.getDnaSequence());

		Assert.assertEquals(temValor, true);

	}
}