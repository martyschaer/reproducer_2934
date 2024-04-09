package dev.schaer.reproducer_2934.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ActionCancellation extends ActionBase {
    private String place;

    public ActionCancellation(Long id, Long caseId, final String place) {
        super(id, caseId);
        this.place = place;
    }
}
