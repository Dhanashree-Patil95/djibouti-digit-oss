package org.egov.bpa.repository.rowmapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.egov.bpa.web.model.AuditDetails;
import org.egov.bpa.web.model.BPA;
import org.egov.bpa.web.model.BuildingInfo;
import org.egov.bpa.web.model.Document;
import org.egov.bpa.web.model.FloorInfo;
import org.egov.bpa.web.model.PlotInfo;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BPARowMapper implements ResultSetExtractor<List<BPA>> {

	@Autowired
	private ObjectMapper mapper;

	/**
	 * extract the data from the resultset and prepare the BPA Object
	 * 
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 */
	@Override
	public List<BPA> extractData(ResultSet rs) throws SQLException, DataAccessException {

		Map<String, BPA> buildingMap = new LinkedHashMap<String, BPA>();

		while (rs.next()) {
			String id = rs.getString("bpa_id");
			String applicationNo = rs.getString("applicationno");
			String approvalNo = rs.getString("approvalNo");
			BPA currentbpa = buildingMap.get(id);
			String tenantId = rs.getString("bpa_tenantId");
			if (currentbpa == null) {
				Long lastModifiedTime = rs.getLong("bpa_lastModifiedTime");
				if (rs.wasNull()) {
					lastModifiedTime = null;
				}

				Object additionalDetails = new Gson().fromJson(rs.getString("additionalDetails").equals("{}")
						|| rs.getString("additionalDetails").equals("null") ? null : rs.getString("additionalDetails"),
						Object.class);

				AuditDetails auditdetails = AuditDetails.builder().createdBy(rs.getString("bpa_createdBy"))
						.createdTime(rs.getLong("bpa_createdTime")).lastModifiedBy(rs.getString("bpa_lastModifiedBy"))
						.lastModifiedTime(lastModifiedTime).build();

				currentbpa = BPA.builder().auditDetails(auditdetails).applicationNo(applicationNo)
						.status(rs.getString("status")).tenantId(tenantId).approvalNo(approvalNo)
						.approvalDate(rs.getLong("approvalDate")).accountId(rs.getString("accountId"))
						.landId(rs.getString("landId")).applicationDate(rs.getLong("applicationDate")).id(id)
						.additionalDetails(additionalDetails).businessService(rs.getString("businessService")).build();

				buildingMap.put(id, currentbpa);
			}
			addChildrenToProperty(rs, currentbpa);

		}

		return new ArrayList<>(buildingMap.values());

	}

	/**
	 * add child objects to the BPA fro the results set
	 * 
	 * @param rs
	 * @param bpa
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	private void addChildrenToProperty(ResultSet rs, BPA bpa) throws SQLException {

		String tenantId = bpa.getTenantId();
		AuditDetails auditdetails = AuditDetails.builder().createdBy(rs.getString("bpa_createdBy"))
				.createdTime(rs.getLong("bpa_createdTime")).lastModifiedBy(rs.getString("bpa_lastModifiedBy"))
				.lastModifiedTime(rs.getLong("bpa_lastModifiedTime")).build();

		if (bpa == null) {
			JsonNode additionalDetail = getAdditionalDetails(rs.getObject("additionaldetail"));
			bpa.setAdditionalDetails(additionalDetail);
		}

		String plotId = rs.getString("bpa_plot_id");
		JsonNode plotDetails = getAdditionalDetails(rs.getObject("bpa_plot_details"));
		if (StringUtils.isNotBlank(plotId)) {
			PlotInfo plotInfo = PlotInfo.builder().id(plotId).plotArea(rs.getDouble("bpa_plot_area"))
					.plotNumber(rs.getString("bpa_plot_number")).khataNumber(rs.getString("bpa_khata_number"))
					.additionalDetails(plotDetails).build();
			bpa.setPlotInfo(plotInfo);
		}

		String buildingId = rs.getString("bpa_building_id");
		JsonNode buildingDetails = getAdditionalDetails(rs.getObject("bpa_building_details"));
		if (StringUtils.isNotBlank(plotId)) {
			BuildingInfo buildingInfo = BuildingInfo.builder().id(buildingId)
					.totalBuiltupArea(rs.getDouble("bpa_total_builtup_area"))
					.numberOfFloors(rs.getInt("bpa_building_num_floor"))
					.buildingHeight(rs.getDouble("bpa_building_height")).additionalDetails(buildingDetails).build();
			bpa.addBuildingInfoItem(buildingInfo);

			String floorId = rs.getString("bpa_floor_id");
			JsonNode floorDetails = getAdditionalDetails(rs.getObject("bpa_floor_details"));
			if (StringUtils.isNotBlank(plotId)) {
				FloorInfo floorInfo = FloorInfo.builder().id(floorId).floorName(rs.getString("bpa_floor_name"))
						.level(rs.getInt("bpa_floor_level")).usage(rs.getString("bpa_floor_usage"))
						.buildupArea(rs.getDouble("bpa_floor_buildup_area")).floorArea(rs.getDouble("bpa_floor_area"))
						.carpetArea(rs.getDouble("bpa_floor_carpet_area")).additionalDetails(floorDetails).build();

				buildingInfo.addFloorInfoItem(floorInfo);
			}
		}

		String documentId = rs.getString("bpa_doc_id");
		JsonNode docDetails = getAdditionalDetails(rs.getObject("doc_details"));
		if (documentId != null) {
			Document document = Document.builder().documentType(rs.getString("bpa_doc_documenttype"))
					.fileStoreId(rs.getString("bpa_doc_filestore")).id(documentId).additionalDetails(docDetails)
					.documentUid(rs.getString("documentUid")).build();
			bpa.addDocumentsItem(document);
		}
	}

	private JsonNode getAdditionalDetails(Object additionaldetailsObj) {
		JsonNode additionalDetail = null;
		if (additionaldetailsObj == null) {
			return additionalDetail;
		}
		PGobject pgObj = (PGobject) additionaldetailsObj;
		String value = pgObj.getValue();
		if (StringUtils.isNotBlank(value)) {
			try {
				additionalDetail = mapper.readTree(value);
			} catch (IOException e) {
				log.error("Failed to parse additionalDetails", e);
			}
		}
		return additionalDetail;
	}
}
