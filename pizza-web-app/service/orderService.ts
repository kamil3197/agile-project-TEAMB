import { Order } from "../model/orderr";

const axios = require('axios');

module.exports.getOrders = async function (): Promise<Order[]> {
    try {
        const response = await axios.get('http://localhost:8080/api/orders');

        return response.data;
    }
    catch (e) {
        throw new Error('Could not get products');
    }
}

module.exports.getOrderById = async function (id: number): Promise<Order> {
    try {
        const response = await axios.get(`http://localhost:8080/api/orders/${id}`);

        return response.data;
    }
    catch (e) {
        throw new Error('Could not get products');
    }
}