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
    public Boolean addTranRecord(TransRecord transRecord){
        return transRecordMapper.addTransRecord(transRecord);
    }
    public Boolean updateTranRecord(TransRecord transRecord){
        return transRecordMapper.updateTransRecord(transRecord);
    }
    public List<TransRecord> getTransRecordsByUserId(Integer userId){
        return transRecordMapper.getTransRecordsByUserId(userId);
    }
}
