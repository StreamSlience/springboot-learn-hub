package com.streamslience.mapstruct.officialtutorial.datatypeconversions.command.domain;

import lombok.Data;

/**
 * @author StreamSlience
 * @date 2020-10-14 20:57
 */
@Data
public class Report {

    private Organisation organisation = new Organisation();

    private String organisationName = "organisationName";

}
