package com.expriment.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author SAIDULU (EMPID:061)
 * @version 1.0 27-Jun-2018
 */

@Configuration
@Profile({ "application-project-url"})
public class AppProps {

	@Value("${location_to_upload_doc}")
	private String locationToUploadDoc;

	@Value("${tcl_api_request_details_file}")
	private String tclApiRequestDetailsFile;

	@Value("${handle_evoke_request_uri}")
	private String evokeServiceUrl;

	@Value("${tclpa.apis.getofferdetails.query}")
	private String paOfferDetailsQuery;

	@Value("${tcl_ccare_from}")
	private String fromMailId;

	@Value("${tcl_pa_bcc_ids}")
	private String bccMailIds;

	public String getHelloBose() {
		return helloBose;
	}

	public void setHelloBose(String helloBose) {
		this.helloBose = helloBose;
	}

	@Value(("${tcl.data.bos}"))
	private String helloBose;


	public String getLocationToUploadDoc() {
		return locationToUploadDoc;
	}

	public void setLocationToUploadDoc(String locationToUploadDoc) {
		this.locationToUploadDoc = locationToUploadDoc;
	}

	public String getTclApiRequestDetailsFile() {
		return tclApiRequestDetailsFile;
	}

	public void setTclApiRequestDetailsFile(String tclApiRequestDetailsFile) {
		this.tclApiRequestDetailsFile = tclApiRequestDetailsFile;
	}

	public String getEvokeServiceUrl() {
		return evokeServiceUrl;
	}

	public void setEvokeServiceUrl(String evokeServiceUrl) {
		this.evokeServiceUrl = evokeServiceUrl;
	}

	public String getPaOfferDetailsQuery() {
		return paOfferDetailsQuery;
	}

	public void setPaOfferDetailsQuery(String paOfferDetailsQuery) {
		this.paOfferDetailsQuery = paOfferDetailsQuery;
	}

	public String getFromMailId() {
		return fromMailId;
	}

	public void setFromMailId(String fromMailId) {
		this.fromMailId = fromMailId;
	}

	public String getBccMailIds() {
		return bccMailIds;
	}

	public void setBccMailIds(String bccMailIds) {
		this.bccMailIds = bccMailIds;
	}
	
}
