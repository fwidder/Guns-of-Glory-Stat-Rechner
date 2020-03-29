package com.github.fwidder.statrechner.util;

import com.github.fwidder.statrechner.model.Resource;
import com.github.fwidder.statrechner.model.ResourcePackage;

public class ResourceUtil {
    public static Long resourceSum(Resource resource){
        Long value = resource.getQuantity();
        for(ResourcePackage resourcePackage: resource.getResourcePackages()) {
            value += (resourcePackage.getQuantity() * resourcePackage.getUnitSize());
        }
        return value;
    }
}
