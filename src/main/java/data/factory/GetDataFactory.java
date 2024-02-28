package data.factory;

import model.GetRequestDTO;
import java.util.List;

public class GetDataFactory {
    public static GetRequestDTO getPadrao() {
        GetRequestDTO getReq = new GetRequestDTO();
        getReq.setPage(0);
        getReq.setSize(1);
        getReq.setSort(List.of(""));

        return getReq;
    }
}
