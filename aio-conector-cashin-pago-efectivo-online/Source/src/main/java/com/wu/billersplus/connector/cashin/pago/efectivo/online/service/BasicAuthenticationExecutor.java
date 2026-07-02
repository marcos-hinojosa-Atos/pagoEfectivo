package com.wu.billersplus.connector.cashin.pago.efectivo.online.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpHeaders;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicAuthenticationExecutor {

	private static final Logger logger = LoggerFactory.getLogger(BasicAuthenticationExecutor.class);

	private DefaultHttpClient httpClient;

	private static int connectionTimeoutMill = 5000;
	private static int socketTimeoutMill = 5000;
	private static int maxConnections = 10;

	private void crearCliente(int connectionTimeout, int socketTimeout, int maxConnections, String user,
			String password, boolean allCertsValid) {

		connectionTimeout = connectionTimeout > 0 ? connectionTimeout
				: BasicAuthenticationExecutor.connectionTimeoutMill;
		socketTimeout = socketTimeout > 0 ? socketTimeout : BasicAuthenticationExecutor.socketTimeoutMill;
		maxConnections = maxConnections > 0 ? maxConnections : BasicAuthenticationExecutor.maxConnections;

		logger.debug(
				"Se creará un ejecutor http con [Conection Timeout {}], [Socket Timeout {}] [ Maximas Conexiones {}]",
				connectionTimeout, socketTimeout, maxConnections);
		PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
		cm.setMaxTotal(maxConnections);
		cm.setDefaultMaxPerRoute(maxConnections);

		if (allCertsValid) {
			this.markAllCertificatesAsValid(httpClient, cm);
		}

		httpClient = new DefaultHttpClient(cm);

		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, connectionTimeout);
		HttpConnectionParams.setSoTimeout(httpParams, socketTimeout);

		httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
		httpClient.setParams(httpParams);
	}

	@SuppressWarnings("deprecation")
	private void markAllCertificatesAsValid(DefaultHttpClient httpClient, PoolingClientConnectionManager cm) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};

			X509HostnameVerifier verifier = new X509HostnameVerifier() {
				@Override
				public void verify(String string, SSLSocket ssls) throws IOException {
				}

				@Override
				public void verify(String string, X509Certificate xc) throws SSLException {
				}

				@Override
				public void verify(String string, String[] strings, String[] strings1) throws SSLException {
				}

				@Override
				public boolean verify(String string, SSLSession ssls) {
					return true;
				}
			};

			ctx.init(null, new TrustManager[] { tm }, null);

			SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			ssf.setHostnameVerifier(verifier);

			SchemeRegistry sr = cm.getSchemeRegistry();
			sr.register(new Scheme("https", 443, ssf));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ClientExecutor getEjecutor() {
		if (httpClient == null) {
			crearCliente(0, 0, 0, null, null, true);
		}

		return new ApacheHttpClient4Executor(httpClient);
	}

	public ClientExecutor getEjecutor(int connectionTimeout, int socketTimeout, int maxConections, String user,
			String password, String allCertsValid) {
		final String userBasic = user;
		final String passwordBasic = password;

		boolean certsValid = Boolean.parseBoolean(allCertsValid);

		if (httpClient == null) {
			crearCliente(connectionTimeout, socketTimeout, maxConections, user, password, certsValid);
		}

		ApacheHttpClient4Executor executor = new ApacheHttpClient4Executor(httpClient) {
			@Override
			@SuppressWarnings("rawtypes")
			public ClientResponse execute(ClientRequest request) throws Exception {
				request.header(HttpHeaders.AUTHORIZATION, getBasicAuthentication(userBasic, passwordBasic));
				return super.execute(request);
			}

			private String getBasicAuthentication(String user, String password) {
				String token = user + ":" + password;
				try {
					return "Basic " + DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
				} catch (UnsupportedEncodingException ex) {
					throw new IllegalStateException("Cannot encode with UTF-8", ex);
				}
			}

		};

		return executor;
	}

}
