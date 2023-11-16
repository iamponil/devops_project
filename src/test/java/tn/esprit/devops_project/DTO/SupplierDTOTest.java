package tn.esprit.devops_project.DTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tn.esprit.devops_project.dto.SupplierDTO;
import tn.esprit.devops_project.entities.SupplierCategory;

import static org.assertj.core.api.Assertions.assertThat;

class SupplierDTOTest {

    @Test
    void testEquals() {
        // Arrange
        SupplierDTO supplierDTO1 = SupplierDTO.builder()
                .idSupplier(1L)
                .code("Code1")
                .label("Label1")
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .build();

        SupplierDTO supplierDTO2 = SupplierDTO.builder()
                .idSupplier(1L)
                .code("Code1")
                .label("Label1")
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .build();

        SupplierDTO differentSupplierDTO = SupplierDTO.builder()
                .idSupplier(2L)
                .code("Code2")
                .label("Label2")
                .supplierCategory(SupplierCategory.CONVENTIONNE)
                .build();

        // Act and Assert
        Assertions.assertThat(supplierDTO1).isEqualTo(supplierDTO2).isNotEqualTo(differentSupplierDTO);
    }

    @Test
    void testHashCode() {
        // Arrange
        SupplierDTO supplierDTO1 = SupplierDTO.builder()
                .idSupplier(1L)
                .code("Code1")
                .label("Label1")
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .build();

        SupplierDTO supplierDTO2 = SupplierDTO.builder()
                .idSupplier(1L)
                .code("Code1")
                .label("Label1")
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .build();

        SupplierDTO differentSupplierDTO = SupplierDTO.builder()
                .idSupplier(2L)
                .code("Code2")
                .label("Label2")
                .supplierCategory(SupplierCategory.CONVENTIONNE)
                .build();

        // Act and Assert
        assertThat(supplierDTO1.hashCode()).hasSameHashCodeAs(supplierDTO2.hashCode());
        assertThat(supplierDTO1.hashCode()).isNotEqualTo(differentSupplierDTO.hashCode());
    }
}
