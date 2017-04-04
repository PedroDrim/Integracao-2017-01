
package com.mycompany.horus.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0-SNAPSHOT
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EstoqueService", targetNamespace = "http://servicos.saude.gov.br/horus/v1r0/EstoqueService", wsdlLocation = "https://servicoshm.saude.gov.br/horus/v1r0/EstoqueService?wsdl")
public class EstoqueService
    extends Service
{

    private final static URL ESTOQUESERVICE_WSDL_LOCATION;
    private final static WebServiceException ESTOQUESERVICE_EXCEPTION;
    private final static QName ESTOQUESERVICE_QNAME = new QName("http://servicos.saude.gov.br/horus/v1r0/EstoqueService", "EstoqueService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://servicoshm.saude.gov.br/horus/v1r0/EstoqueService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ESTOQUESERVICE_WSDL_LOCATION = url;
        ESTOQUESERVICE_EXCEPTION = e;
    }

    public EstoqueService() {
        super(__getWsdlLocation(), ESTOQUESERVICE_QNAME);
    }

    public EstoqueService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ESTOQUESERVICE_QNAME, features);
    }

    public EstoqueService(URL wsdlLocation) {
        super(wsdlLocation, ESTOQUESERVICE_QNAME);
    }

    public EstoqueService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ESTOQUESERVICE_QNAME, features);
    }

    public EstoqueService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EstoqueService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EstoqueServicePortType
     */
    @WebEndpoint(name = "EstoqueServicePort")
    public EstoqueServicePortType getEstoqueServicePort() {
        return super.getPort(new QName("http://servicos.saude.gov.br/horus/v1r0/EstoqueService", "EstoqueServicePort"), EstoqueServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EstoqueServicePortType
     */
    @WebEndpoint(name = "EstoqueServicePort")
    public EstoqueServicePortType getEstoqueServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://servicos.saude.gov.br/horus/v1r0/EstoqueService", "EstoqueServicePort"), EstoqueServicePortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ESTOQUESERVICE_EXCEPTION!= null) {
            throw ESTOQUESERVICE_EXCEPTION;
        }
        return ESTOQUESERVICE_WSDL_LOCATION;
    }

}