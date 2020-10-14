package it.dinfo.stlab.model.providers;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class ServiceProviderInfo {

	@Enumerated(EnumType.STRING)
	private ServiceProviderType serviceProviderType;

	/*
	 * Se ServiceProviderType ha valore:
	 * ApiServiceProvider allora la stringa content sarà una URI
	 * EmbeddableViewServiceProvider allora la stringa content sarà un content(pezzo di html o altro)
	 * RedirectableServiceProvider allora la stringa content sarà una URI
	*/
	@Column(name = "serviceProviderUri")
	private String serviceProviderUri;

	public ServiceProviderInfo() {
	}

	public ServiceProviderInfo(ServiceProviderType type, String content) {
		this.serviceProviderType = type;
		this.serviceProviderUri = content;
	}

	public ServiceProviderType getServiceProviderType() {
		return serviceProviderType;
	}

	public void setServiceProviderType(ServiceProviderType serviceProviderType) {
		this.serviceProviderType = serviceProviderType;
	}

	public String getServiceProviderUri() {
		return serviceProviderUri;
	}

	public void setServiceProviderUri(String content) {
		this.serviceProviderUri = content;
	}
}
