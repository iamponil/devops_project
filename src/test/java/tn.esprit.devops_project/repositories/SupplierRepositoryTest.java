package tn.esprit.devops_project.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;



import java.util.List;
@SpringBootTest
@ExtendWith(SpringExtension.class)
class SupplierRepositoryTest {

    @Autowired
    private SupplierRepository supplierRepository;
    @Test
    public void itShouldFindSupplierByCategory() {
        //Arrange
        Supplier supplier = Supplier.builder()
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .label("Test Supplier")
                .code("Supply n 001")
                .build();
        Supplier savedSupplier = supplierRepository.save(supplier);
        //Act
        List<Supplier> byCategory = supplierRepository.findBySupplierCategory(SupplierCategory.ORDINAIRE);
        //Assert
        Assertions.assertThat(byCategory.size()).isEqualTo(1);
        Assertions.assertThat(byCategory.get(0)).isEqualTo(savedSupplier);
    }

    @Test
    public void itShouldFindTwoSupplierByCategory() {
        //Arrange
        Supplier supplier = Supplier.builder()
                .supplierCategory(SupplierCategory.CONVENTIONNE)
                .label("Test Supplier")
                .code("Supply n 001")
                .build();

        Supplier supplier2 = Supplier.builder()
                .idSupplier(1L)
                .supplierCategory(SupplierCategory.CONVENTIONNE)
                .label("Test Supplier")
                .code("Supply n 001")
                .build();
        Supplier savedSupplier = supplierRepository.save(supplier);
        Supplier savedSupplier2 = supplierRepository.save(supplier2);
        //Act
        List<Supplier> byCategory = supplierRepository.findBySupplierCategory(SupplierCategory.CONVENTIONNE);
        //Assert
        Assertions.assertThat(byCategory.size()).isEqualTo(2);
        Assertions.assertThat(byCategory.get(1)).isEqualTo(savedSupplier);
        Assertions.assertThat(byCategory.get(0)).isEqualTo(savedSupplier2);


    }
}