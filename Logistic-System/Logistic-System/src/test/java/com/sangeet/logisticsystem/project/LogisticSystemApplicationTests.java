package com.sangeet.logisticsystem.project;

import com.sangeet.logisticsystem.project.controller.OrderController;
import com.sangeet.logisticsystem.project.controller.UserController;
import com.sangeet.logisticsystem.project.controller.VehicleDeliveryController;
import com.sangeet.logisticsystem.project.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class LogisticSystemApplicationTests {

	@Autowired
	private OrderController orderController;

	@Autowired
	private UserController userController;

	@Autowired
	private VehicleDeliveryController vehicleDeliveryController;

	@Test
	void contextLoads() {
	}

	@Test
	void test(){

		List<Vehicle> vehicleList = Arrays.asList(

				vehicleDeliveryController.registerBikeVehicle("CD-MX-0011", "Clark",
						new Location("loc-1", "city-1", "state-1", "country-1", 330033, 23.00, 84.00)),
				vehicleDeliveryController.registerBikeVehicle("CD-MX-0022", "Bruce",
						new Location("loc-11", "city-11", "state-11", "country-11", 440044, 23.33, 84.44))
		);

		List<User> userList = Arrays.asList(
				userController.createUser("abc@abc.com", "112233", "Jhon"),
				userController.createUser("ABCD@abc.com", "111221333", "Doe")
		);

		List<Item> itemList1 = Arrays.asList(
				new Item("Choclate1", 10.0, 0.20),
				new Item("Choclate2", 10.0, 0.20),
				new Item("Wheat", 100.0, 0.60)
		);

		List<Item> itemList2 = Arrays.asList(
				new Item("Wheat", 100.0, 1.0),
				new Item("Rice", 50.0, 1.0)
		);

		List<Item> itemList3 = Arrays.asList(
				new Item("Pulses", 150.0, 2.0),
				new Item("Vegetables", 100.0, 1.5)
		);

		OrderPackage orderPackage1 = new OrderPackage();
		orderPackage1.addItemsToPackage(itemList1);

		OrderPackage orderPackage2 = new OrderPackage();
		orderPackage2.addItemsToPackage(itemList2);

		OrderPackage orderPackage3 = new OrderPackage();
		orderPackage3.addItemsToPackage(itemList3);

		Order order_1 = orderController.createOrder(userList.get(0).getUserID(),
				Arrays.asList(orderPackage1),
				new Location("loc1", "city1", "state1", "country1", 110011, 23.011, 84.011),
				new Location("loc2", "city2", "state2", "country2", 110088, 24.011, 86.011)
		);

		System.out.println("## Order:1 created");
		System.out.println(order_1.toString());

		System.out.println("## Order:1 track with correct user ID");
		System.out.println(orderController.trackOrder(userList.get(0).getUserID(), order_1.getOrderID()));

		System.out.println("## Order:1 track with different user ID");
		System.out.println(orderController.trackOrder(userList.get(1).getUserID(), order_1.getOrderID()));

		System.out.println("## Order:1 update order -> cancel");
		System.out.println(orderController.updateOrder(order_1.getOrderID(), OrderStatus.CANCELLED));

		System.out.println("## Order:1 track with correct user ID after cancellation");
		System.out.println(orderController.trackOrder(userList.get(0).getUserID(), order_1.getOrderID()));

		//keep the max_order_taking_capacity in BikeDelivery class to 2 for testing purpose

		System.out.println();

		Order order_2 = orderController.createOrder(userList.get(1).getUserID(),
				Arrays.asList(orderPackage2),
				new Location("loc10", "city10", "state10", "country10", 131099, 26.301, 82.601),
				new Location("loc20", "city20", "state20", "country20", 121011, 22.103, 84.106)
		);

		System.out.println("## Order:2 created");
		System.out.println(order_2.toString());

		System.out.println("## Order:2 track with correct user ID");
		System.out.println(orderController.trackOrder(userList.get(1).getUserID(), order_2.getOrderID()));

		System.out.println();

		System.out.println("## Vehicle: orders per vehicle");
		List<Order> orderPerVehicle = vehicleDeliveryController
				.allCurrentOrdersWithVehicle(vehicleList.get(0).getVehicleNo());
		System.out.println(vehicleList.get(0).getVehicleNo()+ " orders count: "+orderPerVehicle.size());
		orderPerVehicle.stream().forEach(order -> System.out.println(order.toString()));

		orderPerVehicle = vehicleDeliveryController
				.allCurrentOrdersWithVehicle(vehicleList.get(1).getVehicleNo());
		System.out.println(vehicleList.get(1).getVehicleNo()+ " orders count: "+orderPerVehicle.size());
		orderPerVehicle.stream().forEach(order -> System.out.println(order.toString()));

		System.out.println();

		Order order_3 = orderController.createOrder(userList.get(1).getUserID(),
				Arrays.asList(orderPackage3),
				new Location("loc10", "city10", "state10", "country10", 131099, 26.301, 82.601),
				new Location("loc20", "city20", "state20", "country20", 121011, 22.103, 84.106)
		);

		System.out.println("## Order:3 created");
		System.out.println(order_3.toString());

		System.out.println("## Order:3 track with correct user ID");
		System.out.println(orderController.trackOrder(userList.get(1).getUserID(), order_3.getOrderID()));

		System.out.println();

		System.out.println("## Order:2 track with correct user ID after delivery vehicle got max order");
		System.out.println(orderController.trackOrder(userList.get(1).getUserID(), order_2.getOrderID()));

		System.out.println();

		System.out.println("## Vehicle: orders per vehicle");
		orderPerVehicle = vehicleDeliveryController
				.allCurrentOrdersWithVehicle(vehicleList.get(0).getVehicleNo());
		System.out.println(vehicleList.get(0).getVehicleNo()+ " orders count: "+orderPerVehicle.size());
		orderPerVehicle.stream().forEach(order -> System.out.println(order.toString()));

		orderPerVehicle = vehicleDeliveryController
				.allCurrentOrdersWithVehicle(vehicleList.get(1).getVehicleNo());
		System.out.println(vehicleList.get(1).getVehicleNo()+ " orders count: "+orderPerVehicle.size());
		orderPerVehicle.stream().forEach(order -> System.out.println(order.toString()));

		System.out.println();

		//order 3 got delivered
		System.out.println("## Order:3 update order -> delivered");
		System.out.println(orderController.updateOrder(order_3.getOrderID(), OrderStatus.DELIVERED));

		System.out.println("## Order:3 track with correct user ID");
		System.out.println(orderController.trackOrder(userList.get(1).getUserID(), order_3.getOrderID()));

		System.out.println("## Order:3 details");
		System.out.println(order_3.toString());

		System.out.println();

		System.out.println("## Vehicle: orders per vehicle");
		orderPerVehicle = vehicleDeliveryController
				.allCurrentOrdersWithVehicle(vehicleList.get(0).getVehicleNo());
		System.out.println(vehicleList.get(0).getVehicleNo()+ " orders count: "+orderPerVehicle.size());
		orderPerVehicle.stream().forEach(order -> System.out.println(order.toString()));

		orderPerVehicle = vehicleDeliveryController
				.allCurrentOrdersWithVehicle(vehicleList.get(1).getVehicleNo());
		System.out.println(vehicleList.get(1).getVehicleNo()+ " orders count: "+orderPerVehicle.size());
		orderPerVehicle.stream().forEach(order -> System.out.println(order.toString()));

		System.out.println();

	}

}
