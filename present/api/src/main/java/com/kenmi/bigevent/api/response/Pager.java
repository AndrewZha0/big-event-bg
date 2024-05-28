package com.kenmi.bigevent.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Pager<T> {
    private Long total;//总条数
    private List<T> items;//当前页数据集合
}
