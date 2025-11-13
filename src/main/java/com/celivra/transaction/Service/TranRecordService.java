package com.celivra.transaction.Service;

import com.celivra.transaction.Mapper.TransRecordMapper;
import com.celivra.transaction.Pojo.TransRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranRecordService {
    @Autowired
    TransRecordMapper transRecordMapper;
    public Boolean addTransRecord(TransRecord transRecord){
        return transRecordMapper.addTransRecord(transRecord);
    }
    public Boolean updateTranRecord(TransRecord transRecord){
        return transRecordMapper.updateTransRecord(transRecord);
    }
    public List<TransRecord> getTransRecordsByBuyer(Integer userId){
        return transRecordMapper.getTransRecordsByBuyer(userId);
    }
    public List<TransRecord> getTransRecordsBySeller(Integer userId){
        return transRecordMapper.getTransRecordsBySeller(userId);
    }
}
