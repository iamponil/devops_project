package tn.esprit.devops_project.services.services_interface;

import tn.esprit.devops_project.dto.SupplierDTO;
import tn.esprit.devops_project.entities.Supplier;

import java.util.List;

public interface ISupplierService {

	List<Supplier> retrieveAllSuppliers();

	Supplier addSupplier(SupplierDTO supplier);

	void deleteSupplier(Long id);

	Supplier updateSupplier(Supplier supplier);

	Supplier retrieveSupplier(Long id);

}
