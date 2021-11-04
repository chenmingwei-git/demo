package com.cmw.mybatis.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @description:
 * @author: cmw
 * @date: 2021/11/4
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "human_info")
public class HumanInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1443327827336836596L;

    private String name;

    private String idNo;

    private String age;

    private String address;

    private String remark;

}
