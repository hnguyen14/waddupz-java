/**
 * ProductSearchServiceV2PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.cj.api;

public interface ProductSearchServiceV2PortType extends java.rmi.Remote {
    public com.cj.service.product.ProductResponse search(java.lang.String developerKey, java.lang.String websiteId, java.lang.String advertiserIds, java.lang.String keywords, java.lang.String serviceableArea, java.lang.String upc, java.lang.String manufacturerName, java.lang.String manufacturerSku, java.lang.String advertiserSku, java.lang.String lowPrice, java.lang.String highPrice, java.lang.String lowSalePrice, java.lang.String highSalePrice, java.lang.String currency, java.lang.String isbn, java.lang.String sortBy, java.lang.String sortOrder, java.lang.String startAt, java.lang.String maxResults) throws java.rmi.RemoteException;
}
