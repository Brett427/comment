package org.sysu.dao;

import org.springframework.stereotype.Component;
import org.sysu.bean.Ad;
import org.sysu.dto.AdDto;


@Component
public interface AdDao {

    int insert(Ad ad);
}
