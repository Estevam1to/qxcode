package com.qxcode.Utils;

import java.io.File;
import java.util.ArrayList;

public interface IIterator <T> {

    boolean hasNext(ArrayList<T> list);

    File next(ArrayList<T> list);

    ArrayList<T> sort(ArrayList<File> list);
}