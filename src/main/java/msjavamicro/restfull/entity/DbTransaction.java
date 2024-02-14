package msjavamicro.restfull.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "db_transaction_v2")
public class DbTransaction {
    @Id
    @UuidGenerator
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id_transaction")
    private String idTransaction;
    @Column(name = "id_category")
    private String idCategory;
    @Column(name = "id_user")
    private String idUser;
    private String type;
    private Integer amount;
    private String description;
    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private DbUser dbUser;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    private DbCategory dbCategory;
}
