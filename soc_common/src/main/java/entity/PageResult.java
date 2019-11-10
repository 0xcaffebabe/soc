package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author MY
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    private Long total;

    @JsonProperty("rows")
    private List<T> data;

}
