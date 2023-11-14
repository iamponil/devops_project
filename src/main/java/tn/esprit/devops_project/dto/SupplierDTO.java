package tn.esprit.devops_project.dto;


import lombok.*;
import tn.esprit.devops_project.entities.SupplierCategory;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierDTO {

    Long idSupplier;
    String code;
    String label;
    SupplierCategory supplierCategory;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SupplierDTO supplierDTO = (SupplierDTO) obj;

        // Compare internal fields for equality
        return Objects.equals(idSupplier, supplierDTO.idSupplier)
                && Objects.equals(code, supplierDTO.code)
                && Objects.equals(label, supplierDTO.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSupplier, code, label, supplierCategory);
    }
}
