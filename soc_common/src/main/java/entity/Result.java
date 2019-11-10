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

    public Result(Boolean flag, StatusCode code, String message) {
        this.flag = flag;
        this.code = code.getCode();
        this.message = message;
    }

    public Result(Boolean flag, StatusCode code, String message, Object data) {
        this.flag = flag;
        this.code = code.getCode();
        this.message = message;
        this.data = data;
    }
}
