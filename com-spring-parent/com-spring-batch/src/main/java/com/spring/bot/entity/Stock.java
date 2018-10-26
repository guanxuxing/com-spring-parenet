package com.spring.bot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Administrator on 2018-10-26.
 */
@XmlRootElement(name = "details")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Stock {

    private Long id;
    private String name;
    private Long count;
    private Long sale;
    private Integer version;


    /***
     * 重新toString方法，返回的信息即是到file的信息格式
     * @return
     */
    @Override
    public String toString () {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[")
                .append("id=").append(id).append(",")
                .append("name=").append(name).append(",")
                .append("count=").append(count).append(",")
                .append("sale=").append(sale).append(",")
                .append("version=").append(version)
                .append("]");
        return buffer.toString();

    }




}
