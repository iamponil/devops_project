package tn.esprit.devops_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findBySupplierCategory(SupplierCategory category);

}
