/**
 * ProductSearchServiceV2Locator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.cj.api;

public class ProductSearchServiceV2Locator extends org.apache.axis.client.Service implements com.cj.api.ProductSearchServiceV2 {

    // Use to get a proxy class for productSearchServiceV2HttpPort
    private final java.lang.String productSearchServiceV2HttpPort_address = "https://product.api.cj.com/services/productSearchServiceV2";

    public java.lang.String getproductSearchServiceV2HttpPortAddress() {
        return productSearchServiceV2HttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String productSearchServiceV2HttpPortWSDDServiceName = "productSearchServiceV2HttpPort";

    public java.lang.String getproductSearchServiceV2HttpPortWSDDServiceName() {
        return productSearchServiceV2HttpPortWSDDServiceName;
    }

    public void setproductSearchServiceV2HttpPortWSDDServiceName(java.lang.String name) {
        productSearchServiceV2HttpPortWSDDServiceName = name;
    }

    public com.cj.api.ProductSearchServiceV2PortType getproductSearchServiceV2HttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(productSearchServiceV2HttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getproductSearchServiceV2HttpPort(endpoint);
    }

    public com.cj.api.ProductSearchServiceV2PortType getproductSearchServiceV2HttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.cj.api.ProductSearchServiceV2HttpBindingStub _stub = new com.cj.api.ProductSearchServiceV2HttpBindingStub(portAddress, this);
            _stub.setPortName(getproductSearchServiceV2HttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.cj.api.ProductSearchServiceV2PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.cj.api.ProductSearchServiceV2HttpBindingStub _stub = new com.cj.api.ProductSearchServiceV2HttpBindingStub(new java.net.URL(productSearchServiceV2HttpPort_address), this);
                _stub.setPortName(getproductSearchServiceV2HttpPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("productSearchServiceV2HttpPort".equals(inputPortName)) {
            return getproductSearchServiceV2HttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://api.cj.com", "productSearchServiceV2");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("productSearchServiceV2HttpPort"));
        }
        return ports.iterator();
    }

}
