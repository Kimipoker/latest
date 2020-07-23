package com.example.wechat.util;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyUtil {
    public static void main(String[] args) {
        List<Path> wxFolder = findWxFolder();

    }




    public static List<Path> findWxFolder() {
        File[] roots = File.listRoots();
        CopyOnWriteArrayList<Path> wxFilePath = new CopyOnWriteArrayList<>();
        for (File root : roots) {
                    FileUtil.walkFiles(root.toPath(), 6, new FileVisitor<Path>() {
                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                            if (dir.toString().endsWith("WeChat Files")){
                                System.out.println(dir);
                                wxFilePath.add(dir);
                                return FileVisitResult.SKIP_SIBLINGS;
                            }
                            return FileVisitResult.CONTINUE;
                        }
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            return FileVisitResult.CONTINUE;
                        }
                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                            return FileVisitResult.CONTINUE;
                        }
                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                            return FileVisitResult.CONTINUE;
                        }
                    });
        }
        return wxFilePath;
    }
}
