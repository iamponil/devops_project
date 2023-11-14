package tn.esprit.devops_project.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devops_project.dto.SupplierDTO;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.services.services_interface.ISupplierService;
import java.util.List;


@RestController
@AllArgsConstructor
public class SupplierController {

	ISupplierService supplierService;

	@GetMapping("/supplier")
	public List<Supplier> getSuppliers() {
		return supplierService.retrieveAllSuppliers();
	}

	@GetMapping("/supplier/{supplierId}")
	public Supplier retrieveSupplier(@PathVariable Long supplierId) {
		return supplierService.retrieveSupplier(supplierId);
	}

	@PostMapping("/supplier")
	public Supplier addSupplier(@RequestBody SupplierDTO supplier) {
		return supplierService.addSupplier(supplier);
	}

	@DeleteMapping("/supplier/{supplierId}")
	public void removeFournisseur(@PathVariable Long supplierId) {
		supplierService.deleteSupplier(supplierId);
	}



}
