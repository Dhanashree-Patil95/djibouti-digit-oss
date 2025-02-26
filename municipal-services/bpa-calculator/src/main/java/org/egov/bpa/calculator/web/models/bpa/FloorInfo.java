package org.egov.bpa.calculator.web.models.bpa;

import org.egov.bpa.calculator.web.models.AuditDetails;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Validated
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@Setter
@ToString
@Builder
public class FloorInfo {

	@JsonProperty("id")
	private String id;

	@JsonProperty("floorName")
	private String floorName;

	@JsonProperty("level")
	private Integer level;

	@JsonProperty("usage")
	private String usage;

	@JsonProperty("buildupArea")
	private Double buildupArea;

	@JsonProperty("floorArea")
	private Double floorArea;

	@JsonProperty("carpetArea")
	private Double carpetArea;

	@JsonProperty("additionalDetails")
	private Object additionalDetails = null;

	@JsonProperty("auditDetails")
	private AuditDetails auditDetails = null;
}
