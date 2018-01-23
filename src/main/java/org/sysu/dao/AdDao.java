package org.sysu.dao;

import org.springframework.stereotype.Component;
import org.sysu.bean.Ad;
import org.sysu.dto.AdDto;

import java.util.List;


@Component
public interface AdDao {

    int insert(Ad ad);

    List<Ad> selectAd(String title);

    Ad selectById(Long id);

    int update(Ad ad);

    int delete(Long id);

}
