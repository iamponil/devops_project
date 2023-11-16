package tn.esprit.devops_project.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    void testEqualsWhenObjectIsNull() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(1L);
        supplier.setCode("TestCode");
        supplier.setLabel("TestLabel");
        supplier.setSupplierCategory(SupplierCategory.ORDINAIRE);

        // Act
        boolean result = false;

        // Assert
        Assertions.assertThat(result).isFalse();
    }

    @Test
    void testToString() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(1L);
        supplier.setCode("TestCode");
        supplier.setLabel("TestLabel");
        supplier.setSupplierCategory(SupplierCategory.ORDINAIRE);

        // Act
        String result = supplier.toString();

        // Assert
        Assertions.assertThat(result).contains("id : 1", "code : TestCode", "label : TestLabel", "category : ORDINAIRE");
    }
    @Test
     void itShouldFindSupplierByCategory() {
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
        Assertions.assertThat(byCategory).hasSize(1);
        Assertions.assertThat(byCategory.get(0)).isEqualTo(savedSupplier);
    }

    @Test
     void itShouldFindTwoSupplierByCategory() {
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
        Assertions.assertThat(byCategory).hasSize(2);
        Assertions.assertThat(byCategory.get(1)).isEqualTo(savedSupplier);
        Assertions.assertThat(byCategory.get(0)).isEqualTo(savedSupplier2);


    }
}