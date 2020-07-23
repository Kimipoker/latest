package com.example.files.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.lang.Console;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

public class FileScan {
    private static final String RECYCLE_BIN="$RECYCLE.BIN";
    public static void main(String[] args) {
        File file = FileUtil.file("D://");
//这里只监听文件或目录的修改事件
        WatchMonitor watchMonitor = WatchMonitor.createAll(file, new SimpleWatcher(){
            @Override
            public void onCreate(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                if (!isRecycleBin(currentPath)) {
                    Console.log("创建：{}-> {}", currentPath, obj);
                }

            }

            @Override
            public void onModify(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                if (!isRecycleBin(currentPath)) {
                    Console.log("修改：{}-> {}", currentPath, obj);
                }
            }

            @Override
            public void onOverflow(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("Overflow：{}-> {}", currentPath, obj);
            }
        });


//设置监听目录的最大深入，目录层级大于制定层级的变更将不被监听，默认只监听当前层级目录
        watchMonitor.setMaxDepth(5);
//启动监听
        watchMonitor.start();
    }

    private static boolean isRecycleBin(Path path) {
        if (path == null) {
            return false;
        }
        return path.toString().indexOf(RECYCLE_BIN)>-1;
    }
}
