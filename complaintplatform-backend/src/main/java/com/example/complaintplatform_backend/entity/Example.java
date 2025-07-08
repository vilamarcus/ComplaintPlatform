package com.example.complaintplatform_backend.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 样例实体类
 */
@Data
@Entity
@Table(name = "example")
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键ID

    @Column(nullable = false, length = 50)
    private String name; // 名称

    @Column(length = 200)
    private String description; // 描述

    @CreationTimestamp
    @Column(updatable = false, name = "create_time")
    private Date createTime; // 创建时间

    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime; // 更新时间
}
