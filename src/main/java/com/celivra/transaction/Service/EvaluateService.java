package com.celivra.transaction.Service;

import com.celivra.transaction.Mapper.EvaluateMapper;
import com.celivra.transaction.Pojo.Evaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateService {
    @Autowired
    EvaluateMapper evaluateMapper;

    public Boolean addEvaluate(Evaluate e){
        return evaluateMapper.addEvaluate(e);
    }
    public List<Evaluate> getEvaluatesByProductId(Integer productId){
        return evaluateMapper.getEvaluatesByProductId(productId);
    }
}
