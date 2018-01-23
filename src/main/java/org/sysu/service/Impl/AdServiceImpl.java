package org.sysu.service.Impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.sysu.bean.Ad;
import org.sysu.dao.AdDao;
import org.sysu.dto.AdDto;
import org.sysu.service.AdService;
import org.sysu.utils.FileUtil;
import sun.plugin2.util.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
    @Override
    public List<Ad> selectAd(AdDto adDto,int page,int rows) {
        String title = adDto.getTitle();
        PageHelper.startPage(page,rows);
        List<Ad> ads =adDao.selectAd(title);
        return ads;
    }

    @Override
    public boolean remove(Long id) {
        adDao.delete(id);
        return true;
    }

    @Override
    public AdDto getById(Long id) {
        AdDto result =new AdDto();
        Ad ad =adDao.selectById(id);
        BeanUtils.copyProperties(ad,result);
        result.setImg(adImageSavePAth + ad.getImgFileName());
        return result;
    }

    @Override
    public boolean modify(AdDto adDto) {
        Ad ad = new Ad();
        BeanUtils.copyProperties(adDto, ad);

        String fileName = null;
        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
            try {
                fileName = FileUtil.save(adDto.getImgFile(), adImageSavePAth);
                ad.setImgFileName(fileName);
            } catch (IllegalStateException | IOException e) {
                return false;
            }
        }
        int updateCount = adDao.update(ad);
        if (updateCount != 1) {
            return false;
        }
        if (fileName != null) {
            return FileUtil.delete(adImageSavePAth + adDto.getImgFile());
        }
        return true;
    }
}
