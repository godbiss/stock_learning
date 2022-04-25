package com.zhu.api_comment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * name
     */
    private String name;
    /**
     * content
     */
    private String content;
    /**
     * info
     */
    private String info;
    /**
     * price
     */
    private BigDecimal price;
    /**
     * cover
     */
    private String cover;
    /**
     * createtime
     */
    private Date createtime;
    /**
     * updatetime
     */
    private Date updatetime;
    /**
     * createUser
     */
    private Integer createUser;

    /**
     * catagoryId
     */
    private Integer catagoryId;
}
