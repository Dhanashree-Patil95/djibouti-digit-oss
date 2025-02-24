package org.egov.bpa.web.model;

import org.hibernate.validator.constraints.SafeHtml;
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
public class PlotInfo {

	@SafeHtml
	@JsonProperty("id")
	private String id;

	@JsonProperty("plotArea")
	private Double plotArea;

	@SafeHtml
	@JsonProperty("plotNumber")
	private String plotNumber;

	@SafeHtml
	@JsonProperty("khataNumber")
	private String khataNumber;

	@JsonProperty("additionalDetails")
	private Object additionalDetails = null;

	@JsonProperty("auditDetails")
	private AuditDetails auditDetails = null;
}
