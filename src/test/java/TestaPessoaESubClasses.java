import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.vitormarcal.sismetal.model.Funcionario;
import com.vitormarcal.sismetal.model.Pessoa;
import com.vitormarcal.sismetal.model.TipoPessoa;

public class TestaPessoaESubClasses {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SisMetalPU");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		Pessoa vitor = new Funcionario();
		
		vitor.setNome("Vítor Peneira Marçal");
		vitor.setEmail("vitormarcadl8@gmail.com");
		vitor.setTipoPessoa(TipoPessoa.JURIDICA);
		vitor.setDocumentoReceitaFederal("03490663101");
		manager.persist(vitor);
		
		manager.getTransaction().commit();

	}

}
