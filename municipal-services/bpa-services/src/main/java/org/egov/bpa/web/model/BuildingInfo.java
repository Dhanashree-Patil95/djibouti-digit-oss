package org.egov.bpa.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
public class BuildingInfo {

	@SafeHtml
	@JsonProperty("id")
	private String id;

	@JsonProperty("totalBuiltupArea")
	private Double totalBuiltupArea;

	@JsonProperty("numberOfFloors")
	private Integer numberOfFloors;

	@JsonProperty("buildingHeight")
	private Double buildingHeight;

	@JsonProperty("floorInfos")
	@Valid
	private List<FloorInfo> floorInfos = null;

	@JsonProperty("additionalDetails")
	private Object additionalDetails = null;

	@JsonProperty("auditDetails")
	private AuditDetails auditDetails = null;

	public BuildingInfo addFloorInfoItem(FloorInfo floorInfoItem) {
		if (this.floorInfos == null) {
			this.floorInfos = new ArrayList<>();
		}
		this.floorInfos.add(floorInfoItem);
		return this;
	}
}
