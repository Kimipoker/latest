package com.example.files.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.Tailer;

import java.io.File;
import java.util.Arrays;

public class FileTailer {
    public static void main(String[] args) {
//        Tailer tailer = new Tailer(FileUtil.file("d://1.txt"), Tailer.CONSOLE_HANDLER, 2);
//        tailer.start();

        File[] roots = File.listRoots();
        Arrays.stream(roots).forEach(root->{
            File[] files = root.listFiles();

        });

    }
}
