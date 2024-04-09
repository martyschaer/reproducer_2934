package dev.schaer.reproducer_2934.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ActionSpecialStop extends ActionBase {
    private String place;
    private String stopReason;

    public ActionSpecialStop(Long id, Long caseId, String place, String stopReason) {
        super(id, caseId);
        this.place = place;
        this.stopReason = stopReason;
    }
}
