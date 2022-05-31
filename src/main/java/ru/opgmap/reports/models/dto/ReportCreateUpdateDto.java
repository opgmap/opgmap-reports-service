package ru.opgmap.reports.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.opgmap.reports.models.enums.ReportType;
import ru.opgmap.reports.models.enums.SubjectType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ReportCreateUpdateDto implements Serializable {

    @NotNull
    private ReportType reportType;

    @NotNull
    private SubjectType subjectType;

    @NotNull
    private UUID subjectId;

    @Size(max = 255)
    private String text;


}
