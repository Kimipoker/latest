package com.example.wechat.domain;

public class FileHeadInfo {
    public int fileHeadByte;
    public int[] checkBytes;
    public String remarks;
    public String suffix;
    public int key;

    public FileHeadInfo(int fileHeadByte, int[] checkBytes, String remarks, String suffix, int key) {
        this.fileHeadByte = fileHeadByte;
        this.checkBytes = checkBytes;
        this.remarks = remarks;
        this.suffix = suffix;
        this.key = key;
    }

    public static FileHeadInfo[] getAll() {
        FileHeadInfo[] fileHeadInfos = new FileHeadInfo[2];
        fileHeadInfos[0]= getJpg();
        fileHeadInfos[1]= getPng();
        return fileHeadInfos;
    }

    public static FileHeadInfo getJpg() {
        return new FileHeadInfo(-1, new int[]{-1, -40}, "jpg文件", ".jpg", -1);
    }
    public static FileHeadInfo getPng() {
        return new FileHeadInfo(-119, new int[]{-119, 80}, "png文件", ".png", -1);
    }
}
