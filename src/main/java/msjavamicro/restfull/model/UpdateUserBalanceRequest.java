package msjavamicro.restfull.model;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserBalanceRequest {
    
    @Min(value = 10000, message = "Top Minimal 10000")
    private Integer balance;

}
