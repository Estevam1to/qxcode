package com.qxcode.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

public class Iterator implements IIterator <File>{



    public Iterator()  {
    }

    public boolean hasNext(ArrayList<File> list) {
        return !list.isEmpty();
    }

    public File next(ArrayList<File> list) {
        if (!list.isEmpty()) {
            return list.remove(0);
        } 
        return null;
        
    }

    public ArrayList<File> sort(ArrayList<File> list) {
        ArrayList<File> newList = list;

        newList.sort(new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                int num1 = Integer.parseInt(file1.getName().replaceAll("\\D", ""));
                int num2 = Integer.parseInt(file2.getName().replaceAll("\\D", ""));
                return Integer.compare(num1, num2);
            }
        });

        return newList;
    }
}
