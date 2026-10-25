package com.codymitra.shared_service.modules.document.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.document_status.entities.DocumentStatusEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "documents")
@Table(name = "documents")
@EqualsAndHashCode(callSuper = true)
public class DocumentEntity extends BaseEntity {

    @Column(name = "name",unique = true,nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_status_id")
    private DocumentStatusEntity documentStatus;

    // file type
    //document type

    @Column(name = "active")
    private Boolean active;
}
