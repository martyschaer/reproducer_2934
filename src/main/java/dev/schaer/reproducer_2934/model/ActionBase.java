package dev.schaer.reproducer_2934.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public abstract class ActionBase {
    private Long id;
    private Long caseId;
}
