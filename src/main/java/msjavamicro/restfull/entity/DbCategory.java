package msjavamicro.restfull.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "db_category_v2")
public class DbCategory {
    @Id
    @UuidGenerator
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id_category")
    private String idCategory;
    @Column(name = "id_user")
    private String idUser;
    @Column(name = "category_name")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private DbUser dbUser;

    @OneToMany(mappedBy = "id_category")
    private List<DbTransaction> dbTransactions;
}
