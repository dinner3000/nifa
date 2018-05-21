package com.brh.p2p.nifa.service;

import java.util.List;

public interface ExportBusinessService<T> {

    List<T> findAllByInputdate(String inputdate);

    void generateAllByInputdate(String inputdate);
    void generateAll(String inputdate);

    void removeAllByInputdate(String inputdate);
    void clearAll();

    void regenerateAllByInputdate(String inputdate);
    void regenerateAll(String inputdate);

}
