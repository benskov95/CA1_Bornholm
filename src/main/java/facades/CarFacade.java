/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CarDTO;
import entities.Car;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Tha-Y
 */
public class CarFacade {
    
    private static CarFacade instance;
    private static EntityManagerFactory emf;
    
    
       public static CarFacade getCarFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<CarDTO> getAllCars () {
        
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT c FROM Car c");
        List<Car> carslist = query.getResultList();
        List<CarDTO> carDTOlist = new ArrayList<>();
        
        for (Car cars : carslist) {
            carDTOlist.add(new CarDTO(cars));
            
        }
        return carDTOlist;
        
        
    } 
    public void populateDB (){
        
       EntityManager em = emf.createEntityManager();
       
       try{
          em.getTransaction().begin();
          em.persist(new Car(1997,"Ford","E350",3000,"Pelle Rasmussen", 5));
          em.persist(new Car(1999,"Chevy","Venture",4900,"Mari Haugen", 1));
          em.persist(new Car(2000,"Chevy","Venture",5000,"John Snow", 4));
          em.persist(new Car(1996,"Jeep","Grand Cherokee",4799,"Mari Haugen", 3));
          em.persist(new Car(2005,"Volvo","V70",44799,"Martin Karlsen", 2));
          em.persist(new Car(2018,"Tesla","S",65000,"Henning Madsen", 6));
          em.getTransaction().commit();
           
       }finally{
           em.close();
       }
    }
}
    
    
    

