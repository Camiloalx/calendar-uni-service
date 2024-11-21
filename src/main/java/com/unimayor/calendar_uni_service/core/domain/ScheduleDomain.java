package com.unimayor.calendar_uni_service.core.domain;

import com.unimayor.calendar_uni_service.core.constant.MessageConstant;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@Setter
@Getter
public class ScheduleDomain {
    @Size(min = 1, max = 2)
    private int id;
    @NotNull(message = MessageConstant.EL_CAMPO + "titulo" + MessageConstant.ES_OBLIGATORIO)
    private String title;
    @NotBlank(message = MessageConstant.EL_CAMPO + "descripcion" + MessageConstant.ES_OBLIGATORIO)
    private String description;
    @NotBlank(message = MessageConstant.EL_CAMPO + "ubicacion" + MessageConstant.ES_OBLIGATORIO)
    private String location;
    @NotBlank(message = MessageConstant.EL_CAMPO + "fecha inicio" + MessageConstant.ES_OBLIGATORIO)
    private String startDate;
    @NotBlank(message = MessageConstant.EL_CAMPO + "fecha fin" + MessageConstant.ES_OBLIGATORIO)
    private String endDate;
}
