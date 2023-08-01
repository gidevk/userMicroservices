package com.expriment.service;


import com.expriment.entity.vo.NameMatchKarzaRequest;
import com.expriment.entity.vo.NameMatchKarzaResponse;

import java.util.Map;

public interface NameMatchingService {
//    Map<String, Boolean> nameMatchingApi(NameMatchKarzaRequest request);

    Map<String, Boolean> matchingInputsWithEvokeAPI(NameMatchKarzaRequest request);

    NameMatchKarzaResponse NameMatchByKarza(NameMatchKarzaRequest request);

//    NameMatchKarzaResponse NameMatchByKarza(NameMatchKarzaRequest request);
}
