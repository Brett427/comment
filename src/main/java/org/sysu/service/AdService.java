package org.sysu.service;

import org.sysu.bean.Ad;
import org.sysu.dto.AdDto;

import java.util.List;

public interface AdService {

    boolean insertad(AdDto adDto);

    List<Ad> selectAd(AdDto adDto,int page,int rows);

    boolean remove(Long id);

    AdDto getById(Long id);

    boolean modify(AdDto adDto);
}
