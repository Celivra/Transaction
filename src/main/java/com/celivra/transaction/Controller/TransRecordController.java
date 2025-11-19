package com.celivra.transaction.Controller;

import com.celivra.transaction.Pojo.TransRecord;
import com.celivra.transaction.Service.TranRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransRecordController {
    @Autowired
    TranRecordService tranRecordService;


    @PostMapping("/shipNow")
    @ResponseBody
    public ResponseEntity<String> shipNow(@RequestParam Integer recordId) {
        TransRecord record = tranRecordService.getTransRecordById(recordId);
        if (record == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("记录不存在");
        }

        record.setStatus("已发货");
        tranRecordService.updateTranRecord(record);
        return ResponseEntity.ok("ok");
    }
}
