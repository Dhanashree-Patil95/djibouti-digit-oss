package org.egov.bpa.calculator.web.models.bpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.egov.bpa.calculator.web.models.AuditDetails;
import org.egov.bpa.calculator.web.models.Document;
import org.egov.bpa.calculator.web.models.landinfo.LandInfo;
import org.egov.bpa.calculator.web.models.landinfo.Workflow;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * BPA application object to capture the details of land, land owners, and
 * address of the land.
 */
@ApiModel(description = "BPA application object to capture the details of land, land owners, and address of the land.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-23T05:52:32.717Z[GMT]")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BPA {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("applicationNo")
	private String applicationNo = null;

	@JsonProperty("approvalNo")
	private String approvalNo = null;

	@JsonProperty("accountId")
	private String accountId = null;

	@JsonProperty("riskType")
	private String riskType = null;

	@JsonProperty("businessService")
	private String businessService = null;

	@JsonProperty("landId")
	private String landId = null;

	@JsonProperty("tenantId")
	private String tenantId = null;

	@JsonProperty("approvalDate")
	private Long approvalDate = null;

	@JsonProperty("applicationDate")
	private Long applicationDate = null;

	@JsonProperty("status")
	private String status = null;

	@JsonProperty("documents")
	@Valid
	private List<Document> documents = null;

	@JsonProperty("plotInfo")
	private PlotInfo plotInfo = null;

	@JsonProperty("buildingInfos")
	@Valid
	private List<BuildingInfo> buildingInfos = null;

	@JsonProperty("landInfo")
	private LandInfo landInfo = null;

	@JsonProperty("workflow")
	private Workflow workflow = null;

	@JsonProperty("auditDetails")
	private AuditDetails auditDetails = null;

	@JsonProperty("additionalDetails")
	private Object additionalDetails = null;

	/**
	 * Unique Identifier(UUID) of the bpa application for internal reference.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(readOnly = true, value = "Unique Identifier(UUID) of the bpa application for internal reference.")

	@Size(min = 1, max = 64)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Generate formatted Unique Identifier of the building permit application. Keep
	 * the format in mdms
	 * 
	 * @return applicationNo
	 **/
	@ApiModelProperty(readOnly = true, value = "Generate formatted Unique Identifier of the building permit application. Keep the format in mdms")

	@Size(min = 1, max = 64)
	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	/**
	 * Generate Approval number based on wf status. When to generate approvalNo will
	 * be depends on wf state so make it configurable at application level
	 * 
	 * @return approvalNo
	 **/
	@ApiModelProperty(readOnly = true, value = "Generate Approval number based on wf status. When to generate approvalNo will be depends on wf state so make it configurable at  application level")

	@Size(min = 1, max = 64)
	public String getApprovalNo() {
		return approvalNo;
	}

	public void setApprovalNo(String approvalNo) {
		this.approvalNo = approvalNo;
	}

	/**
	 * Initiator User UUID
	 * 
	 * @return accountId
	 **/
	@ApiModelProperty(value = "Initiator User UUID")

	@Size(min = 1, max = 64)
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * Risk type will be drived based on mdms configuration
	 * 
	 * @return riskType
	 **/
	@ApiModelProperty(readOnly = true, value = "Risk type will be drived based on mdms configuration")

	@Size(min = 1, max = 64)
	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public Long getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Long approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Long getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Long applicationDate) {
		this.applicationDate = applicationDate;
	}

	@Size(min = 1, max = 64)
	public String getBusinessService() {
		return businessService;
	}

	public void setBusinessService(String businessService) {
		this.businessService = businessService;
	}

	/**
	 * Unique Identifier(UUID) of the land for internal reference.
	 * 
	 * @return landId
	 **/
	@ApiModelProperty(value = "Unique Identifier(UUID) of the land for internal reference.")

	@Size(min = 1, max = 64)
	public String getLandId() {
		return landId;
	}

	public void setLandId(String landId) {
		this.landId = landId;
	}

	/**
	 * Unique ULB identifier.
	 * 
	 * @return tenantId
	 **/
	@ApiModelProperty(required = true, value = "Unique ULB identifier.")
	@NotNull

	@Size(min = 2, max = 256)
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * status of the application.
	 * 
	 * @return status
	 **/
	@ApiModelProperty(value = "status of the application.")

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BPA addDocumentsItem(Document documentsItem) {
		if (this.documents == null) {
			this.documents = new ArrayList<Document>();
		}
		this.documents.add(documentsItem);
		return this;
	}

	/**
	 * The documents attached by owner for exemption.
	 * 
	 * @return documents
	 **/
	@ApiModelProperty(value = "The documents attached by owner for exemption.")
	@Valid
	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	/**
	 * Get landInfo
	 * 
	 * @return landInfo
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public LandInfo getLandInfo() {
		return landInfo;
	}

	public void setLandInfo(LandInfo landInfo) {
		this.landInfo = landInfo;
	}

	/**
	 * Get workflow
	 * 
	 * @return workflow
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	/**
	 * Get auditDetails
	 * 
	 * @return auditDetails
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public AuditDetails getAuditDetails() {
		return auditDetails;
	}

	public void setAuditDetails(AuditDetails auditDetails) {
		this.auditDetails = auditDetails;
	}

	/**
	 * The json to capturing the custom fields
	 * 
	 * @return additionalDetails
	 **/
	@ApiModelProperty(value = "The json to capturing the custom fields")

	public Object getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(Object additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	/**
	 * Get plotInfo
	 * 
	 * @return plotInfo
	 **/
	@Valid
	public PlotInfo getPlotInfo() {
		return plotInfo;
	}

	public void setPlotInfo(PlotInfo plotInfo) {
		this.plotInfo = plotInfo;
	}

	public BPA addBuildingInfoItem(BuildingInfo buildingInfoItem) {
		if (this.buildingInfos == null) {
			this.buildingInfos = new ArrayList<>();
		}
		this.buildingInfos.add(buildingInfoItem);
		return this;
	}

	/**
	 * The building informations specified by owner.
	 * 
	 * @return buildingInfos
	 **/
	@Valid
	public List<BuildingInfo> getBuildingInfos() {
		return buildingInfos;
	}

	public void setBuildingInfos(List<BuildingInfo> buildingInfos) {
		this.buildingInfos = buildingInfos;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BPA BPA = (BPA) o;
		return Objects.equals(this.id, BPA.id) && Objects.equals(this.applicationNo, BPA.applicationNo)
				&& Objects.equals(this.approvalNo, BPA.approvalNo) && Objects.equals(this.accountId, BPA.accountId)
				&& Objects.equals(this.riskType, BPA.riskType) && Objects.equals(this.landId, BPA.landId)
				&& Objects.equals(this.tenantId, BPA.tenantId) && Objects.equals(this.status, BPA.status)
				&& Objects.equals(this.documents, BPA.documents) && Objects.equals(this.landInfo, BPA.landInfo)
				&& Objects.equals(this.plotInfo, BPA.plotInfo) && Objects.equals(this.buildingInfos, BPA.buildingInfos)
				&& Objects.equals(this.workflow, BPA.workflow) && Objects.equals(this.auditDetails, BPA.auditDetails)
				&& Objects.equals(this.additionalDetails, BPA.additionalDetails);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, applicationNo, approvalNo, accountId, riskType, landId, tenantId, status, documents,
				landInfo, plotInfo, buildingInfos, workflow, auditDetails, additionalDetails);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BPA {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    applicationNo: ").append(toIndentedString(applicationNo)).append("\n");
		sb.append("    approvalNo: ").append(toIndentedString(approvalNo)).append("\n");
		sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
		sb.append("    riskType: ").append(toIndentedString(riskType)).append("\n");
		sb.append("    landId: ").append(toIndentedString(landId)).append("\n");
		sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    documents: ").append(toIndentedString(documents)).append("\n");
		sb.append("    landInfo: ").append(toIndentedString(landInfo)).append("\n");
		sb.append("    plotInfo: ").append(toIndentedString(plotInfo)).append("\n");
		sb.append("    buildingInfos: ").append(toIndentedString(buildingInfos)).append("\n");
		sb.append("    workflow: ").append(toIndentedString(workflow)).append("\n");
		sb.append("    auditDetails: ").append(toIndentedString(auditDetails)).append("\n");
		sb.append("    additionalDetails: ").append(toIndentedString(additionalDetails)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
