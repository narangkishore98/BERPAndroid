package com.bikloo.berpandroid.Classes

class Product
{
    enum class ProductType
    {
        Food,Bakery,Organic,Dairy,Beverages
    }

    companion object
    {
        var PRODUCT_ID = 0
    }
    var productID:Int? = null
    var name:String? = null
    var barcode:String? = null
    var description:String? = null
    var price:Float? = null
    var productType:ProductType? = null

    constructor(
        name: String?,
        barcode: String?,
        description: String?,
        price: Float?,
        productType: ProductType?
    ) {
        PRODUCT_ID += 1
        this.productID = PRODUCT_ID
        this.name = name
        this.barcode = barcode
        this.description = description
        this.price = price
        this.productType = productType
    }

    override fun toString(): String {
        return "Product(productID=$productID, name=$name, barcode=$barcode, description=$description, price=$price, productType=$productType)"
    }

}