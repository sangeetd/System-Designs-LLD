package com.sangeetdas.project;

import com.sangeetdas.project.managers.AccountManager;
import com.sangeetdas.project.messages.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ChatAppLldApplicationTests {

	@Test
	public void testing(){

		System.out.println("### Registering Sangeet");
		//Sangeet is a registered user
		AccountManager sangeet = new AccountManager();
		sangeet.createAccount("22223344", "sangeet");

		System.out.println("### Registering Shubham");
		//Shubham is a registered user
		AccountManager shubham = new AccountManager();
		shubham.createAccount("22223355", "shubham");

		System.out.println("### Adding contacts in Sangeet app library");
		//adding contacts in Sangeet app library
		sangeet.addContact("22223355", "shubham");
		sangeet.addContact("22223366", "shanti"); //NOT A REGISTERED USER

		System.out.println("### Message From : sangeet, To : shubham");
		//Message From : sangeet, To : shubham
		Message message = new Message("hello world",
				sangeet.getUserAccountDetails().getUserContact().getContactNo(),
				"22223355");
		sangeet.send("22223355", message);
		message = new Message("Java is best language. here is the attachment",
				new MultimediaContent("https://storage.chatapp.com/[content-id]",
						3.0,
						MultimediaType.PDF),
				sangeet.getUserAccountDetails().getUserContact().getContactNo(),
				"22223355");
		sangeet.send("22223355", message);

		System.out.println("### Message that has been sent by Sangeet should be visible locally to Sangeet");
		//Message that has been sent by Sangeet should be visible locally to Sangeet
		IMessageFormatter defaultMessageFormatter = new DefaultMessageFormatter();
		List<Message> messageList = sangeet.retrieveMessage("22223355");
		messageList.stream().forEach(msg -> System.out.println(defaultMessageFormatter.format(msg)));

		System.out.println("### Sending message to Shanti who is not registered user globally in your App system");
		//Sending message to Shanti who is not registered user globally in your App system
		Exception exception = assertThrows(RuntimeException.class,
				() -> sangeet.send("22223366", new Message("Hi mom",
						sangeet.getUserAccountDetails().getUserContact().getContactNo(),
						"22223366")));
		assertTrue(exception.getMessage().contains("The user you want to send this message is not registered with us"));

		System.out.println("### Sangeet trying to retrieve message for Shanti who is not a registered user and a message never sent to her");
		exception = assertThrows(RuntimeException.class,
				() -> sangeet.retrieveMessage("22223366"));
		assertTrue(exception.getMessage().contains("Unable to retrieve chat"));

		System.out.println("### Messages that Shubham has received from Sangeet should be visible to Shubham");
		//Messages that Shubham has received from Sangeet should be visible to Shubham
		messageList = shubham.retrieveMessage("22223344");
		messageList.stream().forEach(msg -> System.out.println(defaultMessageFormatter.format(msg)));

		System.out.println("### Shubham replying to the messages Sangeet has sent to him");
		shubham.send("22223344", new Message("Yeah Sangeet, Java is really best language",
				shubham.getUserAccountDetails().getUserContact().getContactNo(),
				"22223344"));
		shubham.send("22223344", new Message("And we both think likewise",
				shubham.getUserAccountDetails().getUserContact().getContactNo(),
				"22223344"));

		System.out.println("### Retrieving Message that has been sent by Shubham as a reply to Sangeet");
		//Retrieving Message that has been sent by Shubham as a reply to Sangeet
		messageList = sangeet.retrieveMessage("22223355");
		messageList.stream().forEach(msg -> System.out.println(defaultMessageFormatter.format(msg)));

	}

}
