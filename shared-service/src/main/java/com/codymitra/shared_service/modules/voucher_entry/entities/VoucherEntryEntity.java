package com.codymitra.shared_service.modules.voucher_entry.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "voucher_entries")
@Table(name = "voucher_entries")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class VoucherEntryEntity extends BaseEntity {

}
