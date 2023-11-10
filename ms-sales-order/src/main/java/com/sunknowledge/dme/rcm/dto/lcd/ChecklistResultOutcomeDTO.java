package com.sunknowledge.dme.rcm.dto.lcd;

import com.sunknowledge.dme.rcm.service.dto.SoLcdCoverageCriteriaTransactionDTO;
import com.sunknowledge.dme.rcm.service.dto.SoLcdDocRefTransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistResultOutcomeDTO {
    private List<DocumentChecklistDTO> documentChecklist;
    private List<CoverageCriteriaChecklistDTO> coverageCriteriaChecklist;

    private List<SoLcdDocRefTransactionDTO> soLcdDocRefTransactionDTOS;
    private List<SoLcdCoverageCriteriaTransactionDTO> soLcdCoverageCriteriaTransactionDTOS;
}
