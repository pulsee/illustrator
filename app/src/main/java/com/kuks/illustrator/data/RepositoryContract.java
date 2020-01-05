package com.kuks.illustrator.data;

import java.util.List;

public interface RepositoryContract {

    List<Asset> loadAssets(String path);

    List<Asset> loadDrawables();
}
