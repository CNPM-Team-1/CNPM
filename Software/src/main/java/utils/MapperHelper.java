package utils;

import dataModel.MerchandiseModel;
import entities.Merchandise;

import java.util.ArrayList;
import java.util.List;

public class MapperHelper {
    public static List<MerchandiseModel> toMerchandiseModelList(List<Merchandise> merchandiseList) {
        List<MerchandiseModel> result = new ArrayList<>();

        for (Merchandise item : merchandiseList) {
            MerchandiseModel merchandiseModel = new MerchandiseModel();
            merchandiseModel.setId(item.getId());
            merchandiseModel.setName(item.getName());
            merchandiseModel.setType(item.getType());
            merchandiseModel.setBranch(item.getBranch());
            merchandiseModel.setQuantity(item.getQuantity());
            merchandiseModel.setImportPrice(item.getImportPrice());
            merchandiseModel.setPrice(item.getPrice().toString());
            merchandiseModel.setCreatedDate(item.getCreatedDate());
            merchandiseModel.setUpdatedDate(item.getUpdatedDate());

            result.add(merchandiseModel);
        }

        return result;
    }
}
