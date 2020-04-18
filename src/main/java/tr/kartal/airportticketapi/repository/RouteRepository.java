package tr.kartal.airportticketapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tr.kartal.airportticketapi.model.Route;

@RepositoryRestResource
public interface RouteRepository extends JpaRepository<Route, Integer>, JpaSpecificationExecutor<Route> {

}
