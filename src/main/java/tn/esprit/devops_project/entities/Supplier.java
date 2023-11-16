package tn.esprit.devops_project.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSupplier;
	String code;
	String label;
	@Enumerated(EnumType.STRING)
	SupplierCategory supplierCategory;


	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		Supplier supplier = (Supplier) obj;

		// Compare internal fields for equality
		return Objects.equals(idSupplier, supplier.idSupplier)
				&& Objects.equals(code, supplier.code)
				&& Objects.equals(label, supplier.label);
	}
	@Override
	public String toString() {
		return "[ id : "+idSupplier+" | code : "+code+" | label : "+label+" | category : "+supplierCategory+"]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSupplier, code, label, supplierCategory);
	}
}