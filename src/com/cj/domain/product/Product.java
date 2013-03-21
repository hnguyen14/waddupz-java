/**
 * Product.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.cj.domain.product;

public class Product  implements java.io.Serializable {
    private java.lang.Long adId;
    private java.lang.Long advertiserId;
    private java.lang.String advertiserName;
    private java.lang.String buyUrl;
    private java.lang.String catalogId;
    private java.lang.String currency;
    private java.lang.String description;
    private java.lang.String imageUrl;
    private java.lang.String inStock;
    private java.lang.String isbn;
    private java.lang.String manufacturerName;
    private java.lang.String manufacturerSku;
    private java.lang.String name;
    private java.lang.Double price;
    private java.lang.Double retailPrice;
    private java.lang.Double salePrice;
    private java.lang.String sku;
    private java.lang.String upc;

    public Product() {
    }

    public java.lang.Long getAdId() {
        return adId;
    }

    public void setAdId(java.lang.Long adId) {
        this.adId = adId;
    }

    public java.lang.Long getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(java.lang.Long advertiserId) {
        this.advertiserId = advertiserId;
    }

    public java.lang.String getAdvertiserName() {
        return advertiserName;
    }

    public void setAdvertiserName(java.lang.String advertiserName) {
        this.advertiserName = advertiserName;
    }

    public java.lang.String getBuyUrl() {
        return buyUrl;
    }

    public void setBuyUrl(java.lang.String buyUrl) {
        this.buyUrl = buyUrl;
    }

    public java.lang.String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(java.lang.String catalogId) {
        this.catalogId = catalogId;
    }

    public java.lang.String getCurrency() {
        return currency;
    }

    public void setCurrency(java.lang.String currency) {
        this.currency = currency;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public java.lang.String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(java.lang.String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public java.lang.String getInStock() {
        return inStock;
    }

    public void setInStock(java.lang.String inStock) {
        this.inStock = inStock;
    }

    public java.lang.String getIsbn() {
        return isbn;
    }

    public void setIsbn(java.lang.String isbn) {
        this.isbn = isbn;
    }

    public java.lang.String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(java.lang.String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public java.lang.String getManufacturerSku() {
        return manufacturerSku;
    }

    public void setManufacturerSku(java.lang.String manufacturerSku) {
        this.manufacturerSku = manufacturerSku;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.Double getPrice() {
        return price;
    }

    public void setPrice(java.lang.Double price) {
        this.price = price;
    }

    public java.lang.Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(java.lang.Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public java.lang.Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(java.lang.Double salePrice) {
        this.salePrice = salePrice;
    }

    public java.lang.String getSku() {
        return sku;
    }

    public void setSku(java.lang.String sku) {
        this.sku = sku;
    }

    public java.lang.String getUpc() {
        return upc;
    }

    public void setUpc(java.lang.String upc) {
        this.upc = upc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Product)) return false;
        Product other = (Product) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.adId==null && other.getAdId()==null) || 
             (this.adId!=null &&
              this.adId.equals(other.getAdId()))) &&
            ((this.advertiserId==null && other.getAdvertiserId()==null) || 
             (this.advertiserId!=null &&
              this.advertiserId.equals(other.getAdvertiserId()))) &&
            ((this.advertiserName==null && other.getAdvertiserName()==null) || 
             (this.advertiserName!=null &&
              this.advertiserName.equals(other.getAdvertiserName()))) &&
            ((this.buyUrl==null && other.getBuyUrl()==null) || 
             (this.buyUrl!=null &&
              this.buyUrl.equals(other.getBuyUrl()))) &&
            ((this.catalogId==null && other.getCatalogId()==null) || 
             (this.catalogId!=null &&
              this.catalogId.equals(other.getCatalogId()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.imageUrl==null && other.getImageUrl()==null) || 
             (this.imageUrl!=null &&
              this.imageUrl.equals(other.getImageUrl()))) &&
            ((this.inStock==null && other.getInStock()==null) || 
             (this.inStock!=null &&
              this.inStock.equals(other.getInStock()))) &&
            ((this.isbn==null && other.getIsbn()==null) || 
             (this.isbn!=null &&
              this.isbn.equals(other.getIsbn()))) &&
            ((this.manufacturerName==null && other.getManufacturerName()==null) || 
             (this.manufacturerName!=null &&
              this.manufacturerName.equals(other.getManufacturerName()))) &&
            ((this.manufacturerSku==null && other.getManufacturerSku()==null) || 
             (this.manufacturerSku!=null &&
              this.manufacturerSku.equals(other.getManufacturerSku()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.price==null && other.getPrice()==null) || 
             (this.price!=null &&
              this.price.equals(other.getPrice()))) &&
            ((this.retailPrice==null && other.getRetailPrice()==null) || 
             (this.retailPrice!=null &&
              this.retailPrice.equals(other.getRetailPrice()))) &&
            ((this.salePrice==null && other.getSalePrice()==null) || 
             (this.salePrice!=null &&
              this.salePrice.equals(other.getSalePrice()))) &&
            ((this.sku==null && other.getSku()==null) || 
             (this.sku!=null &&
              this.sku.equals(other.getSku()))) &&
            ((this.upc==null && other.getUpc()==null) || 
             (this.upc!=null &&
              this.upc.equals(other.getUpc())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAdId() != null) {
            _hashCode += getAdId().hashCode();
        }
        if (getAdvertiserId() != null) {
            _hashCode += getAdvertiserId().hashCode();
        }
        if (getAdvertiserName() != null) {
            _hashCode += getAdvertiserName().hashCode();
        }
        if (getBuyUrl() != null) {
            _hashCode += getBuyUrl().hashCode();
        }
        if (getCatalogId() != null) {
            _hashCode += getCatalogId().hashCode();
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getImageUrl() != null) {
            _hashCode += getImageUrl().hashCode();
        }
        if (getInStock() != null) {
            _hashCode += getInStock().hashCode();
        }
        if (getIsbn() != null) {
            _hashCode += getIsbn().hashCode();
        }
        if (getManufacturerName() != null) {
            _hashCode += getManufacturerName().hashCode();
        }
        if (getManufacturerSku() != null) {
            _hashCode += getManufacturerSku().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getPrice() != null) {
            _hashCode += getPrice().hashCode();
        }
        if (getRetailPrice() != null) {
            _hashCode += getRetailPrice().hashCode();
        }
        if (getSalePrice() != null) {
            _hashCode += getSalePrice().hashCode();
        }
        if (getSku() != null) {
            _hashCode += getSku().hashCode();
        }
        if (getUpc() != null) {
            _hashCode += getUpc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Product.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://product.domain.cj.com", "Product"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "adId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("advertiserId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "advertiserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("advertiserName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "advertiserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buyUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "buyUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("catalogId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "catalogId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "currency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imageUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "imageUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inStock");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "inStock"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isbn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "isbn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manufacturerName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "manufacturerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manufacturerSku");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "manufacturerSku"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retailPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "retailPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salePrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "salePrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sku");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "sku"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://product.domain.cj.com", "upc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
