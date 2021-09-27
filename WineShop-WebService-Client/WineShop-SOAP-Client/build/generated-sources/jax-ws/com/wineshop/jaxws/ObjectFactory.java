
package com.wineshop.jaxws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.wineshop.jaxws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetProductsByNameResponse_QNAME = new QName("http://jaxws.wineshop.com/", "getProductsByNameResponse");
    private final static QName _GetProductsByName_QNAME = new QName("http://jaxws.wineshop.com/", "getProductsByName");
    private final static QName _GetProductById_QNAME = new QName("http://jaxws.wineshop.com/", "getProductById");
    private final static QName _GetProductByIdResponse_QNAME = new QName("http://jaxws.wineshop.com/", "getProductByIdResponse");
    private final static QName _GetProducts_QNAME = new QName("http://jaxws.wineshop.com/", "getProducts");
    private final static QName _GetProductsResponse_QNAME = new QName("http://jaxws.wineshop.com/", "getProductsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.wineshop.jaxws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetProductsByName }
     * 
     */
    public GetProductsByName createGetProductsByName() {
        return new GetProductsByName();
    }

    /**
     * Create an instance of {@link GetProductsByNameResponse }
     * 
     */
    public GetProductsByNameResponse createGetProductsByNameResponse() {
        return new GetProductsByNameResponse();
    }

    /**
     * Create an instance of {@link GetProductsResponse }
     * 
     */
    public GetProductsResponse createGetProductsResponse() {
        return new GetProductsResponse();
    }

    /**
     * Create an instance of {@link GetProducts }
     * 
     */
    public GetProducts createGetProducts() {
        return new GetProducts();
    }

    /**
     * Create an instance of {@link GetProductByIdResponse }
     * 
     */
    public GetProductByIdResponse createGetProductByIdResponse() {
        return new GetProductByIdResponse();
    }

    /**
     * Create an instance of {@link GetProductById }
     * 
     */
    public GetProductById createGetProductById() {
        return new GetProductById();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductsByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.wineshop.com/", name = "getProductsByNameResponse")
    public JAXBElement<GetProductsByNameResponse> createGetProductsByNameResponse(GetProductsByNameResponse value) {
        return new JAXBElement<GetProductsByNameResponse>(_GetProductsByNameResponse_QNAME, GetProductsByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductsByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.wineshop.com/", name = "getProductsByName")
    public JAXBElement<GetProductsByName> createGetProductsByName(GetProductsByName value) {
        return new JAXBElement<GetProductsByName>(_GetProductsByName_QNAME, GetProductsByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.wineshop.com/", name = "getProductById")
    public JAXBElement<GetProductById> createGetProductById(GetProductById value) {
        return new JAXBElement<GetProductById>(_GetProductById_QNAME, GetProductById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.wineshop.com/", name = "getProductByIdResponse")
    public JAXBElement<GetProductByIdResponse> createGetProductByIdResponse(GetProductByIdResponse value) {
        return new JAXBElement<GetProductByIdResponse>(_GetProductByIdResponse_QNAME, GetProductByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProducts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.wineshop.com/", name = "getProducts")
    public JAXBElement<GetProducts> createGetProducts(GetProducts value) {
        return new JAXBElement<GetProducts>(_GetProducts_QNAME, GetProducts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.wineshop.com/", name = "getProductsResponse")
    public JAXBElement<GetProductsResponse> createGetProductsResponse(GetProductsResponse value) {
        return new JAXBElement<GetProductsResponse>(_GetProductsResponse_QNAME, GetProductsResponse.class, null, value);
    }

}
