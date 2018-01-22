package org.sysu.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.sysu.bean.Ad;
import org.sysu.dao.AdDao;
import org.sysu.dto.AdDto;
import org.sysu.service.AdService;
import sun.plugin2.util.SystemUtil;

import java.io.File;
import java.io.IOException;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdDao adDao;

    @Value("${adImage.savePath}")
    private String  adImageSavePAth;

    @Override
    public boolean insertad(AdDto adDto) {

        Ad ad =new Ad();
        BeanUtils.copyProperties(adDto,ad);

        if(adDto.getImgFile()!=null&& adDto.getImgFile().getSize()>0)
        {
            String filename =adImageSavePAth+ System.currentTimeMillis()+adDto.getImgFile().getOriginalFilename();
            File f =new File(filename);
            File fileforder =new File(adImageSavePAth);
            if (!fileforder.exists()) {
                fileforder.mkdirs();
            }
            try {
                adDto.getImgFile().transferTo(f);
                ad.setImgFileName(System.currentTimeMillis()+adDto.getImgFile().getOriginalFilename());
                adDao.insert(ad);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        else
        {
            return false;
        }

    }
}
