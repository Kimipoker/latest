package com.example.wechat;

import cn.hutool.core.io.FileUtil;
import com.example.wechat.domain.FileHeadInfo;
import com.example.wechat.domain.Img;
import com.example.wechat.util.EncryptionUtil;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Main {
    private static ConcurrentMap<Byte, FileHeadInfo> LocalFileMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        File file = FileUtil.file("D://data/wechat/WeChat Files/kimipoker/FileStorage/Image/2020-05/00beb78379322ee3f2ab8a5749482c45.dat");
        Img deImg = getDeImg(file);
        File de = deImg.toFile("d://1");
    }

    public static Img getDeImg(File file) {
        byte[] bytes = FileUtil.readBytes(file);
        boolean encryption = isEncryption(bytes);
        Img img;
        if (encryption) {
            FileHeadInfo fileHeadInfo = getKey(bytes);
            byte[] outbytes = EncryptionUtil.EnDeFile(bytes, fileHeadInfo.key);
            img = new Img(outbytes, file.getName(), fileHeadInfo);
        } else {
            img = new Img(bytes, file.getName(), FileHeadInfo.getJpg());
        }
        return img;
    }


    /**
     * 字节转十六进制
     * @param b 需要进行转换的byte字节
     * @return 转换后的Hex字符串
     */
    public static String byteToHex(byte b) {
        String hex = Integer.toHexString(b & 0xFF);
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

    /**
     * 判断文件是否是加密的
     * @param buffer
     * @return
     */
    public static boolean isEncryption(byte[] buffer) {
        boolean b = !Arrays.stream(FileHeadInfo.getAll()).anyMatch(fileInfor -> buffer[0] == fileInfor.checkBytes[0] && buffer[1] == fileInfor.checkBytes[1]);
        return b;
    }

    /**
     * 获取加密Key
     *
     * @param bytes
     * @return
     */
    public static FileHeadInfo getKey(byte[] bytes) {
        FileHeadInfo fileHeadInfo = LocalFileMap.get(bytes[0]);
        if (fileHeadInfo != null) {
            return fileHeadInfo;
        }
        Optional<FileHeadInfo> any = Arrays.stream(FileHeadInfo.getAll()).filter(f -> {
            int tempKey = bytes[0] ^ f.fileHeadByte;
            byte[] enbyte = EncryptionUtil.EnDeFile(bytes, tempKey);
            if (enbyte[0] == f.checkBytes[0] && enbyte[1] == f.checkBytes[1]) {
                f.key = tempKey;
                LocalFileMap.put(bytes[0], f);
                return true;
            }
            return false;
        }).findAny();
        return any.get();
    }


}
