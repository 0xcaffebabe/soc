package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MY
 * @date 2019/11/9 16:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Boolean flag;

    private Integer code;

    private String message;

    private Object data;


}
