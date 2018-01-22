package org.sysu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.web.multipart.MultipartFile;
import org.sysu.bean.Ad;

@JsonInclude(Include.NON_NULL)
public class AdDto extends Ad {
	private String title;
    private String img;
    private String link;
    private MultipartFile imgFile;

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "AdDto{" +
				"title='" + title + '\'' +
				", img='" + img + '\'' +
				", link='" + link + '\'' +
				", imgFile=" + imgFile +
				'}';
	}
}
