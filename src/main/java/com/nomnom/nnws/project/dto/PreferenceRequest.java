package com.nomnom.nnws.project.dto;

import com.nomnom.nnws.project.enums.PreferenceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreferenceRequest {
    private PreferenceType preference;
}