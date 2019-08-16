package com.bikloo.berpandroid.Classes

class Enterprise
{
    enum class EnterpriseType
    {
        Restaurant,Store,MegaMart
    }
    companion object
    {
        var ENTERPRISE_ID:Int = 0
    }
    var enterpriseID:Int? = null
    var enterpriseName:String? = null
    var address:String? = null
    var bonusMultiplier:Int = 1
    var enterpriseType:EnterpriseType? = null
    var products:MutableList<Product> = ArrayList()
    var employees:MutableList<Employee> = ArrayList()
    var orders:MutableList<Order> = ArrayList()

    constructor(
        enterpriseName: String?,
        address: String?,
        enterpriseType: EnterpriseType?
    ) {
        ENTERPRISE_ID +=1
        this.enterpriseID = ENTERPRISE_ID
        this.enterpriseName = enterpriseName
        this.address = address
        this.enterpriseType = enterpriseType
    }

    override fun toString(): String {
        return "Enterprise(enterpriseID=$enterpriseID, enterpriseName=$enterpriseName, address=$address, bonusMultiplier=$bonusMultiplier, enterpriseType=$enterpriseType, products=$products, employees=$employees, orders=$orders)"
    }

    fun getMaxIncome():Float
    {
        var income:Float = 0F
        for(order in orders)
        {
            income += order.totalPrice
        }
        return income
    }

    fun addEmployee(employee:Employee)
    {

        employee.workingIn = this
        this.employees.add(employee)

    }
    fun addProduct(product:Product)
    {
        this.products.add(product)
    }
    fun getEmployee(withEmail:String) : Employee?
    {
        for(employee in employees)
        {
            if(employee.email == withEmail)
            {
                return employee
            }
        }
        return null
    }
    fun addOrder(order:Order)
    {
        orders.add(order)
    }

    fun getOrdersOfEmployee(withEmployee:Employee):MutableList<Order>
    {
        var tempOrders:MutableList<Order> = ArrayList()
        for(order in orders)
        {
            if(order.orderMadeBy!!.userId == withEmployee.userId)
            {
                tempOrders.add(order)
            }
        }
        return tempOrders
    }

    fun getEmployeesTopToBottom() : MutableList<Employee>
    {
        var newEmployees:MutableList<Employee> = employees
        newEmployees.sortedByDescending { employee -> employee.points }
        return newEmployees
    }
}