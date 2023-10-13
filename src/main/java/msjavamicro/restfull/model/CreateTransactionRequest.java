package msjavamicro.restfull.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTransactionRequest {

    @JsonIgnore
    @NotBlank
    private String categoryId;

    @NotBlank
    private String type;

    private Integer amount;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdDate;
}
