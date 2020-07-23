package com.example.wechat.domain;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;

public class Img {
    byte[] bytes;
    String name;
    FileHeadInfo fileHeadInfo;

    public File toFile(String desFileFolder) {
        if (bytes.length == 0) {
            throw new IllegalStateException("图片内容为空");
        }
        if(StrUtil.isEmpty(desFileFolder)){
            throw new IllegalArgumentException("入参不能为空");
        }
        File dir = FileUtil.file(desFileFolder);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File outFile=new File(dir,name+fileHeadInfo.suffix);
        FileUtil.writeBytes(this.bytes,new File(dir,name+fileHeadInfo.suffix));
        return outFile;
    }
    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public FileHeadInfo getFileHeadInfo() {
        return fileHeadInfo;
    }

    public void setFileHeadInfo(FileHeadInfo fileHeadInfo) {
        this.fileHeadInfo = fileHeadInfo;
    }

    public Img(byte[] bytes, FileHeadInfo fileHeadInfo) {
        this.bytes = bytes;
        this.fileHeadInfo = fileHeadInfo;
    }

    public Img(byte[] bytes, String name, FileHeadInfo fileHeadInfo) {
        this.bytes = bytes;
        this.name = name;
        this.fileHeadInfo = fileHeadInfo;
    }
}
