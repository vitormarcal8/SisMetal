import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AbreConexaoComBanco {
	public static void main(String[] args) {
	
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SisMetalPU");
		EntityManager manager = factory.createEntityManager();
		manager.close();
		
	
	}
}
