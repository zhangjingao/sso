package org.wlgzs.website.util.Result;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjg
 * @date 2018/7/17 21:14
 * @Description 统一的返回结果
 */
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class Result<T> {

    //返回枚举代码
    private ResultCodeEnum resultCode;
    //返回信息
    private String message;
    //返回响应数据
    private T data;

    public Result (ResultCodeEnum resultCodeEnum) {
        this.resultCode = resultCodeEnum;
    }

    public Result (ResultCodeEnum resultCodeEnum, String message) {
        this.resultCode = resultCodeEnum;
        this.message = message;
    }

    @Override
    public String toString () {
        return JSON.toJSONString("resultCode"+":"+resultCode+","+
            "message"+":"+message+","+"data"+":"+data);
    }

}
